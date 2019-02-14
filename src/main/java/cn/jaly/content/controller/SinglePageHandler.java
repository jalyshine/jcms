package cn.jaly.content.controller;

import cn.jaly.content.entity.AttachIndex;
import cn.jaly.content.entity.Category;
import cn.jaly.content.entity.SinglePage;
import cn.jaly.content.service.AttachIndexService;
import cn.jaly.content.service.CategoryService;
import cn.jaly.content.service.SinglePageService;
import cn.jaly.utils.common.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

@Controller
@RequestMapping("/content/SinglePage/")
public class SinglePageHandler {

    @Autowired
    private SinglePageService singlePageService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AttachIndexService attachIndexService;

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Map<String, Object> request, HttpServletRequest servletRequest,
                       @RequestParam("cid") Integer categoryId) {
        SinglePage singlePage = singlePageService.getByCategoryId(categoryId);
        if (singlePage == null) {
            singlePage = new SinglePage();
            singlePage.setCategoryId(categoryId);
        }
        request.put("singlePage", singlePage);

        Category category = categoryService.getById(categoryId);
        request.put("category", category);
        return "content/single_page";
    }

    @ResponseBody
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String save(SinglePage singlePage, String editorAttachment) {
        singlePageService.save(singlePage);

        // 关联上传的附件
        String[] tokens = editorAttachment.split("\\,");
        AttachIndex attachIndex = new AttachIndex();
        attachIndex.setModule("content");
        attachIndex.setCategoryId(singlePage.getCategoryId());
        attachIndex.setHost("single_page-" + singlePage.getId());
        attachIndexService.save(Arrays.asList(tokens), attachIndex);
        return new ResultBean(0).toJson();
    }

}
