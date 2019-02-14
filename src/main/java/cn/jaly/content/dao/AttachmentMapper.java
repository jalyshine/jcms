package cn.jaly.content.dao;

import cn.jaly.content.entity.Attachment;
import cn.jaly.content.entity.AttachmentExample;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentMapper {
    long countByExample(AttachmentExample example);

    int deleteByExample(AttachmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Attachment record);

    int insertSelective(Attachment record);

    List<Attachment> selectByExample(AttachmentExample example);

    Attachment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Attachment record, @Param("example") AttachmentExample example);

    int updateByExample(@Param("record") Attachment record, @Param("example") AttachmentExample example);

    int updateByPrimaryKeySelective(Attachment record);

    int updateByPrimaryKey(Attachment record);

    // 自定义
    List<Attachment> queryForList(@Param("name") String name,
                                  @Param("startTime") Date startTime,
                                  @Param("endTime") Date endTime,
                                  @Param("type") String type,
                                  @Param("siteId") Integer siteId,
                                  @Param("order") String order);

    void batchInsert(@Param("records") List<Attachment> attachments);
}