package cn.jaly.admin.service;

import cn.jaly.admin.dao.AdminRolePrivacyMapper;
import cn.jaly.admin.dao.BackMenuMapper;
import cn.jaly.admin.entity.AdminRolePrivacy;
import cn.jaly.admin.entity.AdminRolePrivacyExample;
import cn.jaly.admin.entity.BackMenu;
import cn.jaly.admin.entity.BackMenuExample;
import cn.jaly.utils.common.BasicUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class BackMenuService {

    @Autowired
    private BackMenuMapper backMenuMapper;

    @Autowired
    private AdminRolePrivacyMapper adminRolePrivacyMapper;

    /**
     * 获取所有菜单（深度<=4）
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<BackMenu> getAll() {
        return treeForList(null, null, (byte) 4);
    }

    /**
     * 获取所有父菜单（深度<=3）
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<BackMenu> getAllParents() {
        return treeForList(null, null, (byte) 3);
    }

    /**
     * 获取指定菜单ID的子菜单（深度<=4）
     *
     * @param parentId
     * @return
     */
    @Transactional(readOnly = true)
    public List<BackMenu> getChildForList(Integer parentId) {
        return treeForList(parentId, null, (byte) 4);
    }

    /**
     * 以列表形式获取树形菜单
     *
     * @param parentId
     * @param display
     * @param maxDepth
     * @return
     */
    private List<BackMenu> treeForList(Integer parentId, Boolean display, Byte maxDepth) {
        List<BackMenu> libs = null;
        List<BackMenu> tree = new ArrayList<>();
        BackMenu parent = null;
        BackMenuExample example = new BackMenuExample();
        BackMenuExample.Criteria criteria = example.createCriteria();
        if (display != null) {
            criteria.andDisplayEqualTo(display);
        }
        if (maxDepth != null) {
            criteria.andDepthLessThanOrEqualTo(maxDepth);
        }
        if (parentId == null) {
            libs = backMenuMapper.selectByExample(example);
        } else {
            criteria.andPathLike("%" + parentId + ",%");
            libs = backMenuMapper.selectByExample(example);
            parent = backMenuMapper.selectByPrimaryKey(parentId);
        }
        append(libs, tree, parent);
        return tree;
    }

    private void append(List<BackMenu> libs, List<BackMenu> tree, BackMenu parent) {
        List<BackMenu> temp = new ArrayList<>();
        Integer parentId = parent == null ? null : parent.getId();
        for (BackMenu menu : libs) {
            if (menu.getParentId() == parentId) {
                temp.add(menu);
            }
        }
        temp.sort(new Comparator<BackMenu>() {
            @Override
            public int compare(BackMenu o1, BackMenu o2) {
                return o1.getListOrder() - o2.getListOrder();
            }
        });
        for (BackMenu menu : temp) {
            tree.add(menu);
            append(libs, tree, menu);
        }
    }

    /**
     * 获取后台导航菜单（树形显示）
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<BackMenu> getIndexNavByPrivacy(Integer roleId, Integer siteId) {
        BackMenuExample example = new BackMenuExample();
        BackMenuExample.Criteria criteria = example.createCriteria();
        criteria.andDepthLessThanOrEqualTo((byte) 3);
        criteria.andDisplayEqualTo(true);
        List<BackMenu> libs = backMenuMapper.selectByExample(example);
        if (roleId != 1 && siteId != null) {
            AdminRolePrivacyExample privacyExample = new AdminRolePrivacyExample();
            AdminRolePrivacyExample.Criteria privacyExampleCriteria = privacyExample.createCriteria();
            privacyExampleCriteria.andSiteIdEqualTo(siteId);
            privacyExampleCriteria.andAdminRoleIdEqualTo(roleId);
            List<AdminRolePrivacy> privacies = adminRolePrivacyMapper.selectByExample(privacyExample);

            List<Integer> mids = new ArrayList<>();
            for (BackMenu menu : libs) {
                for (AdminRolePrivacy privacy : privacies) {
                    if (privacy.equalsBackMenu(menu)) {
                        String[] tokens = (menu.getPath() + menu.getId()).split("\\,");
                        for (String token : tokens) {
                            if (BasicUtils.isInteger(token)) {
                                Integer id = Integer.parseInt(token);
                                if (!mids.contains(id)) {
                                    mids.add(id);
                                }
                            }
                        }
                        break;
                    }
                }
            }
            List<BackMenu> backMenus = new ArrayList<>();
            for(Integer mid : mids){
                for(BackMenu menu : libs){
                    if(mid.equals(menu.getId())){
                        backMenus.add(menu);
                        break;
                    }
                }
            }
            libs = backMenus;
        }

        List<BackMenu> tree = new ArrayList<>();
        for (BackMenu menu : libs) {
            if (menu.getParentId() == null) {
                tree.add(menu);
                append(libs, menu);
            }
        }
        return tree;
    }

    private void append(List<BackMenu> libs, BackMenu parent) {
        parent.setChilds(new ArrayList<>());
        for (BackMenu menu : libs) {
            if (menu.getParentId() == parent.getId()) {
                parent.getChilds().add(menu);
                append(libs, menu);
            }
        }
        if (!parent.getChilds().isEmpty()) {
            Collections.sort(parent.getChilds(), new Comparator<BackMenu>() {
                @Override
                public int compare(BackMenu o1, BackMenu o2) {
                    return o1.getListOrder() - o2.getListOrder();
                }
            });
        }
    }

    @Transactional(readOnly = true)
    public BackMenu getById(Integer id) {
        return backMenuMapper.selectByPrimaryKey(id);
    }

    /**
     * 插入和更新
     * 更新菜单时，若包含子菜单，且depth和path改变，其子菜单也要相应改变
     *
     * @param backMenu
     */
    @Transactional
    public void save(BackMenu backMenu) {
        // 设置路径和深度
        if (backMenu.getParentId() == 0) {
            backMenu.setParentId(null);
            backMenu.setPath("");
            backMenu.setDepth((byte) 1);
        } else {
            BackMenu parent = backMenuMapper.selectByPrimaryKey(backMenu.getParentId());
            if (parent.getDepth() >= 4) { // 菜单最大深度为4
                return;
            }
            backMenu.setPath(parent.getPath() + parent.getId() + ",");
            backMenu.setDepth((byte) (parent.getDepth() + 1));
        }
        // 插入和更新
        if (backMenu.getId() == null) {
            backMenu.setListOrder(0);
            backMenuMapper.insert(backMenu);
        } else {
            List<BackMenu> backMenus = getChildForList(backMenu.getId());
            if (backMenus.isEmpty()) {
                backMenuMapper.updateByPrimaryKeySelective(backMenu);
            } else {
                BackMenu parent = backMenuMapper.selectByPrimaryKey(backMenu.getId()); // 更新前的菜单
                int detDepth = backMenu.getDepth() - parent.getDepth();                // 深度更改量
                String prePath = parent.getPath();       // 原路径
                String path = backMenu.getPath();        // 现路径
                // 深度和路径改变，需要改变所有子菜单
                if (detDepth != 0 || !prePath.equals(path)) {
                    for (int i = 0; i < backMenus.size(); i++) {
                        BackMenu menu = backMenus.get(i);
                        menu.setDepth((byte) (menu.getDepth() + detDepth));
                        menu.setPath(menu.getPath().replace(prePath, path));
                    }
                    backMenuMapper.batchUpdatePathAndDepth(backMenus);
                }
                backMenuMapper.updateByPrimaryKeySelective(backMenu);
            }
        }
    }

    /**
     * 更新排序
     *
     * @param id
     * @param listOrder
     */
    @Transactional
    public void updateListOrder(Integer[] id, Integer[] listOrder) {
        if (id != null) {
            List<BackMenu> backMenus = new ArrayList<>();
            for (int i = 0; i < id.length; i++) {
                BackMenu backMenu = new BackMenu();
                backMenu.setId(id[i]);
                backMenu.setListOrder(listOrder[i]);
                backMenus.add(backMenu);
            }
            backMenuMapper.batchUpdateListOrder(backMenus);
        }
    }

    /**
     * 删除菜单，其所有子菜单将一并删除
     *
     * @param id
     */
    @Transactional
    public void delete(Integer id) {
        List<Integer> ids = new ArrayList<>();
        ids.add(id);
        List<BackMenu> backMenus = getChildForList(id);
        for (BackMenu backMenu : backMenus) {
            ids.add(backMenu.getId());
        }
        BackMenuExample example = new BackMenuExample();
        BackMenuExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        backMenuMapper.deleteByExample(example);
    }
}
