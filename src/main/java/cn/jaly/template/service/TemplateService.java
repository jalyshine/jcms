package cn.jaly.template.service;

import cn.jaly.admin.dao.SiteMapper;
import cn.jaly.admin.entity.Site;
import cn.jaly.template.dao.TemplateMapper;
import cn.jaly.template.entity.Template;
import cn.jaly.template.entity.TemplateExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TemplateService {

    @Autowired
    private TemplateMapper templateMapper;

    @Autowired
    private SiteMapper siteMapper;

    @Transactional(readOnly = true)
    public List<Template> queryForList(String keyword, Integer colorId, Integer typeId, String order) {
        if(order == null || order.isEmpty()){
            order = "update_time desc";
        }
        if(keyword != null){
            keyword = "%" + keyword + "%";
        }
        return templateMapper.queryForList(keyword, colorId, typeId, order);
    }

    @Transactional(readOnly = true)
    public List<Template> getAll(){
        return templateMapper.selectByExample(null);
    }

    @Transactional(readOnly = true)
    public Integer getIdByTitle(String title) {
        TemplateExample example = new TemplateExample();
        TemplateExample.Criteria criteria = example.createCriteria();
        criteria.andTitleEqualTo(title);
        List<Template> templates = templateMapper.selectByExample(example);
        if(templates == null || templates.isEmpty()){
            return null;
        }
        return templates.get(0).getId();
    }

    @Transactional(readOnly = true)
    public Template getById(Integer id){
        return templateMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public void save(Template template){
        template.setUpdateTime(new Date());
        if(template.getId() == null){
            templateMapper.insert(template);
        } else {
            templateMapper.updateByPrimaryKeySelective(template);
        }
    }

    @Transactional
    public void delete(Integer id){
        templateMapper.deleteByPrimaryKey(id);
    }

    /**
     * 按条件查找模板文件
     * @param contextPath 根目录
     * @param siteId      站点ID
     * @param dirName     文件所在目录名称
     * @param preFix      文件名前缀
     * @return
     */
    @Transactional(readOnly = true)
    public List<String> queryFileForList(String contextPath, Integer siteId, String dirName, String preFix){
        Site site = siteMapper.selectByPrimaryKey(siteId);
        String uiStyle = site.getUiStyle();
        String dirPath = contextPath + "template/" + uiStyle + "/" + dirName;
        File dirFile = new File(dirPath);
        if(!dirFile.exists()){
            return null;
        }
        String[] fileListNames = dirFile.list();
        List<String> result = new ArrayList<>();
        for(String name : fileListNames){
            if(name.startsWith(preFix)){
                result.add(name);
            }
        }
        return result;
    }
}
