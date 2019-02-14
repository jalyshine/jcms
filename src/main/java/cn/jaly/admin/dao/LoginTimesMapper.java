package cn.jaly.admin.dao;

import cn.jaly.admin.entity.LoginTimes;
import cn.jaly.admin.entity.LoginTimesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginTimesMapper {
    long countByExample(LoginTimesExample example);

    int deleteByExample(LoginTimesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LoginTimes record);

    int insertSelective(LoginTimes record);

    List<LoginTimes> selectByExample(LoginTimesExample example);

    LoginTimes selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LoginTimes record, @Param("example") LoginTimesExample example);

    int updateByExample(@Param("record") LoginTimes record, @Param("example") LoginTimesExample example);

    int updateByPrimaryKeySelective(LoginTimes record);

    int updateByPrimaryKey(LoginTimes record);
}