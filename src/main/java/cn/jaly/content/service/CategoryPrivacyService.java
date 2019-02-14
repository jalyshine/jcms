package cn.jaly.content.service;

import cn.jaly.content.dao.CategoryPrivacyMapper;
import cn.jaly.content.entity.Category;
import cn.jaly.content.entity.CategoryPrivacy;
import cn.jaly.content.entity.CategoryPrivacyExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CategoryPrivacyService {

    @Autowired
    private CategoryPrivacyMapper categoryPrivacyMapper;

//    /**
//     * 判断栏目权限是否存在
//     * @param roleId
//     * @param categoryId
//     * @param action
//     * @return
//     */
//    @Transactional(readOnly = true)
//    public boolean isCategoryPrivacyExist(Integer roleId, Integer categoryId, String action){
//        CategoryPrivacyExample example = new CategoryPrivacyExample();
//        CategoryPrivacyExample.Criteria criteria = example.createCriteria();
//        criteria.andAdminRoleIdEqualTo(roleId);
//        criteria.andCategoryIdEqualTo(categoryId);
//        criteria.andActionEqualTo(action);
//        long count = categoryPrivacyMapper.countByExample(example);
//        return count == 1;
//    }

    @Transactional(readOnly = true)
    public List<Category> initCategoryList(Integer roleId, List<Category> categories){
        CategoryPrivacyExample example = new CategoryPrivacyExample();
        CategoryPrivacyExample.Criteria criteria = example.createCriteria();
        criteria.andAdminRoleIdEqualTo(roleId);
        List<CategoryPrivacy> privacies = categoryPrivacyMapper.selectByExample(example);

        if(privacies != null && !privacies.isEmpty()){
            for(Category category : categories){
                StringBuilder sb = new StringBuilder();
                for(CategoryPrivacy privacy : privacies){
                    if(category.getId() == privacy.getCategoryId()){
                        sb.append(privacy.getAction()).append(",");
                    }
                }
                category.setPrivacyString(sb.toString());
            }
        }
        return categories;
    }

    @Transactional
    public void save(Integer roleId, Integer[] cids, String[] actions){
        List<CategoryPrivacy> privacies = new ArrayList<>();
        for(int k=0; k<cids.length; k++){
            if(!actions[k].contains(",")){
                continue;
            }
            String[] tokens = actions[k].split("\\,");
            for(String token : tokens){
                if("".equals(token)){
                    continue;
                }
                CategoryPrivacy privacy = new CategoryPrivacy();
                privacy.setAdminRoleId(roleId);
                privacy.setCategoryId(cids[k]);
                privacy.setAction(token);
                privacies.add(privacy);
            }
        }
        // 删除原来的
        CategoryPrivacyExample example = new CategoryPrivacyExample();
        CategoryPrivacyExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdIn(Arrays.asList(cids));
        criteria.andAdminRoleIdEqualTo(roleId);
        categoryPrivacyMapper.deleteByExample(example);
        // 批量插入新的权限
        categoryPrivacyMapper.batchInsert(privacies);
    }
}
