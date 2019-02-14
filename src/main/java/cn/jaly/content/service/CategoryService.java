package cn.jaly.content.service;

import cn.jaly.content.dao.CategoryMapper;
import cn.jaly.content.dao.CategoryPrivacyMapper;
import cn.jaly.content.dao.ModelMapper;
import cn.jaly.content.entity.*;
import cn.jaly.utils.common.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryPrivacyMapper categoryPrivacyMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<Category> treeForListBySiteId(Integer siteId) {
        List<Category> tree = new ArrayList<>();
        List<Category> libs = categoryMapper.selectBySiteIdWithModel(siteId);
        appendToTree(libs, tree, null);
        return tree;
    }

    private void appendToTree(List<Category> libs, List<Category> tree, Category parent) {
        List<Category> temp = new ArrayList<>();
        Integer parentId = parent == null ? null : parent.getId();
        byte level = (byte) (parent == null? 1 : parent.getLevel() + 1);
        for (Category category : libs) {
            if (category.getParentId() == parentId) {
                category.setLevel(level);
                temp.add(category);
            }
        }
        temp.sort(new Comparator<Category>() {
            @Override
            public int compare(Category o1, Category o2) {
                return o1.getListOrder() - o2.getListOrder();
            }
        });
        for (Category category : temp) {
            tree.add(category);
            appendToTree(libs, tree, category);
        }
    }

    @Transactional(readOnly = true)
    public List<ContentMenu> getContentNav(Integer siteId, Integer roleId) {
        List<ContentMenu> tree = new ArrayList<>();
        List<Category> libs = categoryMapper.selectBySiteIdWithModel(siteId);
        if(roleId != 1){
            CategoryPrivacyExample example = new CategoryPrivacyExample();
            CategoryPrivacyExample.Criteria criteria = example.createCriteria();
            criteria.andAdminRoleIdEqualTo(roleId);
            List<CategoryPrivacy> privacies = categoryPrivacyMapper.selectByExample(example);
            List<Category> categories = new ArrayList<>();
            for(Category category : libs){
                for(CategoryPrivacy privacy : privacies){
                    if(category.getId() == privacy.getCategoryId()){
                        categories.add(category);
                        break;
                    }
                }
            }
            libs = categories;
        }
        for (Category category : libs) {
            if (category.getParentId() == null && category.getType() != Constant.CATEGORY_TYPE_LINK) {
                ContentMenu contentMenu = new ContentMenu(category);
                tree.add(contentMenu);
                append(libs, contentMenu);
            }
        }
        return tree;
    }

    private void append(List<Category> libs, ContentMenu parent) {
        for (Category category : libs) {
            if (category.getParentId() == parent.getId() && category.getType() != Constant.CATEGORY_TYPE_LINK) {
                ContentMenu contentMenu = new ContentMenu(category);
                parent.getChildren().add(contentMenu);
                append(libs, contentMenu);
            }
        }
        if (!parent.getChildren().isEmpty()) {
            Collections.sort(parent.getChildren(), new Comparator<ContentMenu>() {
                @Override
                public int compare(ContentMenu o1, ContentMenu o2) {
                    return o1.getListOrder() - o2.getListOrder();
                }
            });
        }
    }

    /**
     * 按照ModelId对Category进行分类，用于推荐位input页面
     * @param models
     * @return
     */
    @Transactional(readOnly = true)
    public Map<Integer, List<Category>> mapByModel(List<Model> models) {
        if(models == null || models.isEmpty()){
            return null;
        }
        List<Integer> modelIds = new ArrayList<>();
        for(Model model : models){
            modelIds.add(model.getId());
        }

        CategoryExample example = new CategoryExample();
        example.setOrderByClause("list_order asc");
        CategoryExample.Criteria criteria = example.createCriteria();
        criteria.andModelIdIn(modelIds);
        List<Category> categories = categoryMapper.selectByExample(example);

        Map<Integer, List<Category>> map = new HashMap<>();
        for(Category category : categories){
            Integer modelId = category.getModelId();
            if(!map.containsKey(modelId)){
                map.put(modelId, new ArrayList<>());
            }
            map.get(modelId).add(category);
        }
        return map;
    }

    @Transactional(readOnly = true)
    public Category getById(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    public Category getByIdWithWorkFlow(Integer id) {
        return categoryMapper.selectByPrimaryKeyWithWorkFlow(id);
    }

    @Transactional
    public void save(Category category) {
        if (category.getId() == null) {
            category.setListOrder(0);
            category.setHits(0);
            categoryMapper.insert(category);
        } else {
            categoryMapper.updateByPrimaryKeySelective(category);
        }
    }

    @Transactional
    public void sort(Integer[] id, Integer[] order) {
        if (id != null) {
            for (int i = 0; i < id.length; i++) {
                Category category = new Category();
                category.setId(id[i]);
                category.setListOrder(order[i]);
                categoryMapper.updateByPrimaryKeySelective(category);
            }
        }
    }

    @Transactional
    public void delete(Integer id) {
        categoryMapper.deleteByPrimaryKey(id);
    }
}
