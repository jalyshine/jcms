package cn.jaly.content.controller;

import cn.jaly.content.entity.*;
import cn.jaly.content.service.*;
import cn.jaly.member.entity.MemberGroup;
import cn.jaly.content.entity.PictureItem;
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
@RequestMapping("/content/Picture/")
public class PictureHandler {

	@Autowired
	private PictureService pictureService;

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
		List<Picture> pictures = pictureService.queryForList(kwd, startTime, endTime, categoryId, status, order);
		PageInfo page = new PageInfo(pictures);
		request.put("page", page);
		Category category = categoryService.getById(categoryId);
		request.put("category", category);
		return "content/picture_list";
	}

	/**
	 * 检查标题是否重名
	 * @param title
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="check", method=RequestMethod.POST)
	public String check(@RequestParam("ttl") String title){
		Integer id = pictureService.getIdByTitle(title);
		return id == null ? "0" : id.toString();
	}

	@Token(save = true)
	@RequestMapping(value = "{input}", method = RequestMethod.GET)
	public String input(@PathVariable("input") String input, Map<String, Object> request,
						@RequestParam("cid") Integer categoryId,
						@RequestParam(value = "id", required = false) Integer id){
		if (("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
			Picture picture = new Picture();
			if (id == null) {
				picture.setPublishTime(new Date());
				picture.setStatus((byte) Constant.CONTENT_VERIFY_PASS);

				PictureData pictureData = new PictureData();
				pictureData.setReadPoint(0);
				pictureData.setAllowComment(true);
				picture.setPictureData(pictureData);
			} else {
				picture = pictureService.getByIdWithData(id);
				if(picture.getCategoryId() != categoryId){
					return null;
				}
			}
			request.put("picture", picture);
			prepareRequest(request, categoryId);
			return "content/picture_input";
		}
		return null;
	}

	@Token(remove = true)
	@RequestMapping(value = "{save}", method = RequestMethod.POST)
	public String save(@PathVariable("save") String save, Picture picture,
					   String[] itemName, String[] itemSize, String[] itemUrl, String[] itemDesc){
		Integer id = picture.getId();
		if (("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
			if (itemUrl != null && itemDesc != null) {
				List<PictureItem> pictureItems = new ArrayList<>();
				for (int i = 0; i < itemUrl.length; i++) {
					Long size =  Long.parseLong(itemSize[i]);
					PictureItem pictureItem = new PictureItem(itemName[i], size, itemUrl[i], itemDesc[i]);
					pictureItems.add(pictureItem);
				}
				picture.getPictureData().setPictureItems(pictureItems);
			}
			pictureService.save(picture);

            // 关联上传的附件
            List<String> filePaths = new ArrayList<>();
            filePaths.add(picture.getThumb());
            filePaths.addAll(Arrays.asList(itemUrl));
            AttachIndex attachIndex = new AttachIndex();
            attachIndex.setModule("content");
            attachIndex.setCategoryId(picture.getCategoryId());
            attachIndex.setHost("picture-" + picture.getId());
            attachIndexService.save(filePaths, attachIndex);
			return redirectList(picture.getCategoryId(), picture.getStatus(), null);
		}
		return null;
	}

	@RequestMapping(value="delete", method=RequestMethod.POST)
	public String delete(@RequestParam("id") Integer id, Integer categoryId, Byte tts, String odr){
		pictureService.delete(id);
		return redirectList(categoryId, tts, odr);
	}

	@RequestMapping(value = "batch-{method}", method = RequestMethod.POST)
	public String batchOperate(@PathVariable("method") String method,
							   Integer[] ids, Integer categoryId, Byte tts, String odr) {
		boolean flag = false;
		if (ids != null && ids.length > 0) {
			switch (method) {
				case "delete":
					pictureService.delete(ids);
					flag = true;
					break;
				default:
					break;
			}
		}
		return flag ? redirectList(categoryId, tts, odr) : null;
	}

	@ModelAttribute
	public void getPicture(@RequestParam(value = "id", required = false) Integer id,
						   Map<String, Object> request){
		if(id != null){
			Picture picture = pictureService.getByIdWithData(id);
			request.put("picture", picture);
		}
	}

	/**
	 * 准备添加和编辑页面的请求域
	 * @param request
	 * @param categoryId
	 */
	private void prepareRequest(Map<String, Object> request, Integer categoryId){
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
	 * @param cid
	 * @param tts
	 * @param odr
	 * @return
	 */
	private String redirectList(Integer cid, Byte tts, String odr){
		String res = "redirect:/content/Picture/list?cid=" + cid;
		if(tts != null && !"".equals(tts)){
			res += "&tts=" + tts;
		}
		if(odr != null && !"".equals(odr)){
			res += "&odr=" + odr;
		}
		return res;
	}

}
