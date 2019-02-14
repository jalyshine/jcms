package cn.jaly.admin.service;

import cn.jaly.admin.dao.AdminRolePrivacyMapper;
import cn.jaly.admin.dao.BackMenuMapper;
import cn.jaly.admin.entity.AdminRolePrivacy;
import cn.jaly.admin.entity.AdminRolePrivacyExample;
import cn.jaly.admin.entity.BackMenu;
import cn.jaly.admin.entity.BackMenuExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class AdminRolePrivacyService {

    @Autowired
    private AdminRolePrivacyMapper adminRolePrivacyMapper;

    @Autowired
    private BackMenuMapper backMenuMapper;

    @Transactional(readOnly = true)
    public List<Integer> getOwnSiteIdsByRoleId(Integer roleId) {
        return adminRolePrivacyMapper.selectOwnSiteIdsByRoleId(roleId);
    }

    @Transactional(readOnly = true)
    public List<BackMenu> initBackMenuList(Integer roleId, Integer siteId, List<BackMenu> backMenus) {
        AdminRolePrivacyExample example = new AdminRolePrivacyExample();
        AdminRolePrivacyExample.Criteria criteria = example.createCriteria();
        criteria.andAdminRoleIdEqualTo(roleId);
        criteria.andSiteIdEqualTo(siteId);
        List<AdminRolePrivacy> privacies = adminRolePrivacyMapper.selectByExample(example);

        for (BackMenu backMenu : backMenus) {
            backMenu.setDisplay(false);
        }

        if (privacies != null && !privacies.isEmpty()) {
            for (BackMenu backMenu : backMenus) {
                for (AdminRolePrivacy privacy : privacies) {
                    if (privacy.equalsBackMenu(backMenu)) {
                        backMenu.setDisplay(true);
                        break;
                    }
                }
            }
        }
        return backMenus;
    }

    /**
     * 查找数据库中是否有给定条件的权限。包括uri,queryString,Site,role
     * @param uriMap
     * @param queryString
     * @param siteId
     * @param roleId
     * @return
     */
    @Transactional(readOnly = true)
    public boolean isPrivacyPass(Map<String, String> uriMap, String queryString, Integer siteId, Integer roleId){
        List<AdminRolePrivacy> privacies = adminRolePrivacyMapper.selectAsMenu(uriMap.get("module"),
                uriMap.get("entity"), uriMap.get("action"), siteId, roleId);
        if(privacies == null || privacies.isEmpty()){
            return false;
        }
        if(queryString == null){
            return true;
        }
        for(AdminRolePrivacy privacy : privacies){
            String data = privacy.getData();
            if(data == null || queryString.contains(data)){
                return true;
            }
        }
        return false;
    }

    @Transactional
    public void save(Integer roleId, Integer siteId, Integer[] menuIds) {
        // 先清除原来的
        AdminRolePrivacyExample example1 = new AdminRolePrivacyExample();
        AdminRolePrivacyExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andAdminRoleIdEqualTo(roleId);
        criteria1.andSiteIdEqualTo(siteId);
        adminRolePrivacyMapper.deleteByExample(example1);
        if (menuIds != null && menuIds.length != 0) {
            // 再插入新的记录
            BackMenuExample example = new BackMenuExample();
            BackMenuExample.Criteria criteria = example.createCriteria();
            criteria.andIdIn(Arrays.asList(menuIds));
            List<BackMenu> backMenus = backMenuMapper.selectByExample(example);
            List<AdminRolePrivacy> privacies = new ArrayList<>();
            for (BackMenu backMenu : backMenus) {
                AdminRolePrivacy privacy = new AdminRolePrivacy(backMenu);
                privacy.setAdminRoleId(roleId);
                privacy.setSiteId(siteId);
                privacies.add(privacy);
            }
            adminRolePrivacyMapper.batchInsert(privacies);
        }
    }
}
