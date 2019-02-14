package cn.jaly.content.dao;

import cn.jaly.content.entity.Download;
import cn.jaly.content.entity.DownloadExample;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DownloadMapper {
    long countByExample(DownloadExample example);

    int deleteByExample(DownloadExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Download record);

    int insertSelective(Download record);

    List<Download> selectByExample(DownloadExample example);

    Download selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Download record, @Param("example") DownloadExample example);

    int updateByExample(@Param("record") Download record, @Param("example") DownloadExample example);

    int updateByPrimaryKeySelective(Download record);

    int updateByPrimaryKey(Download record);

    // 自定义
    List<Download> queryForList(@Param("keyword") String keyword,
                                @Param("startTime") Date startTime,
                                @Param("endTime") Date endTime,
                                @Param("categoryId") Integer categoryId,
                                @Param("status") Byte status,
                                @Param("order") String order);

    Download selectByPrimaryKeyWithData(Integer id);
}