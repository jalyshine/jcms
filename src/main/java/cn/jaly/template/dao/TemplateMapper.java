package cn.jaly.template.dao;

import cn.jaly.template.entity.Template;
import cn.jaly.template.entity.TemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateMapper {
    long countByExample(TemplateExample example);

    int deleteByExample(TemplateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Template record);

    int insertSelective(Template record);

    List<Template> selectByExample(TemplateExample example);

    Template selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Template record, @Param("example") TemplateExample example);

    int updateByExample(@Param("record") Template record, @Param("example") TemplateExample example);

    int updateByPrimaryKeySelective(Template record);

    int updateByPrimaryKey(Template record);

    // 自定义
    List<Template> queryForList(@Param("kwd") String keyword,
                                @Param("cid") Integer colorId,
                                @Param("tid") Integer typeId,
                                @Param("odr") String order);
}