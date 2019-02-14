package cn.jaly.content.dao;

import cn.jaly.content.entity.Model;
import cn.jaly.content.entity.ModelExample;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelMapper {
    long countByExample(ModelExample example);

    int deleteByExample(ModelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Model record);

    int insertSelective(Model record);

    List<Model> selectByExample(ModelExample example);

    Model selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Model record, @Param("example") ModelExample example);

    int updateByExample(@Param("record") Model record, @Param("example") ModelExample example);

    int updateByPrimaryKeySelective(Model record);

    int updateByPrimaryKey(Model record);

    // 自定义
    void updateDataCount(Model model);

    List<Map<String, Object>> queryContentForList(@Param("tableName") String tableName,
                                                  @Param("keyword") String keyword,
                                                  @Param("startTime") Date startTime,
                                                  @Param("endTime") Date endTime,
                                                  @Param("categoryId") Integer categoryId);

    /**
     * 用于生成静态网页
     * @param tableName    表名（不为null）
     * @param categoryIds  栏目ID （不为null）
     * @param count        最新发布的条数
     * @param startTime    发布起始时间
     * @param endTime      发布截止时间
     * @param fromId       内容起始ID
     * @param toId         内容截止ID
     * @return
     */
    List<Map<String, Object>> queryForPublish(@Param("tableName") String tableName,
                                              @Param("categoryIds") List<Integer> categoryIds,
                                              @Param("count") Integer count,
                                              @Param("startTime") Date startTime,
                                              @Param("endTime") Date endTime,
                                              @Param("fromId") Integer fromId,
                                              @Param("toId") Integer toId);

    /**
     * 更新内容点击次数
     * @param tableName
     * @param id
     * @param times
     */
    void updateContentHit(@Param("tableName") String tableName, @Param("id") Integer id, @Param("times") Integer times);

    /**
     * 获取内容点击次数
     * @param tableName
     * @param id
     * @return
     */
    Integer queryContentHit(@Param("tableName") String tableName, @Param("id") Integer id);
}