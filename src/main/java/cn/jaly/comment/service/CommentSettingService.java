package cn.jaly.comment.service;

import cn.jaly.comment.dao.CommentSettingMapper;
import cn.jaly.comment.entity.CommentSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentSettingService {

    @Autowired
    private CommentSettingMapper commentSettingMapper;

    @Transactional(readOnly = true)
    public CommentSetting getBySiteId(Integer siteId){
        return commentSettingMapper.selectByPrimaryKey(siteId);
    }

    @Transactional
    public void save(CommentSetting commentSetting){
        CommentSetting record = commentSettingMapper.selectByPrimaryKey(commentSetting.getSiteId());
        if(record == null){
            commentSettingMapper.insert(commentSetting);
        } else {
            commentSettingMapper.updateByPrimaryKeySelective(commentSetting);
        }
    }
}
