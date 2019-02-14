package cn.jaly.admin.dao;

import cn.jaly.admin.entity.Linkage;
import cn.jaly.admin.entity.LinkageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkageMapper {
    long countByExample(LinkageExample example);

    int deleteByExample(LinkageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Linkage record);

    int insertSelective(Linkage record);

    List<Linkage> selectByExample(LinkageExample example);

    Linkage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Linkage record, @Param("example") LinkageExample example);

    int updateByExample(@Param("record") Linkage record, @Param("example") LinkageExample example);

    int updateByPrimaryKeySelective(Linkage record);

    int updateByPrimaryKey(Linkage record);
}