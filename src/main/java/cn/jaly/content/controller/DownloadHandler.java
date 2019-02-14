package cn.jaly.content.controller;

import cn.jaly.content.entity.*;
import cn.jaly.content.service.AttachIndexService;
import cn.jaly.content.service.CategoryService;
import cn.jaly.content.service.DownloadService;
import cn.jaly.content.service.RecommendPositionService;
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
@RequestMapping("/content/Download/")
public class DownloadHandler {

	@Autowired
	private DownloadService downloadService;

	@Autowired
	private CategoryService categoryService;

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
		List<Download> articles = downloadService.queryForList(kwd, startTime, endTime, categoryId, status, order);
		PageInfo page = new PageInfo(articles);
		request.put("page", page);
		Category category = categoryService.getById(categoryId);
		request.put("category", category);
		return "content/download_list";
	} 

	/**
	 * 检查标题是否重名
	 * @param title
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="check", method=RequestMethod.POST)
	public String check(@RequestParam("ttl") String title){
		Integer id = downloadService.getIdByTitle(title);
		return id == null ? "0" : id.toString();
	}

	@Token(save = true)
	@RequestMapping(value = "{input}", method = RequestMethod.GET)
	public String input(@PathVariable("input") String input, Map<String, Object> request,
						@RequestParam("cid") Integer categoryId,
						@RequestParam(value = "id", required = false) Integer id){
		if (("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
			Download download = new Download();
			if (id == null) {
				download.setPublishTime(new Date());
				download.setStatus((byte) Constant.CONTENT_VERIFY_PASS);

				DownloadDataWithBLOBs downloadData = new DownloadDataWithBLOBs();
				downloadData.setReadPoint(0);
				downloadData.setAllowComment(true);
				download.setDownloadData(downloadData);
			} else {
				download = downloadService.getByIdWithData(id);
				if(download.getCategoryId() != categoryId){
					return null;
				}
			}
			request.put("download", download);
			prepareRequest(request, categoryId);
			return "content/download_input";
		}
		return null;
	}

	@Token(remove = true)
	@RequestMapping(value = "{save}", method = RequestMethod.POST)
	public String save(@PathVariable("save") String save, Download download,
                       String[] itemName, String[] itemSize, String[] itemUrl, String editorAttachment){
		Integer id = download.getId();
		if (("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
			if (itemName != null && itemUrl != null) {
				List<DownloadItem> downloadItems = new ArrayList<>();
				for (int i = 0; i < itemName.length; i++) {
					Long size =  Long.parseLong(itemSize[i]);
					DownloadItem downloadItem = new DownloadItem(itemName[i], size, itemUrl[i]);
					downloadItems.add(downloadItem);
				}
				DownloadDataWithBLOBs data = (DownloadDataWithBLOBs) download.getDownloadData();
				data.setDownloadItems(downloadItems);
			}
			downloadService.save(download);

            // 关联上传的附件
            List<String> filePaths = new ArrayList<>();
            filePaths.add(download.getThumb());
            filePaths.addAll(Arrays.asList(itemUrl));
            String[] tokens = editorAttachment.split("\\,");
            filePaths.addAll(Arrays.asList(tokens));
            AttachIndex attachIndex = new AttachIndex();
            attachIndex.setModule("content");
            attachIndex.setCategoryId(download.getCategoryId());
            attachIndex.setHost("download-" + download.getId());
            attachIndexService.save(filePaths, attachIndex);
			return redirectList(download.getCategoryId(), download.getStatus(), null);
		}
		return null;
	}

	@RequestMapping(value="delete", method=RequestMethod.POST)
	public String delete(@RequestParam("id") Integer id, Integer categoryId, Byte tts, String odr){
		downloadService.delete(id);
		return redirectList(categoryId, tts, odr);
	}

	@RequestMapping(value = "batch-{method}", method = RequestMethod.POST)
	public String batchOperate(@PathVariable("method") String method,
							   Integer[] ids, Integer categoryId, Byte tts, String odr) {
		boolean flag = false;
		if (ids != null && ids.length > 0) {
			switch (method) {
				case "delete":
					downloadService.delete(ids);
					flag = true;
					break;
				default:
					break;
			}
		}
		return flag ? redirectList(categoryId, tts, odr) : null;
	}

	@ModelAttribute
	public void getDownload(Map<String, Object> request,
							@RequestParam(value = "id", required = false) Integer id){
		if(id != null){
			Download download = downloadService.getByIdWithData(id);
			request.put("download", download);
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
		String res = "redirect:/content/Download/list?cid=" + cid;
		if(tts != null && !"".equals(tts)){
			res += "&tts=" + tts;
		}
		if(odr != null && !"".equals(odr)){
			res += "&odr=" + odr;
		}
		return res;
	}
}