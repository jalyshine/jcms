package cn.jaly.content.service;

import java.util.Date;
import java.util.List;

import cn.jaly.content.dao.SinglePageMapper;
import cn.jaly.content.entity.SinglePage;
import cn.jaly.content.entity.SinglePageExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SinglePageService {

    @Autowired
    private SinglePageMapper singlePageMapper;

    @Transactional(readOnly = true)
    public SinglePage getById(Integer id) {
        return singlePageMapper.selectByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    public SinglePage getByCategoryId(Integer categoryId) {
        SinglePageExample example = new SinglePageExample();
        SinglePageExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<SinglePage> singlePages = singlePageMapper.selectByExampleWithBLOBs(example);
        if (singlePages == null || singlePages.isEmpty()) {
            return null;
        }
        return singlePages.get(0);
    }

    @Transactional
    public void save(SinglePage singlePage) {
        singlePage.setUpdateTime(new Date());
        if (singlePage.getId() == null) {
            singlePageMapper.insert(singlePage);
        } else {
            singlePageMapper.updateByPrimaryKeyWithBLOBs(singlePage);
        }
    }

}
