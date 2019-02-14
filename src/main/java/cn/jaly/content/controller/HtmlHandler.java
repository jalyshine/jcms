package cn.jaly.content.controller;

import cn.jaly.admin.entity.Site;
import cn.jaly.admin.service.SiteService;
import cn.jaly.content.entity.Category;
import cn.jaly.content.service.CategoryService;
import cn.jaly.content.service.HtmlService;
import cn.jaly.utils.common.Constant;
import cn.jaly.utils.common.ExceptionUtils;
import cn.jaly.utils.common.ResultBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/content/Html/")
public class HtmlHandler {

    private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private HtmlService htmlService;

    @Autowired
    private SiteService siteService;

    /**
     * 显示更新栏目页
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "category", method = RequestMethod.GET)
    public String updateCategoryPage(HttpSession session, Map<String, Object> request){
        Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
        Site site = siteService.getById(siteId);
        request.put("site", site);
        List<Category> categories = categoryService.treeForListBySiteId(siteId);
        request.put("categories", categories);
        return "content/html_category";
    }

    /**
     * 显示更新内容页
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String updateDetailPage(HttpSession session, Map<String, Object> request){
        Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
        Site site = siteService.getById(siteId);
        request.put("site", site);
        List<Category> categories = categoryService.treeForListBySiteId(siteId);
        request.put("categories", categories);
        return "content/html_detail";
    }

    /**
     * 更新首页
     * @param session
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "home", method = RequestMethod.POST)
    public String updateHomePage(HttpSession session, HttpServletRequest request){
        ResultBean result = new ResultBean(0);
        String contextPath = request.getServletContext().getRealPath("/");
        Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
        Site site = siteService.getById(siteId);
        try {
            htmlService.updateHomePage(site, contextPath);
        } catch (IOException e) {
            logger.error(ExceptionUtils.formatExceptionInfo(e));
            result.setCode(1);
            result.setMsg(e.getMessage());
        }
        return result.toJson();
    }

    /**
     * 更新栏目页
     * @param request
     * @param categoryId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "category", method = RequestMethod.POST)
    public String updateContentPage(HttpServletRequest request, HttpSession session, Integer[] categoryId){
        ResultBean resultBean = new ResultBean(0);
        String contextPath = request.getServletContext().getRealPath("/");
        try {
            Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
            Site site = siteService.getById(siteId);
            htmlService.updateCategory(site, contextPath, categoryId);
        } catch (IOException e) {
            logger.error(ExceptionUtils.formatExceptionInfo(e));
            resultBean.setCode(1);
            resultBean.setMsg(e.getMessage());
        }
        return resultBean.toJson();
    }

    /**
     * 更新内容页
     * @param request
     * @param categoryId
     * @param count
     * @param stm
     * @param edm
     * @param fromId
     * @param toId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "detail", method = RequestMethod.POST)
    public String updateDetailPage(HttpServletRequest request, HttpSession session, Integer[] categoryId,
                                   @RequestParam(value = "count", required = false) Integer count,
                                   @RequestParam(value = "stm", required = false) String stm,
                                   @RequestParam(value = "edm", required = false) String edm,
                                   @RequestParam(value = "fid", required = false) Integer fromId,
                                   @RequestParam(value = "tid", required = false) Integer toId){
        ResultBean resultBean = new ResultBean(0);
        String contextPath = request.getServletContext().getRealPath("/");
        try {
            Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
            Site site = siteService.getById(siteId);
            htmlService.updateDetail(site, contextPath, categoryId, count, stm, edm, fromId, toId);
        } catch (IOException e) {
            logger.error(ExceptionUtils.formatExceptionInfo(e));
            resultBean.setCode(1);
            resultBean.setMsg(e.getMessage());
        }
        return resultBean.toJson();
    }

    /**
     * 更新静态资源
     * @param request
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "assets", method = RequestMethod.POST)
    public String updateAssets(HttpServletRequest request, HttpSession session){
        ResultBean resultBean = new ResultBean(0);
        String contextPath = request.getServletContext().getRealPath("/");
        Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
        Site site = siteService.getById(siteId);
        try {
            htmlService.updateAssets(contextPath, site.getUiStyle(), site.getDirName());
        } catch (IOException e) {
            logger.error(ExceptionUtils.formatExceptionInfo(e));
            resultBean.setCode(1);
            resultBean.setMsg(e.getMessage());
        }
        return resultBean.toJson();
    }

    /**
     * 同步到节点站
     * @param session
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("synchronize")
    public String synchronize(HttpSession session, HttpServletRequest request){
        ResultBean result = new ResultBean(1);
        Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
        Site site = siteService.getById(siteId);
        if(siteId != 1) {
            String contextPath = request.getServletContext().getRealPath("/");
            try {
                return htmlService.synchronizeContent(contextPath, site);
            } catch (IOException e) {
                logger.error(ExceptionUtils.formatExceptionInfo(e));
                result.setCode(1);
                result.setMsg(e.getMessage());
            }
        }
        return result.toJson();
    }
}