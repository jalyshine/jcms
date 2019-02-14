package cn.jaly.admin.service;

import cn.jaly.admin.dao.SiteHitMapper;
import cn.jaly.admin.entity.SiteHit;
import cn.jaly.admin.entity.SiteHitExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SiteHitService {

    @Autowired
    private SiteHitMapper siteHitMapper;

    @Transactional(readOnly = true)
    public List<SiteHit> getNearestHits(Integer siteId, Integer days){
        return siteHitMapper.selectNearestHits(siteId, days);
    }

    @Transactional
    public void save(SiteHit siteHit){
        siteHitMapper.insert(siteHit);
    }
}
