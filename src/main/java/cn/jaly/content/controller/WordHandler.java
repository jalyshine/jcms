package cn.jaly.content.controller;

import cn.jaly.content.entity.*;
import cn.jaly.content.service.*;
import cn.jaly.member.entity.MemberGroup;
import cn.jaly.member.service.MemberGroupService;
import cn.jaly.utils.annotations.Token;
import cn.jaly.utils.common.Constant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/content/Word/")
public class WordHandler {

    @Autowired
    private WordService wordService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AttachIndexService attachIndexService;

    @RequestMapping("list")
    public String queryForList(Map<String, Object> request,
                               @RequestParam("cid") Integer categoryId,
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
        List<Word> words = wordService.queryForList(kwd, startTime, endTime, categoryId, order);
        PageInfo page = new PageInfo(words);
        request.put("page", page);
        Category category = categoryService.getByIdWithWorkFlow(categoryId);
        request.put("category", category);
        return "content/word_list";
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
        Integer id = wordService.getIdByTitle(title);
        return id == null ? "0" : id.toString();
    }

    @Token(save = true)
    @RequestMapping(value = "{input}", method = RequestMethod.GET)
    public String input(@PathVariable("input") String input, Map<String, Object> request,
                        @RequestParam("cid") Integer categoryId,
                        @RequestParam(value = "id", required = false) Integer id) {
        if (("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
            Word word = new Word();
            if (id != null) {
                word = wordService.getById(id);
                if(word.getCategoryId() != categoryId){
                    return null;
                }
            } else {
                word.setPublishTime(new Date());
            }
            request.put("word", word);
            prepareRequest(request, categoryId);
            return "content/word_input";
        }
        return null;
    }

    @Token(remove = true)
    @RequestMapping(value = "{save}", method = RequestMethod.POST)
    public String save(@PathVariable("save") String save, Word word, String editorAttachment) {
        Integer id = word.getId();
        if (("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
            wordService.save(word);

            // 关联上传的附件
            List<String> filePaths = new ArrayList<>();
            filePaths.add(word.getThumb());
            filePaths.add(word.getBanner());
            filePaths.add(word.getIcon());
            String[] tokens = editorAttachment.split("\\,");
            filePaths.addAll(Arrays.asList(tokens));
            AttachIndex attachIndex = new AttachIndex();
            attachIndex.setModule("content");
            attachIndex.setCategoryId(word.getCategoryId());
            attachIndex.setHost("word-" + word.getId());
            attachIndexService.save(filePaths, attachIndex);
            return redirectList(word.getCategoryId(), null);
        }
        return null;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Integer id, Integer cid, String odr) {
        wordService.delete(id);
        return redirectList(cid, odr);
    }

    @RequestMapping(value = "batch-{method}", method = RequestMethod.POST)
    public String batchOperate(@PathVariable("method") String method,
                               Integer[] ids, Integer cid, String odr) {
        boolean flag = false;
        if (ids != null && ids.length > 0) {
            switch (method) {
                case "delete":
                    wordService.delete(ids);
                    flag = true;
                    break;
                default:
                    break;
            }
        }
        return flag ? redirectList(cid, odr) : null;
    }

    @ModelAttribute
    public void getWord(Map<String, Object> request,
                           @RequestParam(value = "id", required = false) Integer id) {
        if (id != null) {
            Word word = wordService.getById(id);
            request.put("word", word);
        }
    }

    /**
     * 准备添加和编辑页面的请求域
     *
     * @param request
     * @param categoryId
     */
    private void prepareRequest(Map<String, Object> request, Integer categoryId) {
        Category category = categoryService.getByIdWithWorkFlow(categoryId);
        request.put("category", category);
    }

    /**
     * 删除和更改后的页面跳转
     *
     * @param cid
     * @param odr
     * @return
     */
    private String redirectList(Integer cid, String odr) {
        String res = "redirect:/content/Word/list?cid=" + cid;
        if (odr != null && !"".equals(odr)) {
            res += "&odr=" + odr;
        }
        return res;
    }

}