package cn.jaly.music.controller;

import cn.jaly.content.entity.AttachIndex;
import cn.jaly.content.service.AttachIndexService;
import cn.jaly.music.entity.MusicSinger;
import cn.jaly.music.entity.MusicVideo;
import cn.jaly.music.service.MusicSingerService;
import cn.jaly.music.service.MusicVideoService;
import cn.jaly.utils.annotations.Token;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/music/MusicVideo/")
public class MusicVideoHandler {

	@Autowired
	private MusicVideoService musicVideoService;
	
	@Autowired
	private MusicSingerService musicSingerService;

	@Autowired
    private AttachIndexService attachIndexService;
	
	@RequestMapping("list")
	public String queryForList(Map<String, Object> request,
							   @RequestParam(value = "sid", required = false) Integer singerId,
							   @RequestParam(value = "kwd", required = false) String keyword,
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
		List<MusicVideo> musicAlbums = musicVideoService.queryForList(singerId, keyword, order);
		PageInfo page = new PageInfo(musicAlbums);
		request.put("page", page);

		return "music/music_video_list";
	}

	@ResponseBody
	@RequestMapping(value="check", method=RequestMethod.POST)
	public String check(@RequestParam("ttl") String title){
		Integer id = musicVideoService.getIdByTitle(title);
		return id == null?"0":id.toString();
	}

	@Token(save = true)
	@RequestMapping(value = "{input}", method = RequestMethod.GET)
	public String input(@PathVariable("input") String input, Map<String, Object> request,
						@RequestParam(value = "sid", required = false) Integer musicSingerId,
						@RequestParam(value = "id", required = false) Integer id) {
		if(("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
			MusicVideo musicVideo = new MusicVideo();
			if (id == null) {
				musicVideo.setShowDate(new Date());
			} else {
				musicVideo = musicVideoService.getById(id);
				musicSingerId = musicVideo.getMusicSingerId();
			}
			request.put("musicVideo", musicVideo);
			MusicSinger musicSinger = musicSingerService.getById(musicSingerId);
			request.put("musicSinger", musicSinger);
			return "music/music_video_input";
		}
		return null;
	}

	@Token(remove = true)
	@RequestMapping(value = "{save}", method = RequestMethod.POST)
	public String save(@PathVariable("save") String save, MusicVideo musicVideo){
		Integer id = musicVideo.getId();
		if(("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
			int musicSingerId = musicVideo.getMusicSingerId();
			musicVideoService.save(musicVideo);

            // 关联上传的文件
            List<String> filePaths = new ArrayList<>();
            filePaths.add(musicVideo.getThumb());
            filePaths.add(musicVideo.getBanner());
            filePaths.add(musicVideo.getUrl());
            AttachIndex attachIndex = new AttachIndex();
            attachIndex.setModule("music");
            attachIndex.setHost("music_video-" + musicVideo.getId());
            attachIndexService.save(filePaths, attachIndex);
			return "redirect:/music/MusicVideo/list?sid=" + musicSingerId;
		}
		return null;
	}
	
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public String delete(@RequestParam("id") Integer id, @RequestParam("sid") Integer musicSingerId){
		musicVideoService.delete(id);
		return "redirect:/music/MusicVideo/list?sid=" + musicSingerId;
	}
}
