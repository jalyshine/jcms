package cn.jaly.comment.service;

import cn.jaly.comment.dao.CommentDataMapper;
import cn.jaly.comment.dao.CommentMapper;
import cn.jaly.comment.entity.Comment;
import cn.jaly.comment.entity.CommentData;
import cn.jaly.comment.entity.CommentDataExample;
import cn.jaly.comment.entity.CommentExample;
import cn.jaly.utils.common.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommentDataMapper commentDataMapper;

    @Transactional(readOnly = true)
    public List<Comment> queryForList(Integer siteId, String keyword,
                                      String stm, String edm, Byte status, String order){
        Date startTime = DateTimeUtils.parseSimple(stm);
        Date endTime = DateTimeUtils.parseSimple(edm);
        if(keyword != null && !"".equals(keyword)){
            keyword = "%" + keyword + "%";
        }
        if(order == null || "".equals(order)){
            order = "update_time desc";
        }
        return commentMapper.queryForList(siteId, keyword, startTime, endTime, status, order);
    }

    @Transactional
    public void save(Comment comment){
        CommentData commentData = comment.getCommentData();
        if(comment.getId() == null){
            commentMapper.insert(comment);
            commentData.setCommentId(comment.getId());
            commentDataMapper.insert(commentData);
        } else {
            commentMapper.updateByPrimaryKeySelective(comment);
            commentDataMapper.updateByPrimaryKeySelective(commentData);
        }
    }

    @Transactional
    public void delete(Integer id){
        commentDataMapper.deleteByPrimaryKey(id);
        commentMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    public void delete(Integer[] ids){
        List<Integer> idList = Arrays.asList(ids);
        CommentDataExample example1 = new CommentDataExample();
        CommentDataExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andCommentIdIn(idList);
        commentDataMapper.deleteByExample(example1);

        CommentExample example2 = new CommentExample();
        CommentExample.Criteria criteria2 = example2.createCriteria();
        criteria2.andIdIn(idList);
        commentMapper.deleteByExample(example2);
    }
}
