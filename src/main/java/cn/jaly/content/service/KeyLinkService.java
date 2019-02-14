package cn.jaly.content.service;

import cn.jaly.content.dao.KeyLinkMapper;
import cn.jaly.content.entity.KeyLink;
import cn.jaly.content.entity.KeyLinkExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class KeyLinkService {

    @Autowired
    private KeyLinkMapper keyLinkMapper;

    @Transactional(readOnly = true)
    public List<KeyLink> getBySiteId(Integer siteId, String keyword){
        KeyLinkExample example = new KeyLinkExample();
        KeyLinkExample.Criteria criteria = example.createCriteria();
        criteria.andSiteIdEqualTo(siteId);
        if(keyword != null && !"".equals(keyword)){
            criteria.andWordLike("%" + keyword + "%");
        }
        return keyLinkMapper.selectByExample(example);
    }

    @Transactional(readOnly = true)
    public KeyLink getById(Integer id){
        return keyLinkMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public void save(KeyLink keyLink){
        if(keyLink.getId() == null){
            keyLinkMapper.insert(keyLink);
        } else {
            keyLinkMapper.updateByPrimaryKeySelective(keyLink);
        }
    }

    @Transactional
    public void delete(Integer id){
        keyLinkMapper.deleteByPrimaryKey(id);
    }
}
