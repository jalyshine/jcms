package cn.jaly.music.controller;

import cn.jaly.content.entity.AttachIndex;
import cn.jaly.content.service.AttachIndexService;
import cn.jaly.music.entity.MusicAttribute;
import cn.jaly.music.entity.MusicSinger;
import cn.jaly.music.service.MusicAttributeService;
import cn.jaly.music.service.MusicSingerService;
import cn.jaly.utils.annotations.Token;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/music/MusicSinger/")
public class MusicSingerHandler {

	@Autowired
	private MusicSingerService musicSingerService;

	@Autowired
	private MusicAttributeService musicAttributeService;

	@Autowired
    private AttachIndexService attachIndexService;
	
	@RequestMapping("list")
	public String queryForList(Map<String, Object> request,
							   @RequestParam(value = "aid", required = false) Integer areaId,
							   @RequestParam(value = "sex", required = false) Byte sex,
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
		List<MusicSinger> musicSingers = musicSingerService.queryForList(areaId, keyword, sex, order);
		PageInfo page = new PageInfo(musicSingers);
		request.put("page", page);

		List<MusicAttribute> areas = musicAttributeService.getByAttrName("area");
		request.put("areas", areas);
		return "music/music_singer_list";
	}

	/**
	 * 检查重名
	 * @param title
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="check", method=RequestMethod.POST)
	public String check(@RequestParam("ttl") String title){
		Integer id = musicSingerService.getIdByName(title);
		return id == null?"0":id.toString();
	}

	@Token(save = true)
	@RequestMapping(value = "{input}", method = RequestMethod.GET)
	public String input(@PathVariable("input") String input, Map<String, Object> request,
						@RequestParam(value = "id", required = false) Integer id){
		if(("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
			List<MusicAttribute> areas = musicAttributeService.getByAttrName("area");
			request.put("areas", areas);

			MusicSinger musicSinger = new MusicSinger();
			if (id != null) {
				musicSinger = musicSingerService.getById(id);
			}
			request.put("musicSinger", musicSinger);
			return "music/music_singer_input";
		}
		return null;
	}

	@Token(remove = true)
	@RequestMapping(value = "{save}", method = RequestMethod.POST)
	public String save(@PathVariable("save") String save, MusicSinger musicSinger){
		Integer id = musicSinger.getId();
		if(("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
			musicSingerService.save(musicSinger);

            // 关联上传的图片
            String face = musicSinger.getFace();
            AttachIndex attachIndex = new AttachIndex();
            attachIndex.setModule("music");
            attachIndex.setHost("music_singer-" + musicSinger.getId());
            attachIndexService.save(face, attachIndex);
			return "redirect:/music/MusicSinger/list";
		}
		return null;
	}
	
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public String delete(@RequestParam("id") Integer id){
		musicSingerService.delete(id);
		return "redirect:/music/MusicSinger/list";
	}
	
}
