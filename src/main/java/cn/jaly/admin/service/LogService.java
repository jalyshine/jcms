package cn.jaly.admin.service;

import cn.jaly.admin.dao.LogsMapper;
import cn.jaly.admin.entity.Logs;
import cn.jaly.admin.entity.LogsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class LogService {

    @Autowired
    private LogsMapper logsMapper;

    @Transactional(readOnly = true)
    public List<Logs> getAll(){
        return logsMapper.selectByExampleWithAdminAndSite(null);
    }

    @Transactional
    public void save(Logs logs){
        logsMapper.insert(logs);
    }

    @Transactional
    public void delete(Integer[] ids){
        LogsExample example = new LogsExample();
        LogsExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        logsMapper.deleteByExample(example);
    }

}
