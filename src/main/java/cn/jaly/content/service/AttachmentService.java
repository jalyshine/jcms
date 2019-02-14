package cn.jaly.content.service;

import cn.jaly.admin.dao.SiteMapper;
import cn.jaly.admin.entity.Site;
import cn.jaly.content.dao.AttachIndexMapper;
import cn.jaly.content.dao.AttachmentMapper;
import cn.jaly.content.entity.AttachIndexExample;
import cn.jaly.content.entity.Attachment;
import cn.jaly.content.entity.AttachmentExample;
import cn.jaly.utils.common.DateTimeUtils;
import cn.jaly.utils.explorer.HttpUtils;
import cn.jaly.utils.explorer.UploadFile;
import cn.jaly.utils.explorer.UploadResult;
import cn.jaly.utils.explorer.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class AttachmentService {

    @Autowired
    private AttachmentMapper attachmentMapper;

    @Autowired
    private AttachIndexMapper attachIndexMapper;
    @Autowired
    private SiteMapper siteMapper;

    @Transactional(readOnly = true)
    public List<Attachment> queryForList(Integer siteId, String keyword,
                                         String stm, String edm, String type, String order){
        Date startTime = DateTimeUtils.parseSimple(stm);
        Date endTime = DateTimeUtils.parseSimple(edm);
        if(keyword != null && !"".equals(keyword)){
            keyword = "%" + keyword + "%";
        }
        if(order == null || "".equals(order)){
            order = "upload_time desc";
        }
        return attachmentMapper.queryForList(keyword, startTime, endTime, type, siteId, order);
    }

    @Transactional
    public void save(Attachment attachment) {
        if (attachment.getId() == null) {
            attachmentMapper.insert(attachment);
        } else {
            attachmentMapper.updateByPrimaryKeySelective(attachment);
        }
    }

    @Transactional
    public void save(List<Attachment> attachments) {
        attachmentMapper.batchInsert(attachments);
    }

    /**
     * 批量添加上传附件
     * @param result
     */
    @Transactional
    public void save(UploadResult result) {
        List<Attachment> attachments = new ArrayList<>();
        List<UploadFile> uploadFiles = result.getUploadFiles();
        for (UploadFile file : uploadFiles) {
            Attachment attachment = new Attachment();
            attachment.setName(file.getName());
            attachment.setPath(file.getUrl());
            attachment.setSize(file.getSize());
            attachment.setType(result.getType());
            attachment.setUploadTime(new Date());
            attachment.setSiteId(result.getSiteId());
            attachment.setUserId(result.getUserId());
            attachment.setIsAdmin(result.getAdmin());
            attachment.setUploadIp(result.getUploadIp());
            attachment.setAuthCode(file.getAuthCode());
            attachment.setDownloads(0);
            attachments.add(attachment);
        }
        attachmentMapper.batchInsert(attachments);
    }

    /**
     * 获取单个附件的地址
     * @param id
     * @return
     */
    public String getAttachmentFilePath(Integer id){
        Attachment attachment = attachmentMapper.selectByPrimaryKey(id);
        return attachment.getPath();
    }

    /**
     * 获取多个附件的地址
     * @param ids
     * @return
     */
    public List<String> getAttachmentFilePath(Integer[] ids){
        List<Integer> idList = Arrays.asList(ids);
        AttachmentExample example = new AttachmentExample();
        AttachmentExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(idList);
        List<Attachment> attachments = attachmentMapper.selectByExample(example);
        List<String> filePaths = new ArrayList<>();
        for(Attachment attachment : attachments){
            filePaths.add(attachment.getPath());
        }
        return filePaths;
    }

    @Transactional
    public void delete(Integer id){
        attachmentMapper.deleteByPrimaryKey(id);
        // 附件删除，其相应的关联记录也删除
        AttachIndexExample example = new AttachIndexExample();
        AttachIndexExample.Criteria criteria = example.createCriteria();
        criteria.andAttachmentIdEqualTo(id);
        attachIndexMapper.deleteByExample(example);
    }

    /**
     * 删除附件表记录
     * @param ids
     */
    @Transactional
    public void delete(Integer[] ids){
        List<Integer> idList = Arrays.asList(ids);
        AttachmentExample example = new AttachmentExample();
        AttachmentExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(idList);
        // 删除附件表记录
        attachmentMapper.deleteByExample(example);
        // 删除附件关系记录
        AttachIndexExample example1 = new AttachIndexExample();
        AttachIndexExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andAttachmentIdIn(idList);
        attachIndexMapper.deleteByExample(example1);
    }

    /**
     * 删除附件文件
     * @param siteId
     * @param contextPath
     * @param filePaths
     * @throws IOException
     */
    @Transactional
    public void deleteFile(Integer siteId, String contextPath, List<String> filePaths) throws IOException {
        if(siteId == 1){
            for (String filePath : filePaths) {
                UploadUtils.deleteAttachment(contextPath + filePath);
            }
        } else {
            Site site = siteMapper.selectByPrimaryKey(siteId);
            String userName = site.getUserName();
            String password = site.getPassword();
            StringBuilder sb = new StringBuilder();
            sb.append("account=").append(userName).append("&password=").append(password);
            for(String filePath : filePaths){
                sb.append("&filePath=").append(filePath);
            }

            String url = site.getDomain() + "/delAtt";
            HttpUtils.httpPost(url, sb.toString());
        }
    }

}
