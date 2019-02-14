package cn.jaly.admin.dao;

import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

@Repository
public interface SqlCommandMapper {

    List<LinkedHashMap<String, Object>> selectCommandWithOrder(String sql);

    void updateCommand(String sql);

    void insertCommand(String sql);

    void deleteCommand(String sql);

}
