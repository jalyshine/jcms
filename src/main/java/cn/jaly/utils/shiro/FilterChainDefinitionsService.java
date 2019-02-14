package cn.jaly.utils.shiro;

import cn.jaly.admin.dao.AdminRoleMapper;
import cn.jaly.admin.dao.AdminRolePrivacyMapper;
import cn.jaly.admin.dao.BackMenuMapper;
import cn.jaly.admin.entity.AdminRole;
import cn.jaly.admin.entity.AdminRolePrivacy;
import cn.jaly.admin.entity.BackMenu;
import cn.jaly.admin.entity.BackMenuExample;
import cn.jaly.utils.common.ExceptionUtils;
import cn.jaly.utils.common.MapUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.config.Ini;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 生成权限过滤器链
 */
public class FilterChainDefinitionsService {

    private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    private String definitions = "";

    @Autowired
    private AdminRolePrivacyMapper adminRolePrivacyMapper;

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Autowired
    private BackMenuMapper backMenuMapper;

    @Autowired
    private ShiroFilterFactoryBean shiroFilterFactoryBean;

    @PostConstruct
    public void intiPermission() {
        Ini ini = new Ini();
        ini.load(definitions); // 加载资源文件节点串
        Ini.Section section = ini.getSection("urls"); // 使用默认节点
        if (CollectionUtils.isEmpty(section)) {
            section = ini.getSection(Ini.DEFAULT_SECTION_NAME); // 如不存在默认节点切割,则使用空字符转换
        }
        Map<String, String> export = MapUtils.fromProperties(definitions);
        Map<String, String> permissionMap = getOtherPermission(export);
        if (permissionMap != null && !permissionMap.isEmpty()) {
            section.putAll(permissionMap);
        }
        shiroFilterFactoryBean.setFilterChainDefinitionMap(section);
    }

    public void updatePermission() {
        synchronized (shiroFilterFactoryBean) {
            AbstractShiroFilter shiroFilter = null;
            try {
                shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
            } catch (Exception e) {
                logger.error(ExceptionUtils.formatExceptionInfo(e));
            }
            // 获取过滤管理器
            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter
                    .getFilterChainResolver();
            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();
            // 清空初始权限配置
            manager.getFilterChains().clear();
            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
            // 重新构建生成
            shiroFilterFactoryBean.setFilterChainDefinitions(definitions);
            Map<String, String> filterChainDefinitionMap = shiroFilterFactoryBean.getFilterChainDefinitionMap();
            Map<String, String> dynamicDefinitionMap = getOtherPermission(filterChainDefinitionMap);
            filterChainDefinitionMap.putAll(dynamicDefinitionMap);
            for (Map.Entry<String, String> entry : filterChainDefinitionMap.entrySet()) {
                String chainDefinition = entry.getValue().trim().replace(" ", "");
                manager.createChain(entry.getKey(), chainDefinition);
            }
        }
    }

    private Map<String, String> getOtherPermission(Map<String, String> export) {
        Map<String, String> map = new LinkedHashMap<>();
        // 查找已设置的权限
        List<AdminRolePrivacy> privacies = adminRolePrivacyMapper.selectByExampleWithRole(null);
        for (AdminRolePrivacy privacy : privacies) {
            String key = "/" + privacy.getModule() + "/" + privacy.getEntity() + "/" + privacy.getAction();
            if(export.containsKey(key)){
                continue;
            }
            String value = "\"" + privacy.getAdminRole().getName() + "\"";
            if (map.containsKey(key)) {
                value = map.get(key) + "," + value;
            }
            map.put(key, value);
        }
        // roleId = 1的管理员角色名称，这个角色拥有全站任何权限。
        AdminRole adminRole = adminRoleMapper.selectByPrimaryKey(1);
        String superName = adminRole.getName();
        // 查找所有带操作的菜单，若权限表未设置，则赋予超级管理员权限
        BackMenuExample example = new BackMenuExample();
        BackMenuExample.Criteria criteria = example.createCriteria();
        criteria.andDepthGreaterThan((byte) 2);
        List<BackMenu> backMenus = backMenuMapper.selectByExample(example);
        for(BackMenu backMenu : backMenus){
            String key = "/" + backMenu.getModule() + "/" + backMenu.getEntity() + "/" + backMenu.getAction();
            if(map.containsKey(key) || export.containsKey(key)){
                continue;
            }
            map.put(key, "\"" + superName + "\"");
        }
        // 封装到adminRoles
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String value = "adminRoles[" + entry.getValue() + "]";
            map.put(entry.getKey(), value);
        }
        return map;
    }

    public String getDefinitions() {
        return definitions;
    }

    public void setDefinitions(String definitions) {
        this.definitions = definitions;
    }
}