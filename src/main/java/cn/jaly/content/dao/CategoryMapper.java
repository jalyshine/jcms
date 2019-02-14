package cn.jaly.content.dao;

import cn.jaly.content.entity.Category;
import cn.jaly.content.entity.CategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryMapper {
    long countByExample(CategoryExample example);

    int deleteByExample(CategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    List<Category> selectByExample(CategoryExample example);

    Category selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByExample(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    // 自定义
    Category selectByPrimaryKeyWithWorkFlow(Integer id);

    Category selectByPrimaryKeyWithModel(Integer id);

    List<Category> selectBySiteIdWithModel(Integer siteId);

    List<Category> selectByIdWithModelAndSite(@Param("ids") Integer[] ids);
}