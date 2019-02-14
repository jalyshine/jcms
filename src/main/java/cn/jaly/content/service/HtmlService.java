package cn.jaly.content.service;

import cn.jaly.admin.dao.SiteMapper;
import cn.jaly.admin.entity.Site;
import cn.jaly.content.dao.CategoryMapper;
import cn.jaly.content.dao.ModelMapper;
import cn.jaly.content.entity.Category;
import cn.jaly.utils.common.DateTimeUtils;
import cn.jaly.utils.explorer.FileSystemUtils;
import cn.jaly.utils.explorer.HttpUtils;
import cn.jaly.utils.explorer.ZipUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 生成静态页面服务
 * 模板路径： /template/[styleName]/...
 * 静态页路径：/sites/[siteDirname]/...
 * 命名规则：
 * index.jsp?id=1                       =>  index.html
 * content/home_news.jsp?id=1&pn=2      =>  html/[category_dir]/[id]_[pn].html
 * content/show_news.jsp?id=1           =>  html/[category_dir]/[yyyy]/[MMdd]/[id].html
 * content/page_about.jsp?id=1          =>  html/[category_dir]/[id].html
 * special/home_science.jsp?id=1&pn=2   =>  html/[special_dir]/[id]_[pn].html
 * special/show_science.jsp?id=1        =>  html/[special_dir]/[yyyy]/[MMdd]/[id].html
 * announce/show.jsp?id=1               =>  html/announce/[id].html
 *
 * 更新过程：
 * 1 查询所有Category，并遍历之
 * 2 对于主站的Category，直接将生成静态页面保存到相应文件夹即可
 * 3 对于子站的Category，先将生成的静态页面保存到主站文件夹，并打包到ZIP，
 * 然后将此zip发送到子站，子站接收静态页面包后，解压到指定文件夹。
 * 最后删除中间的临时文件。
 */
@Service
public class HtmlService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private SiteMapper siteMapper;

    private SimpleDateFormat format1 = new SimpleDateFormat("yyyy");
    private SimpleDateFormat format2 = new SimpleDateFormat("MMdd");

    /**
     * 获取模板根路径 格式http://[host]/template/[uiStyle]/
     * @param site
     * @return
     */
    private String getTemplateRoot(Site site){
        String domain = siteMapper.selectByPrimaryKey(1).getDomain();
        if(!domain.endsWith("/")){
            domain += "/";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(domain).append("template/").append(site.getUiStyle()).append("/");
        return sb.toString();
    }

    /**
     * 获取内容地址
     * @param site
     * @param template
     * @param id
     * @param pageNumber
     * @return
     */
    private String getContentUrl(Site site, String template, Integer id, Integer pageNumber){
        StringBuilder sb = new StringBuilder();
        sb.append(getTemplateRoot(site)).append("content/").append(template)
                .append("?id=").append(id).append("&auto=1");
        if(pageNumber != null){
            sb.append("&pn=").append(pageNumber);
        }
        return sb.toString();
    }

    /**
     * 获取html根目录 格式[project]/html/[siteDir]/
     * @param contextPath
     * @param site
     * @return
     */
    private String getHtmlRoot(String contextPath, Site site){
        StringBuilder sb = new StringBuilder();
        sb.append(contextPath);
        if(!contextPath.endsWith("/") && !contextPath.endsWith("\\")){
            sb.append("/");
        }
        sb.append("html");
        String dirName = site.getDirName();
        if(!dirName.startsWith("/") && !dirName.startsWith("\\")){
            sb.append("/");
        }
        sb.append(dirName);
        if(!dirName.endsWith("/") && !dirName.endsWith("\\")){
            sb.append("/");
        }
        return sb.toString();
    }

    /**
     * 获取html路径
     * @param contextPath
     * @param site
     * @param categoryDir
     * @param publishTime
     * @return
     */
    private String getContentHtmlPath(String contextPath, Site site, String categoryDir, Date publishTime){
        StringBuilder sb = new StringBuilder();
        sb.append(getHtmlRoot(contextPath, site));
        if(categoryDir.startsWith("/") || categoryDir.endsWith("\\")){
            categoryDir = categoryDir.substring(0, categoryDir.length() - 1);
        }
        sb.append(categoryDir);
        if(publishTime == null){  // 栏目页
            if(categoryDir.endsWith("/") || categoryDir.endsWith("\\")){
                sb.deleteCharAt(sb.length() - 1);
            }
        } else {                  // 内容页
            if(!categoryDir.endsWith("/") && !categoryDir.endsWith("\\")){
                sb.append("/");
            }
            sb.append(format1.format(publishTime)).append("/").append(format2.format(publishTime));
        }
        return sb.toString();
    }

    /**
     * 更新站点首页
     * @param site             目标站点
     * @param contextPath      根目录地址
     * @throws IOException
     */
    @Transactional
    public void updateHomePage(Site site, String contextPath) throws IOException {
        String temp_url = getTemplateRoot(site) + "index.jsp?id=" + site.getId() + "&auto=1";
        String html_url = getHtmlRoot(contextPath, site);
        HttpUtils.staticHtml(temp_url, html_url, "index");
    }

    /**
     * 更新栏目页
     *
     * @param site        目标站点
     * @param contextPath 主站路径
     * @param categoryIds 栏目ID集合
     * @throws IOException
     */
    @Transactional
    public void updateCategory(Site site, String contextPath, Integer[] categoryIds) throws IOException {
        updateContent(site, false, contextPath, categoryIds,
                null, null, null, null, null);
    }

    /**
     * 更新内容页
     *
     * @param site        目标站点
     * @param contextPath 主站路径
     * @param categoryIds 栏目ID集合
     * @param count       只更新前count项
     * @param stm         与edm构成发布时间段，
     * @param edm         只更新在此时间段内的内容
     * @param fromId      与toId构成内容ID段
     * @param toId        只更新在此ID段内的内容
     * @throws IOException
     */
    @Transactional
    public void updateDetail(Site site, String contextPath, Integer[] categoryIds,
                                     Integer count, String stm, String edm, Integer fromId,
                                     Integer toId) throws IOException {
        updateContent(site, true, contextPath, categoryIds, count, stm, edm, fromId, toId);
    }

    private void updateContent(Site site, boolean isContentPage, String contextPath,
                                       Integer[] categoryIds, Integer count, String stm, String edm,
                                       Integer fromId, Integer toId) throws IOException {
        List<Category> categories = categoryMapper.selectByIdWithModelAndSite(categoryIds);
        // 发布内容页
        if (isContentPage) {
            Date startTime = DateTimeUtils.parseSimple(stm);
            Date endTime = DateTimeUtils.parseSimple(edm);
            // 按照表名，给categoryId分类
            Map<String, List<Integer>> categoryMap = new HashMap<>();
            Map<Integer, String> categoryDirNameMap = new HashMap<>();
            Map<Integer, String> showTemplateMap = new HashMap<>();
            for (Category category : categories) {
                String tableName = category.getModel().getMysqlTableName();
                Integer categoryId = category.getId();
                if (!categoryMap.containsKey(tableName)) {
                    categoryMap.put(tableName, new ArrayList<>());
                }
                categoryMap.get(tableName).add(categoryId);
                categoryDirNameMap.put(categoryId, category.getDirName());
                showTemplateMap.put(categoryId, category.getStyleOption().getShow());
            }
            List<Map<String, Object>> result = new ArrayList<>();
            for (Map.Entry<String, List<Integer>> entry : categoryMap.entrySet()) {
                result.addAll(modelMapper
                        .queryForPublish(entry.getKey(), entry.getValue(), count, startTime, endTime, fromId, toId));
            }
            for (Map<String, Object> temp : result) {
                Integer cid = (Integer) temp.get("category_id");
                Integer id = (Integer) temp.get("id");
                Date publish_time = (Date) temp.get("publish_time");
                String categoryDirName = categoryDirNameMap.get(cid);
                String showTemplate = showTemplateMap.get(cid);
                String url = getContentUrl(site, showTemplate, id, null);
                String html_url = getContentHtmlPath(contextPath, site, categoryDirName, publish_time);
                HttpUtils.staticHtml(url, html_url, id.toString());
            }
        } else {
            for (Category category : categories) {
                Integer id = category.getId();
                String html_url = getContentHtmlPath(contextPath, site, category.getDirName(), null);
                Byte type = category.getType();
                if(type == 1){
                    String style = category.getStyleOption().getHome();
                    String result = "";
                    int i = 1;
                    while (result != null){
                        String url = getContentUrl(site, style, id, i);
                        String name = id + "_" + i;
                        result = HttpUtils.staticHtml(url, html_url, name);
                        i++;
                    }
                } else if(type == 2){
                    String style = category.getStyleOption().getPage();
                    String url = getContentUrl(site, style, id, null);
                    HttpUtils.staticHtml(url, html_url, id.toString());
                }
            }
        }
    }

    /**
     * 更新静态资源文件
     * @param contextPath 根路径
     * @param styleName   样式名称
     * @param siteDirName    站点名称
     */
    @Transactional
    public void updateAssets(String contextPath, String styleName, String siteDirName) throws IOException {
        String srcPath = contextPath + "template/" +styleName + "/assets";
        String destPath = contextPath + "html/" + siteDirName;
        FileSystemUtils.copy(srcPath, destPath);
    }

    /**
     * 同步发布静态网页到节点服务器
     *
     * @param contextPath
     * @param site
     */
    @Transactional
    public String synchronizeContent(String contextPath, Site site) throws IOException {
        String htmlPath = getHtmlRoot(contextPath, site);
        String zipFilePath = ZipUtils.zip(htmlPath.substring(0, htmlPath.length() - 1));
        String url = site.getDomain() + "/disk";
        Map<String, String> textMap = new HashMap<String, String>();
        //可以设置多个input的name，value
        textMap.put("account", site.getUserName());
        textMap.put("password", site.getPassword());
        textMap.put("method", "publish");
        //设置file的name，路径
        Map<String, String> fileMap = new HashMap<String, String>();
        fileMap.put("file", zipFilePath);
        return HttpUtils.formUpload(url, textMap, fileMap, "");
    }
}
