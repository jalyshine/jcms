package cn.jaly.content.dao;

import cn.jaly.content.entity.DownloadData;
import cn.jaly.content.entity.DownloadDataExample;
import cn.jaly.content.entity.DownloadDataWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DownloadDataMapper {
    long countByExample(DownloadDataExample example);

    int deleteByExample(DownloadDataExample example);

    int deleteByPrimaryKey(Integer downloadId);

    int insert(DownloadDataWithBLOBs record);

    int insertSelective(DownloadDataWithBLOBs record);

    List<DownloadDataWithBLOBs> selectByExampleWithBLOBs(DownloadDataExample example);

    List<DownloadData> selectByExample(DownloadDataExample example);

    DownloadDataWithBLOBs selectByPrimaryKey(Integer downloadId);

    int updateByExampleSelective(@Param("record") DownloadDataWithBLOBs record, @Param("example") DownloadDataExample example);

    int updateByExampleWithBLOBs(@Param("record") DownloadDataWithBLOBs record, @Param("example") DownloadDataExample example);

    int updateByExample(@Param("record") DownloadData record, @Param("example") DownloadDataExample example);

    int updateByPrimaryKeySelective(DownloadDataWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(DownloadDataWithBLOBs record);

    int updateByPrimaryKey(DownloadData record);
}