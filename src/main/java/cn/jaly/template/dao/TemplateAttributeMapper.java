package cn.jaly.template.dao;

import cn.jaly.template.entity.TemplateAttribute;
import cn.jaly.template.entity.TemplateAttributeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateAttributeMapper {
    long countByExample(TemplateAttributeExample example);

    int deleteByExample(TemplateAttributeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TemplateAttribute record);

    int insertSelective(TemplateAttribute record);

    List<TemplateAttribute> selectByExample(TemplateAttributeExample example);

    TemplateAttribute selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TemplateAttribute record, @Param("example") TemplateAttributeExample example);

    int updateByExample(@Param("record") TemplateAttribute record, @Param("example") TemplateAttributeExample example);

    int updateByPrimaryKeySelective(TemplateAttribute record);

    int updateByPrimaryKey(TemplateAttribute record);
}