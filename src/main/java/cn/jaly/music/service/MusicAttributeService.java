package cn.jaly.music.service;

import cn.jaly.music.dao.MusicAttributeMapper;
import cn.jaly.music.entity.MusicAttribute;
import cn.jaly.music.entity.MusicAttributeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MusicAttributeService {

	@Autowired
	private MusicAttributeMapper musicAttributeMapper;
	
	@Transactional(readOnly=true)
	public List<MusicAttribute> getByAttrName(String attrName){
		MusicAttributeExample example = new MusicAttributeExample();
		MusicAttributeExample.Criteria criteria = example.createCriteria();
		criteria.andAttrNameEqualTo(attrName);
		return musicAttributeMapper.selectByExample(example);
	} 
	
	@Transactional(readOnly=true)
	public MusicAttribute getById(Integer id){
		return musicAttributeMapper.selectByPrimaryKey(id);
	} 
	
	@Transactional
	public void delete(Integer id){
		musicAttributeMapper.deleteByPrimaryKey(id);
	}
	
	@Transactional
	public void save(MusicAttribute musicAttribute){
		if(musicAttribute.getId() == null){
			musicAttributeMapper.insert(musicAttribute);
		} else {
			musicAttributeMapper.updateByPrimaryKeySelective(musicAttribute);
		} 
	}
}
