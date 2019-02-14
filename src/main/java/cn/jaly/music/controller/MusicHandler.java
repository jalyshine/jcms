package cn.jaly.music.controller;

import cn.jaly.music.entity.Music;
import cn.jaly.music.service.MusicService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/music/Music/")
public class MusicHandler {

	@Autowired
	private MusicService musicService;

	@RequestMapping("list")
	public String queryForList(Map<String, Object> request,
							   @RequestParam(value = "aid", required = false) Integer albumId,
							   @RequestParam(value = "stm", required = false) String stm,
							   @RequestParam(value = "edm", required = false) String edm,
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
		List<Music> musics = musicService.queryForList(albumId, keyword, stm, edm, order);
		PageInfo page = new PageInfo(musics);
		request.put("page", page);

		return "music/music_list";
	}

	@RequestMapping(value="delete", method=RequestMethod.POST)
	public String delete(@RequestParam("id") Integer id){
		musicService.delete(id);
		return "redirect:/music/Music/list";
	}

	/**
	 * 批量操作
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "batch-{method}", method = RequestMethod.POST)
	public String batchOperate(@PathVariable("method") String method, Integer[] ids) {
		boolean flag = false;
		if (ids != null && ids.length > 0) {
			switch (method){
				case "delete":
					musicService.deletes(ids);
					flag = true;
					break;
				default: break;
			}
		}
		return flag?"redirect:/music/Music/list":null;
	}

}
