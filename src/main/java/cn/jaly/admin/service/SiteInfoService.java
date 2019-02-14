package cn.jaly.admin.service;

import cn.jaly.admin.dao.SiteInfoMapper;
import cn.jaly.admin.entity.SiteInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SiteInfoService {

    @Autowired
    private SiteInfoMapper siteInfoMapper;

    @Transactional(readOnly = true)
    public SiteInfo getBySiteId(Integer siteId){
        SiteInfo siteInfo = siteInfoMapper.selectByPrimaryKey(siteId);
        if(siteInfo == null){
            siteInfo = new SiteInfo();
        }
        return siteInfo;
    }

    @Transactional
    public void save(SiteInfo siteInfo){
        Integer siteId = siteInfo.getSiteId();
        SiteInfo setting = siteInfoMapper.selectByPrimaryKey(siteId);
        if(setting == null){
            siteInfoMapper.insert(siteInfo);
        } else {
            siteInfoMapper.updateByPrimaryKeySelective(siteInfo);
        }
    } 
}
