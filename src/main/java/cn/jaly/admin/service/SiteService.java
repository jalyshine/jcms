package cn.jaly.admin.service;

import cn.jaly.admin.dao.SiteMapper;
import cn.jaly.admin.entity.Site;
import cn.jaly.admin.entity.SiteExample;
import cn.jaly.utils.explorer.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
public class SiteService {

    @Autowired
    private SiteMapper siteMapper;

    @Transactional(readOnly = true)
    public List<Site> queryForList(String keyword){
        SiteExample example = new SiteExample();
        SiteExample.Criteria criteria = example.createCriteria();
        if(keyword != null && !keyword.isEmpty()){
            criteria.andNameLike("%" + keyword + "%");
        }
        List<Site> sites = siteMapper.selectByExample(example);
        return sites;
    }

    @Transactional(readOnly = true)
    public List<Site> getByAdminPrivacy(Integer adminRoleId){
        return siteMapper.selectByAdminPrivacy(adminRoleId);
    }

    @Transactional(readOnly = true)
    public Site getById(Integer id){
        return siteMapper.selectByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    public Integer getIdByName(String name){
        SiteExample example = new SiteExample();
        SiteExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        List<Site> sites = siteMapper.selectByExample(example);
        if(sites == null || sites.isEmpty()){
            return null;
        }
        return sites.get(0).getId();
    }

    @Transactional
    public void delete(Integer id){
        siteMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    public void save(Site site) throws IOException {
        if(site.getId() == null){
            siteMapper.insert(site);
        } else {
            siteMapper.updateByPrimaryKeySelective(site);
        }
        synchronizeSetting(site);
    }

    /**
     * 同步账户配置
     *
     * @param site
     */
    private void synchronizeSetting(Site site) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("account=").append(site.getUserName())
                .append("&password=").append(site.getPassword())
                .append("&st=").append(site.getStartTimeStr())
                .append("&et=").append(site.getEndTimeStr())
                .append("&id=").append(site.getId());
        HttpUtils.httpPost(site.getDomain() + "/admin", sb.toString());
    }
}
