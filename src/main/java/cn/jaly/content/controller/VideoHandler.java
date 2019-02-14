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
@RequestMapping("/content/Video/")
public class VideoHandler {

	@Autowired
	private VideoService videoService;

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
		List<Video> pictures = videoService.queryForList(kwd, startTime, endTime, categoryId, status, order);
		PageInfo page = new PageInfo(pictures);
		request.put("page", page);
		Category category = categoryService.getById(categoryId);
		request.put("category", category);
		return "content/video_list";
	}

	/**
	 * 检查标题是否重名
	 * @param title
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="check", method=RequestMethod.POST)
	public String check(@RequestParam("ttl") String title){
		Integer id = videoService.getIdByTitle(title);
		return id == null ? "0" : id.toString();
	}

	@Token(save = true)
	@RequestMapping(value = "{input}", method = RequestMethod.GET)
	public String input(@PathVariable("input") String input, Map<String, Object> request,
						@RequestParam("cid") Integer categoryId,
						@RequestParam(value = "id", required = false) Integer id){
		if (("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
			Video video = new Video();
			if (id == null) {
				video.setPublishTime(new Date());
				video.setStatus((byte) Constant.CONTENT_VERIFY_PASS);

				VideoData videoData = new VideoData();
				videoData.setAllowComment(true);
				videoData.setReadPoint(0);
				video.setVideoData(videoData);
			} else {
				video = videoService.getByIdWithData(id);
				if(video.getCategoryId() != categoryId){
					return null;
				}
			}
			request.put("video", video);
			prepareRequest(request, categoryId);
			return "content/video_input";
		}
		return null;
	}

	@Token(remove = true)
	@RequestMapping(value = "{save}", method = RequestMethod.POST)
	public String save(@PathVariable("save") String save, Video video,
					   String[] itemName, String[] itemSize, String[] itemUrl, String[] itemDesc){
		Integer id = video.getId();
		if (("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
			if (itemUrl != null && itemDesc != null) {
				List<VideoItem> videoItems = new ArrayList<>();
				for (int i = 0; i < itemUrl.length; i++) {
					Long size = Long.parseLong(itemSize[i]);
					VideoItem videoItem = new VideoItem(itemName[i], size, itemUrl[i], itemDesc[i]);
					videoItems.add(videoItem);
				}
				video.getVideoData().setVideoItems(videoItems);
			}
			videoService.save(video);

            // 关联上传的附件
            List<String> filePaths = new ArrayList<>();
            filePaths.add(video.getThumb());
            filePaths.addAll(Arrays.asList(itemUrl));
            AttachIndex attachIndex = new AttachIndex();
            attachIndex.setModule("content");
            attachIndex.setCategoryId(video.getCategoryId());
            attachIndex.setHost("video-" + video.getId());
            attachIndexService.save(filePaths, attachIndex);
			return redirectList(video.getCategoryId(), video.getStatus(), null);
		}
		return null;
	}

	@RequestMapping(value="delete", method=RequestMethod.POST)
	public String delete(@RequestParam("id") Integer id, Integer categoryId, Byte tts, String odr){
		videoService.delete(id);
        return redirectList(categoryId, tts, odr);
	}

	@RequestMapping(value = "batch-{method}", method = RequestMethod.POST)
	public String batchOperate(@PathVariable("method") String method,
							   Integer[] ids, Integer categoryId, Byte tts, String odr) {
		boolean flag = false;
		if (ids != null && ids.length > 0) {
			switch (method) {
				case "delete":
					videoService.delete(ids);
					flag = true;
					break;
				default:
					break;
			}
		}
		return flag ? redirectList(categoryId, tts, odr) : null;
	}

	@ModelAttribute
	public void getVideo(@RequestParam(value = "id", required = false) Integer id,
						 Map<String, Object> request){
		if(id != null){
			Video video = videoService.getByIdWithData(id);
			request.put("video", video);
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
		String res = "redirect:/content/Video/list?cid=" + cid;
		if(tts != null && !"".equals(tts)){
			res += "&tts=" + tts;
		}
		if(odr != null && !"".equals(odr)){
			res += "&odr=" + odr;
		}
		return res;
	}

}
