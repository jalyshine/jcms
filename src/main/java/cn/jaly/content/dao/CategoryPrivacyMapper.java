package cn.jaly.content.dao;

import cn.jaly.content.entity.CategoryPrivacy;
import cn.jaly.content.entity.CategoryPrivacyExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryPrivacyMapper {
    long countByExample(CategoryPrivacyExample example);

    int deleteByExample(CategoryPrivacyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CategoryPrivacy record);

    int insertSelective(CategoryPrivacy record);

    List<CategoryPrivacy> selectByExample(CategoryPrivacyExample example);

    CategoryPrivacy selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CategoryPrivacy record, @Param("example") CategoryPrivacyExample example);

    int updateByExample(@Param("record") CategoryPrivacy record, @Param("example") CategoryPrivacyExample example);

    int updateByPrimaryKeySelective(CategoryPrivacy record);

    int updateByPrimaryKey(CategoryPrivacy record);

    // 自定义
    void batchInsert(@Param("records") List<CategoryPrivacy> categoryPrivacies);
}