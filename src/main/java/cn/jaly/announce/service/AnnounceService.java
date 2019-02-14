package cn.jaly.announce.service;

import cn.jaly.announce.dao.AnnounceMapper;
import cn.jaly.announce.entity.Announce;
import cn.jaly.announce.entity.AnnounceExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AnnounceService {

    @Autowired
    private AnnounceMapper announceMapper;

    @Transactional(readOnly = true)
    public List<Announce> queryForList(String keyword, Integer siteId, String order) {
        AnnounceExample example = new AnnounceExample();
        if (order == null || "".equals(order)) {
            order = "update_time desc";
        }
        example.setOrderByClause(order);
        AnnounceExample.Criteria criteria = example.createCriteria();
        criteria.andSiteIdEqualTo(siteId);
        if (keyword != null && !"".equals(keyword)) {
            criteria.andTitleLike("%" + keyword + "%");
        }
        return announceMapper.selectByExample(example);
    }


    @Transactional(readOnly = true)
    public Announce getById(Integer id) {
        return announceMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public void save(Announce announce){
        announce.setUpdateTime(new Date());
        if(announce.getId() == null){
            announce.setHits(0);
            announceMapper.insert(announce);
        } else {
            announceMapper.updateByPrimaryKeySelective(announce);
        }
    }

    @Transactional
    public void delete(Integer id) {
        announceMapper.deleteByPrimaryKey(id);
    }
}
