package cn.jaly.template.service;

import cn.jaly.template.dao.TemplateAttributeMapper;
import cn.jaly.template.entity.TemplateAttribute;
import cn.jaly.template.entity.TemplateAttributeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TemplateAttributeService {

	@Autowired
	private TemplateAttributeMapper templateAttributeMapper;
	
	@Transactional(readOnly=true)
	public List<TemplateAttribute> getByAttrName(String attrName){
		TemplateAttributeExample example = new TemplateAttributeExample();
		TemplateAttributeExample.Criteria criteria = example.createCriteria();
		criteria.andAttrNameEqualTo(attrName);
		return templateAttributeMapper.selectByExample(example);
	}
	
	@Transactional(readOnly=true)
	public TemplateAttribute getById(Integer id){
		return templateAttributeMapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public void save(TemplateAttribute templateAttribute){
		if(templateAttribute.getId() == null){
			templateAttributeMapper.insert(templateAttribute);
		} else {
			templateAttributeMapper.updateByPrimaryKeySelective(templateAttribute);
		}
	}
	
	@Transactional
	public void delete(Integer id){
		templateAttributeMapper.deleteByPrimaryKey(id);
	}
}
