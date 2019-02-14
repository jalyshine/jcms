package cn.jaly.admin.service;

import cn.jaly.admin.dao.SiteContactMapper;
import cn.jaly.admin.entity.SiteContact;
import cn.jaly.admin.entity.SiteContactExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SiteContactService {

    @Autowired
    private SiteContactMapper siteContactMapper;

    @Transactional(readOnly = true)
    public List<SiteContact> queryForList(Integer siteId, String keyword, String order) {
        SiteContactExample example = new SiteContactExample();
        if (order == null || "".equals(order)) {
            order = "time desc";
        }
        example.setOrderByClause(order);
        SiteContactExample.Criteria criteria = example.createCriteria();
        criteria.andSiteIdEqualTo(siteId);
        if (keyword != null && !"".equals(keyword)) {
            criteria.andContentLike("%" + keyword + "%");
        }
        return siteContactMapper.selectByExample(example);
    }


    @Transactional(readOnly = true)
    public SiteContact getById(Integer id) {
        return siteContactMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public void save(SiteContact siteContact){
        siteContact.setTime(new Date());
        if(siteContact.getId() == null){
            siteContactMapper.insert(siteContact);
        } else {
            siteContactMapper.updateByPrimaryKeySelective(siteContact);
        }
    }

    @Transactional
    public void delete(Integer id) {
        siteContactMapper.deleteByPrimaryKey(id);
    }
}
