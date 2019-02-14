package cn.jaly.special.controller;

import cn.jaly.content.entity.AttachIndex;
import cn.jaly.content.entity.Category;
import cn.jaly.content.entity.Model;
import cn.jaly.content.service.AttachIndexService;
import cn.jaly.content.service.CategoryService;
import cn.jaly.content.service.ModelService;
import cn.jaly.special.entity.Special;
import cn.jaly.special.entity.SpecialContent;
import cn.jaly.special.entity.SpecialContentData;
import cn.jaly.special.entity.SpecialType;
import cn.jaly.special.service.SpecialContentService;
import cn.jaly.special.service.SpecialService;
import cn.jaly.special.service.SpecialTypeService;
import cn.jaly.utils.annotations.Token;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/special/SpecialContent/")
public class SpecialContentHandler {

    @Autowired
    private ModelService modelService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SpecialService specialService;

    @Autowired
    private SpecialTypeService specialTypeService;

    @Autowired
    private SpecialContentService specialContentService;

    @Autowired
    private AttachIndexService attachIndexService;

    @RequestMapping("list")
    public String queryForList(Map<String, Object> request,
                               @RequestParam("sid") Integer specialId,
                               @RequestParam(value = "stm", required = false) String startTime,
                               @RequestParam(value = "edm", required = false) String endTime,
                               @RequestParam(value = "kwd", required = false) String kwd,
                               @RequestParam(value = "odr", required = false) String order,
                               @RequestParam(value = "ps", required = false) Integer ps,
                               @RequestParam(value = "pn", required = false) Integer pn) {
        if (ps == null) {
            ps = 20;
        }
        if (pn == null) {
            pn = 1;
        }
        PageHelper.startPage(pn, ps);
        List<SpecialContent> specialContents = specialContentService.queryForList(kwd, startTime, endTime, specialId, order);
        PageInfo page = new PageInfo(specialContents);
        request.put("page", page);
        return "special/special_content_list";
    }

    /**
     * 检查标题是否重名
     *
     * @param title
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "check", method = RequestMethod.POST)
    public String check(@RequestParam("ttl") String title) {
        Integer id = specialContentService.getIdByTitle(title);
        return id == null ? "0" : id.toString();
    }

    @Token(save = true)
    @RequestMapping(value = "{input}", method = RequestMethod.GET)
    public String input(@PathVariable("input") String input, Map<String, Object> request,
                        @RequestParam(value = "sid", required = false) Integer specialId,
                        @RequestParam(value = "id", required = false) Integer id) {
        if (("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
            SpecialContent specialContent = new SpecialContent();
            if (id == null) {
                specialContent.setPublishTime(new Date());
                specialContent.setIsData(true);
                SpecialContentData specialContentData = new SpecialContentData();
                specialContentData.setMaxCharPerPage(0);
                specialContent.setSpecialContentData(specialContentData);
            } else {
                specialContent = specialContentService.getByIdWithData(id);
                specialId = specialContent.getSpecialId();
            }
            request.put("specialContent", specialContent);
            List<SpecialType> specialTypes = specialTypeService.getBySpecialId(specialId);
            request.put("specialTypes", specialTypes);

            return "special/special_content_input";
        }
        return null;
    }

    @Token(remove = true)
    @RequestMapping(value = "{save}", method = RequestMethod.POST)
    public String save(@PathVariable("save") String save,
                       SpecialContent specialContent, String editorAttachment) {
        Integer id = specialContent.getId();
        if (("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
            specialContentService.save(specialContent);

            // 关联上传的附件
            List<String> filePaths = new ArrayList<>();
            filePaths.add(specialContent.getThumb());
            String[] tokens = editorAttachment.split("\\,");
            filePaths.addAll(Arrays.asList(tokens));
            AttachIndex attachIndex = new AttachIndex();
            attachIndex.setModule("special");
            attachIndex.setHost("special_content-" + specialContent.getId());
            attachIndexService.save(filePaths, attachIndex);

            return "redirect:/special/SpecialContent/list?sid=" + specialContent.getSpecialId();
        }
        return null;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Integer id, Integer sid) {
        specialContentService.delete(id);
        return "redirect:/special/SpecialContent/list?sid=" + sid;
    }

    @ModelAttribute
    public void getSpecialContent(Map<String, Object> request,
                                  @RequestParam(value = "id", required = false) Integer id) {
        if (id != null) {
            SpecialContent specialContent = specialContentService.getByIdWithData(id);
            request.put("specialContent", specialContent);
        }
    }

    @RequestMapping(value = "load", method = RequestMethod.GET)
    public String load(Map<String, Object> request,
                       @RequestParam("sid") Integer sid,
                       @RequestParam(value = "mid", required = false) Integer mid,
                       @RequestParam(value = "cid", required = false) Integer categoryId,
                       @RequestParam(value = "stm", required = false) String stm,
                       @RequestParam(value = "edm", required = false) String edm,
                       @RequestParam(value = "kwd", required = false) String keyword) {
        Special special = specialService.getById(sid);
        Integer siteId = special.getSiteId();

        List<Model> models = modelService.getBySiteId(siteId);
        request.put("models", models);

        Map<Integer, List<Category>> categoryMap = categoryService.mapByModel(models);
        request.put("categoryMap", categoryMap);

        List<SpecialType> specialTypes = specialTypeService.getBySpecialId(sid);
        request.put("specialTypes", specialTypes);

        if (mid != null && mid != 0) {
            String tableName = null;
            for (Model m : models) {
                if (m.getId() == mid) {
                    tableName = m.getMysqlTableName();
                }
            }
            if (categoryId == 0) {
                categoryId = null;
            }
            List<Map<String, Object>> contents = modelService.queryContentForList(tableName, keyword, stm, edm, categoryId);
            for (Map<String, Object> content : contents) {
                content.put("table_name", tableName);
            }
            request.put("contents", contents);
        }
        return "special/special_content_load";
    }

    /**
     * 批处理操作（导入）
     *
     * @param sid
     * @param tid
     * @param ids
     * @param tableName
     * @param title
     * @param description
     * @return
     */
    @RequestMapping(value = "load", method = RequestMethod.POST)
    public String load(Integer sid, Integer tid, Integer[] ids,
                       String[] tableName, String[] title, String[] description) {
        if (ids != null) {
            for (int i = 0; i < ids.length; i++) {
                SpecialContent content = new SpecialContent();
                content.setSpecialId(sid);
                content.setSpecialTypeId(tid);
                content.setIsData(false);
                content.setContentEntity(tableName[i]);
                content.setContentId(ids[i]);
                content.setTitle(title[i]);
                content.setDescription(description[i]);
                specialContentService.save(content);
            }
        }
        return "redirect:/special/SpecialContent/list?sid=" + sid;
    }
}
