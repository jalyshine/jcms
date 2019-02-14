package cn.jaly.tip.service;

import cn.jaly.tip.dao.TipMapper;
import cn.jaly.tip.entity.Tip;
import cn.jaly.tip.entity.TipExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class TipService {

	@Autowired
	private TipMapper tipMapper;

	@Transactional(readOnly=true)
	public List<Tip> queryForList(Integer siteId, Integer typeId, String keyword, String order){
		if(order == null || "".equals(order)) {
			order = "update_time desc";
		}
		if(keyword != null && !"".equals(keyword)){
			keyword = "%" + keyword + "%";
		}
		return tipMapper.queryForList(siteId, typeId, keyword, order);
	}
	
	@Transactional(readOnly=true)
	public Tip getById(Integer id){
		return tipMapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public void save(Tip tip){
		tip.setUpdateTime(new Date());
		if(tip.getId() == null){
			tipMapper.insert(tip);
		} else {
			tipMapper.updateByPrimaryKeySelective(tip);
		}
	}
	
	@Transactional
	public void delete(Integer id){ 
		tipMapper.deleteByPrimaryKey(id);
	}
	
}
