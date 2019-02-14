package cn.jaly.admin.service;

import cn.jaly.admin.dao.JsonAttributeMapper;
import cn.jaly.admin.entity.JsonAttribute;
import cn.jaly.admin.entity.JsonAttributeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class JsonAttributeService {

    @Autowired
    private JsonAttributeMapper jsonAttributeMapper;

    @Transactional(readOnly = true)
    public List<JsonAttribute> getByFieldName(Integer siteId, String tableName, String fieldName){
        JsonAttributeExample example = new JsonAttributeExample();
        JsonAttributeExample.Criteria criteria = example.createCriteria();
        criteria.andSiteIdEqualTo(siteId);
        criteria.andTableNameEqualTo(tableName);
        criteria.andFieldNameEqualTo(fieldName);
        return jsonAttributeMapper.selectByExample(example);
    }

    @Transactional
    public void batchInsert(Integer siteId, String tableName, String fieldName, String[] names, String[] types){
        List<JsonAttribute> attributes = new ArrayList<>();
        for(int i=0; i<names.length; i++){
            JsonAttribute attribute = new JsonAttribute();
            attribute.setSiteId(siteId);
            attribute.setTableName(tableName);
            attribute.setFieldName(fieldName);
            attribute.setName(names[i]);
            attribute.setType(types[i]);
            attributes.add(attribute);
        }
        jsonAttributeMapper.batchInsert(attributes);
    }

    @Transactional
    public void delete(Integer id){
        jsonAttributeMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    public void update(JsonAttribute attribute){
        jsonAttributeMapper.updateByPrimaryKeySelective(attribute);
    }
}
