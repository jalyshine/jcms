package cn.jaly.content.controller;

import java.util.List;
import java.util.Map;

import cn.jaly.content.entity.Category;
import cn.jaly.content.entity.Model;
import cn.jaly.content.entity.RecommendPosition;
import cn.jaly.content.service.CategoryService;
import cn.jaly.content.service.ModelService;
import cn.jaly.content.service.RecommendPositionService;
import cn.jaly.utils.annotations.Token;
import cn.jaly.utils.common.Constant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/content/RecommendPosition/")
public class RecommendPositionHandler {

    @Autowired
    private RecommendPositionService recommendPositionService;

    @Autowired
    private ModelService modelService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("list")
    public String list(Map<String, Object> request, HttpSession session,
                       @RequestParam(value = "kwd", required = false) String keyword,
                       @RequestParam(value = "ps", required = false) Integer ps,
                       @RequestParam(value = "pn", required = false) Integer pn) {
        if (ps == null) {
            ps = 20;
        }
        if (pn == null) {
            pn = 1;
        }
        PageHelper.startPage(pn, ps);
        Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
        List<RecommendPosition> recommendPositions = recommendPositionService.getBySiteId(siteId, keyword);
        PageInfo page = new PageInfo(recommendPositions);
        request.put("page", page);
        return "content/recommend_position_list";
    }

    @Token(save = true)
    @RequestMapping(value = "{input}", method = RequestMethod.GET)
    public String input(@PathVariable("input") String input,
                        Map<String, Object> request, HttpSession session,
                        @RequestParam(value = "id", required = false) Integer id) {
        if(("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
            Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
            RecommendPosition recommendPosition = new RecommendPosition();
            if (id != null) {
                recommendPosition = recommendPositionService.getById(id);
            } else {
                recommendPosition.setSiteId(siteId);
                recommendPosition.setMaxItems(5);
            }
            request.put("recommendPosition", recommendPosition);
            List<Model> models = modelService.getBySiteId(siteId);
            request.put("models", models);

            Map<Integer, List<Category>> map = categoryService.mapByModel(models);
            Gson gson = new Gson();
            request.put("categories", gson.toJson(map));

            return "content/recommend_position_input";
        }
        return null;
    }

    @Token(remove = true)
    @RequestMapping(value = "{save}", method = RequestMethod.POST)
    public String save(@PathVariable("save") String save, RecommendPosition recommendPosition) {
        Integer id = recommendPosition.getId();
        if(("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
            recommendPositionService.save(recommendPosition);
            return "redirect:/content/RecommendPosition/list";
        }
        return null;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Integer id) {
        recommendPositionService.delete(id);
        return "redirect:/content/RecommendPosition/list";
    }
}
