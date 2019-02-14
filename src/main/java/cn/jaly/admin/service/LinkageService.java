package cn.jaly.admin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.jaly.admin.dao.LinkageMapper;
import cn.jaly.admin.entity.Linkage;
import cn.jaly.admin.entity.LinkageExample;
import com.google.gson.internal.LinkedTreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LinkageService {

	@Autowired
	private LinkageMapper linkageMapper;

	@Transactional(readOnly = true)
	public List<Linkage> getAll() {
		LinkageExample example = new LinkageExample();
		example.setOrderByClause("list_order asc");
		LinkageExample.Criteria criteria = example.createCriteria();
		criteria.andParentIdIsNull();
		List<Linkage> roots = linkageMapper.selectByExample(example);

		List<Linkage> tree = new ArrayList<>();
		for(Linkage menu : roots){
			menu.setLevel((byte) 0);
			tree.add(menu);
			appendChildMenu(tree, menu);
		}
		return tree;
	}

	private void appendChildMenu(List<Linkage> tree, Linkage parent){
		int pId = parent.getId();
		int pLevel = parent.getLevel();
		List<Linkage> menus = getChilds(pId);
		if(menus != null){
			for(Linkage menu : menus){
				menu.setLevel((byte) (pLevel+1));
				tree.add(menu);
				appendChildMenu(tree, menu);
			}
		}
	}

	private List<Linkage> getChilds(Integer parentId){
		LinkageExample example = new LinkageExample();
		example.setOrderByClause("list_order asc");
		LinkageExample.Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		return linkageMapper.selectByExample(example);
	}
	
	@Transactional(readOnly = true)
	public List<Linkage> getByParentId(Integer siteId, Integer parentId) {
		LinkageExample example = new LinkageExample();
		LinkageExample.Criteria criteria = example.createCriteria();
		criteria.andSiteIdEqualTo(siteId);
		if (parentId == null){
			criteria.andParentIdIsNull();
		} else {
			criteria.andParentIdEqualTo(parentId);
		}
		return linkageMapper.selectByExample(example);
	} 

	@Transactional(readOnly = true)
	public Linkage getById(Integer id) {
		return linkageMapper.selectByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	public Map<Integer, String> getParentsById(Integer id){
		if(id == null){
			return null;
		}
		Linkage linkage = linkageMapper.selectByPrimaryKey(id);
		String parents = linkage.getParents();
		List<Integer> ids = new ArrayList<>();
		String[] idStrs = parents.split("\\,");
		if(idStrs != null && idStrs.length > 0){
			for(String str : idStrs){
				if(!"".equals(str)){
					ids.add(Integer.parseInt(str));
				}
			}
		}
		ids.add(id);

		LinkageExample example = new LinkageExample();
		LinkageExample.Criteria criteria = example.createCriteria();
		criteria.andIdIn(ids);
		List<Linkage> linkages = linkageMapper.selectByExample(example);
		Map<Integer, String> map = new LinkedTreeMap<>();
		for(Integer i : ids){
			for(Linkage l : linkages){
				if(l.getId() == i){
					map.put(i, l.getName());
					break;
				}
			}
		}
		return map;
	}

	@Transactional
	public void save(Linkage linkage) {
		if(linkage.getParentId() == 0){
			linkage.setParentId(null);
			linkage.setParents("");
		} else {
			Integer parentId = linkage.getParentId();
			String parents = linkageMapper.selectByPrimaryKey(parentId).getParents();
			if(!"".equals(parents)){
				parents += ",";
			}
			linkage.setParents(parents + parentId);
		}
		if(linkage.getId() == null){
			linkageMapper.insert(linkage);
		} else {
			linkageMapper.updateByPrimaryKey(linkage);
		}
	}

	@Transactional
	public void delete(Integer id) {
		linkageMapper.deleteByPrimaryKey(id);
	}
}
