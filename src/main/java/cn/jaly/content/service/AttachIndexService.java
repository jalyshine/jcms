package cn.jaly.content.service;

import cn.jaly.content.dao.AttachIndexMapper;
import cn.jaly.content.dao.AttachmentMapper;
import cn.jaly.content.entity.AttachIndex;
import cn.jaly.content.entity.AttachIndexExample;
import cn.jaly.content.entity.Attachment;
import cn.jaly.content.entity.AttachmentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttachIndexService {

    @Autowired
    private AttachIndexMapper attachIndexMapper;

    @Autowired
    private AttachmentMapper attachmentMapper;

    /**
     * 根据附件地址保存附件连接
     *
     * @param path
     * @param attachIndex
     */
    @Transactional
    public void save(String path, AttachIndex attachIndex) {
        if (attachIndex.getId() == null) {
            AttachmentExample example = new AttachmentExample();
            AttachmentExample.Criteria criteria = example.createCriteria();
            criteria.andPathEqualTo(path);
            List<Attachment> attachments = attachmentMapper.selectByExample(example);
            if (attachments != null && !attachments.isEmpty()) {
                Integer attachmentId = attachments.get(0).getId();
                // 删除无用记录
                AttachIndexExample attachIndexExample = new AttachIndexExample();
                AttachIndexExample.Criteria attachIndexExampleCriteria = attachIndexExample.createCriteria();
                attachIndexExampleCriteria.andHostEqualTo(attachIndex.getHost());
                attachIndexExampleCriteria.andAttachmentIdNotEqualTo(attachmentId);
                attachIndexMapper.deleteByExample(attachIndexExample);
                // 添加新记录
                AttachIndex index = new AttachIndex();
                index.setAttachmentId(attachments.get(0).getId());
                index.setHost(attachIndex.getHost());
                index.setCategoryId(attachIndex.getCategoryId());
                index.setModule(attachIndex.getModule());
                attachIndexMapper.insert(index);
            }
        }
    }

    @Transactional
    public void save(List<String> paths, AttachIndex attachIndex) {
        if (attachIndex.getId() == null) {
            // 根据路径，查找所有附件的ID
            AttachmentExample example = new AttachmentExample();
            AttachmentExample.Criteria criteria = example.createCriteria();
            criteria.andPathIn(paths);
            List<Attachment> attachments = attachmentMapper.selectByExample(example);
            if (attachments != null && !attachments.isEmpty()) {
                // 获取所有附件ID
                List<Integer> attachmentIds = new ArrayList<>();
                for (Attachment attachment : attachments) {
                    attachmentIds.add(attachment.getId());
                }
                // 删除无用记录
                AttachIndexExample attachIndexExample = new AttachIndexExample();
                AttachIndexExample.Criteria attachIndexExampleCriteria = attachIndexExample.createCriteria();
                attachIndexExampleCriteria.andHostEqualTo(attachIndex.getHost());
                attachIndexExampleCriteria.andAttachmentIdNotIn(attachmentIds);
                attachIndexMapper.deleteByExample(attachIndexExample);
                // 插入新记录
                List<AttachIndex> attachIndices = new ArrayList<>();
                for (Attachment attachment : attachments) {
                    AttachIndex index = new AttachIndex();
                    index.setAttachmentId(attachment.getId());
                    index.setHost(attachIndex.getHost());
                    index.setCategoryId(attachIndex.getCategoryId());
                    index.setModule(attachIndex.getModule());
                    attachIndices.add(index);
                }
                attachIndexMapper.batchInsert(attachIndices);
            }
        }
    }
}