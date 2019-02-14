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
@RequestMapping("/content/Article/")
public class ArticleHandler {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CopyFromService copyFromService;

    @Autowired
    private RecommendPositionService recommendPositionService;

    @Autowired
    private MemberGroupService memberGroupService;

    @Autowired
    private AttachIndexService attachIndexService;

    @RequestMapping("list")
    public String queryForList(Map<String, Object> request,
                               @RequestParam("cid") Integer categoryId,
                               @RequestParam(value = "tts", required = false) Byte status,
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
        List<Article> articles = articleService.queryForList(kwd, startTime, endTime, categoryId, status, order);
        PageInfo page = new PageInfo(articles);
        request.put("page", page);
        Category category = categoryService.getByIdWithWorkFlow(categoryId);
        request.put("category", category);
        return "content/article_list";
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
        Integer id = articleService.getIdByTitle(title);
        return id == null ? "0" : id.toString();
    }

    @Token(save = true)
    @RequestMapping(value = "{input}", method = RequestMethod.GET)
    public String input(@PathVariable("input") String input, Map<String, Object> request,
                        @RequestParam("cid") Integer categoryId,
                        @RequestParam(value = "id", required = false) Integer id) {
        if (("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
            Article article = new Article();
            if (id == null) {
                article.setPublishTime(new Date());
                article.setStatus((byte) Constant.CONTENT_VERIFY_PASS);

                ArticleData articleData = new ArticleData();
                articleData.setAllowComment(true);
                articleData.setReadPoint(0);
                articleData.setMaxCharPerPage(0);
                article.setArticleData(articleData);
            } else {
                article = articleService.getByIdWithData(id);
                if(article.getCategoryId() != categoryId){
                    return null;
                }
            }
            request.put("article", article);
            prepareRequest(request, categoryId);
            return "content/article_input";
        }
        return null;
    }

    @Token(remove = true)
    @RequestMapping(value = "{save}", method = RequestMethod.POST)
    public String save(@PathVariable("save") String save, Article article,
                       String voteId, String editorAttachment) {
        Integer id = article.getId();
        if (("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
            if (!"".equals(voteId.trim())) {
                article.getArticleData().setVoteId(Integer.parseInt(voteId));
            }
            articleService.save(article);

            // 关联上传的附件
            List<String> filePaths = new ArrayList<>();
            filePaths.add(article.getThumb());
            String[] tokens = editorAttachment.split("\\,");
            filePaths.addAll(Arrays.asList(tokens));
            AttachIndex attachIndex = new AttachIndex();
            attachIndex.setModule("content");
            attachIndex.setCategoryId(article.getCategoryId());
            attachIndex.setHost("article-" + article.getId());
            attachIndexService.save(filePaths, attachIndex);
            return redirectList(article.getCategoryId(), article.getStatus(), null);
        }
        return null;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Integer id, Integer cid, Byte tts, String odr) {
        articleService.delete(id);
        return redirectList(cid, tts, odr);
    }

    @RequestMapping(value = "batch-{method}", method = RequestMethod.POST)
    public String batchOperate(@PathVariable("method") String method,
                               Integer[] ids, Integer cid, Byte tts, String odr) {
        boolean flag = false;
        if (ids != null && ids.length > 0) {
            switch (method) {
                case "delete":
                    articleService.delete(ids);
                    flag = true;
                    break;
                default:
                    break;
            }
        }
        return flag ? redirectList(cid, tts, odr) : null;
    }

    @ModelAttribute
    public void getArticle(Map<String, Object> request,
                           @RequestParam(value = "id", required = false) Integer id) {
        if (id != null) {
            Article article = articleService.getByIdWithData(id);
            request.put("article", article);
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
        List<CopyFrom> copyFroms = copyFromService.getBySiteId(category.getSiteId(), null);
        request.put("copyFroms", copyFroms);
        List<RecommendPosition> recommendPositions = recommendPositionService.getByCategoryId(categoryId);
        request.put("recommendPositions", recommendPositions);
        List<MemberGroup> memberGroups = memberGroupService.getBySiteId(category.getSiteId());
        request.put("memberGroups", memberGroups);
    }

    /**
     * 删除和更改后的页面跳转
     *
     * @param cid
     * @param tts
     * @param odr
     * @return
     */
    private String redirectList(Integer cid, Byte tts, String odr) {
        String res = "redirect:/content/Article/list?cid=" + cid;
        if (tts != null && !"".equals(tts)) {
            res += "&tts=" + tts;
        }
        if (odr != null && !"".equals(odr)) {
            res += "&odr=" + odr;
        }
        return res;
    }

}