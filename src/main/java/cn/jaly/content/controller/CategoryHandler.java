package cn.jaly.content.controller;

import cn.jaly.content.entity.AttachIndex;
import cn.jaly.content.entity.Category;
import cn.jaly.content.entity.Model;
import cn.jaly.content.entity.WorkFlow;
import cn.jaly.content.service.AttachIndexService;
import cn.jaly.content.service.CategoryService;
import cn.jaly.content.service.ModelService;
import cn.jaly.content.service.WorkFlowService;
import cn.jaly.template.service.TemplateService;
import cn.jaly.utils.annotations.Token;
import cn.jaly.utils.common.Constant;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/content/Category/")
public class CategoryHandler {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelService modelService;

    @Autowired
    private WorkFlowService workFlowService;

    @Autowired
    private AttachIndexService attachIndexService;

    @Autowired
    private TemplateService templateService;

    @RequestMapping("list")
    public String list(Map<String, Object> request, HttpSession session) {
        Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
        List<Category> categoryTree = categoryService.treeForListBySiteId(siteId);
        request.put("categoryTree", categoryTree);
        return "content/category_list";
    }

    @Token(save = true)
    @RequestMapping(value = "{input}", method = RequestMethod.GET)
    public String input(@PathVariable("input") String input, HttpServletRequest servletRequest,
                        Map<String, Object> request, HttpSession session,
                        @RequestParam(value = "id", required = false) Integer id,
                        @RequestParam(value = "pid", required = false) Integer parentId,
                        @RequestParam(value = "type", required = false) Byte type) {
        if (("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
            Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
            Category category = new Category();
            if (id == null) {
                category.setSiteId(siteId);
                category.setParentId(parentId);
                category.setType(type);
            } else {
                category = categoryService.getById(id);
                type = category.getType();
                siteId = category.getSiteId();
            }
            request.put("category", category);

            List<Category> categoryTree = categoryService.treeForListBySiteId(siteId);
            request.put("categoryTree", categoryTree);

            if (type == Constant.CATEGORY_TYPE_LINK) {
                return "content/category_input_link";
            }

            String contextPath = servletRequest.getServletContext().getRealPath("/");

            if(type == Constant.CATEGORY_TYPE_NORMAL){
                List<Model> models = modelService.getBySiteId(siteId);
                request.put("models", models);
                List<WorkFlow> workFlows = workFlowService.getBySiteId(siteId);
                request.put("workFlows", workFlows);
                List<String> homeTemps =
                        templateService.queryFileForList(contextPath, siteId, "content", "home_");
                request.put("template_home", homeTemps);
                List<String> showTemps =
                        templateService.queryFileForList(contextPath, siteId, "content", "show_");
                request.put("template_show", showTemps);
            } else if(type == Constant.CATEGORY_TYPE_SINGLE) {
                List<String> pageTemps =
                        templateService.queryFileForList(contextPath, siteId, "content", "page_");
                request.put("template_page", pageTemps);
            }

            return "content/category_input";
        }
        return null;
    }

    @Token(remove = true)
    @RequestMapping(value = "{save}", method = RequestMethod.POST)
    public String save(@PathVariable("save") String save, Category category) {
        Integer id = category.getId();
        if (("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
            Gson gson = new Gson();
            category.setMeta(gson.toJson(category.getMetaOption()));
            category.setStyle(gson.toJson(category.getStyleOption()));
            categoryService.save(category);

            // 关联上传的图片
            List<String> filePaths = new ArrayList<>();
            filePaths.add(category.getBanner());
            filePaths.add(category.getIcon());
            filePaths.add(category.getThumb());
            AttachIndex attachIndex = new AttachIndex();
            attachIndex.setModule("content");
            attachIndex.setHost("category-" + category.getId());
            attachIndexService.save(filePaths, attachIndex);
            return "redirect:/content/Category/list";
        }
        return null;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Integer id) {
        categoryService.delete(id);
        return "redirect:/content/Category/list";
    }

    @RequestMapping(value = "batch-{method}", method = RequestMethod.POST)
    public String batchOperate(@PathVariable("method") String method,
                               Integer[] id, Integer[] order) {
        boolean flag = false;
        if (id != null) {
            switch (method) {
                case "sort":
                    categoryService.sort(id, order);
                    flag = true;
                    break;
                default:
                    break;
            }
        }
        return flag ? "redirect:/content/Category/list" : null;
    }
}
