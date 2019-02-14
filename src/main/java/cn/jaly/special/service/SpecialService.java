package cn.jaly.special.service;

import cn.jaly.special.dao.SpecialMapper;
import cn.jaly.special.dao.SpecialTypeMapper;
import cn.jaly.special.entity.Special;
import cn.jaly.special.entity.SpecialExample;
import cn.jaly.special.entity.SpecialType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SpecialService {

    @Autowired
    private SpecialMapper specialMapper;

    @Autowired
    private SpecialTypeMapper specialTypeMapper;

    @Transactional(readOnly = true)
    public List<Special> getBySite(Integer siteId, String order) {
        SpecialExample example = new SpecialExample();
        if (order != null && !"".equals(order)) {
            example.setOrderByClause(order);
        }
        SpecialExample.Criteria criteria = example.createCriteria();
        criteria.andSiteIdEqualTo(siteId);
        return specialMapper.selectByExample(example);
    }

    @Transactional(readOnly = true)
    public Special getById(Integer id) {
        return specialMapper.selectByPrimaryKey(id);
    }

    @Transactional(readOnly=true)
    public Integer getIdByTitle(String title){
        SpecialExample example = new SpecialExample();
        SpecialExample.Criteria criteria = example.createCriteria();
        criteria.andTitleEqualTo(title);
        List<Special> specials = specialMapper.selectByExample(example);
        if(specials == null || specials.isEmpty()){
            return null;
        } else {
            return specials.get(0).getId();
        }
    }

    @Transactional
    public void save(Special special, Integer[] sid,
                     Integer[] order, String[] name, String[] desc, String[] path) {
        special.setUpdateTime(new Date());
        if (special.getId() == null) {
            specialMapper.insert(special);
        } else {
            specialMapper.updateByPrimaryKeySelective(special);
        }

        if (order != null) {
            for (int i = 0; i < order.length; i++) {
                SpecialType type = new SpecialType();
                type.setId(sid[i]);
                type.setListOrder(order[i]);
                type.setName(name[i]);
                type.setDescription(desc[i]);
                type.setTypeDir(path[i]);
                type.setSpecialId(special.getId());
                if (sid[i] == 0) {
                    specialTypeMapper.insert(type);
                } else {
                    specialTypeMapper.updateByPrimaryKey(type);
                }
            }
        }
    }

    @Transactional
    public void delete(Integer id) {
        specialMapper.deleteByPrimaryKey(id);
    }
}
