package cn.jaly.admin.dao;

import cn.jaly.admin.entity.Module;
import cn.jaly.admin.entity.ModuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ModuleMapper {
    long countByExample(ModuleExample example);

    int deleteByExample(ModuleExample example);

    int deleteByPrimaryKey(String module);

    int insert(Module record);

    int insertSelective(Module record);

    List<Module> selectByExampleWithBLOBs(ModuleExample example);

    List<Module> selectByExample(ModuleExample example);

    Module selectByPrimaryKey(String module);

    int updateByExampleSelective(@Param("record") Module record, @Param("example") ModuleExample example);

    int updateByExampleWithBLOBs(@Param("record") Module record, @Param("example") ModuleExample example);

    int updateByExample(@Param("record") Module record, @Param("example") ModuleExample example);

    int updateByPrimaryKeySelective(Module record);

    int updateByPrimaryKeyWithBLOBs(Module record);

    int updateByPrimaryKey(Module record);
}