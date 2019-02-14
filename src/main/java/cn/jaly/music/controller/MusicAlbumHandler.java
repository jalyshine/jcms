package cn.jaly.music.controller;

import cn.jaly.content.entity.AttachIndex;
import cn.jaly.content.service.AttachIndexService;
import cn.jaly.music.entity.Music;
import cn.jaly.music.entity.MusicAlbum;
import cn.jaly.music.entity.MusicSinger;
import cn.jaly.music.service.MusicAlbumService;
import cn.jaly.music.service.MusicService;
import cn.jaly.music.service.MusicSingerService;
import cn.jaly.utils.annotations.Token;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/music/MusicAlbum/")
public class MusicAlbumHandler {

    @Autowired
    private MusicAlbumService musicAlbumService;

    @Autowired
    private MusicSingerService musicSingerService;

    @Autowired
    private MusicService musicService;

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
        List<MusicAlbum> musicAlbums = musicAlbumService.queryForList(singerId, keyword, order);
        PageInfo page = new PageInfo(musicAlbums);
        request.put("page", page);

        return "music/music_album_list";
    }

    @ResponseBody
    @RequestMapping(value = "check", method = RequestMethod.POST)
    public String check(@RequestParam("ttl") String title) {
        Integer id = musicAlbumService.getIdByTitle(title);
        return id == null ? "0" : id.toString();
    }

    @Token(save = true)
    @RequestMapping(value = "{input}", method = RequestMethod.GET)
    public String input(@PathVariable("input") String input, Map<String, Object> request,
                        @RequestParam(value = "sid", required = false) Integer musicSingerId,
                        @RequestParam(value = "id", required = false) Integer id) {
        if(("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
            MusicAlbum musicAlbum = new MusicAlbum();
            if (id == null) {
                musicAlbum.setShowDate(new Date());
            } else {
                musicAlbum = musicAlbumService.getById(id);
                musicSingerId = musicAlbum.getMusicSingerId();
                List<Music> musics = musicService.queryForList(id, null, null, null, null);
                request.put("musics", musics);
            }
            request.put("musicAlbum", musicAlbum);
            MusicSinger musicSinger = musicSingerService.getById(musicSingerId);
            request.put("musicSinger", musicSinger);
            return "music/music_album_input";
        }
        return null;
    }

    @Token(remove = true)
    @RequestMapping(value = "{save}", method = RequestMethod.POST)
    public String save(@PathVariable("save") String save, MusicAlbum musicAlbum,
                       Integer[] mid, String[] ttl, String[] adr, Integer[] size) {
        Integer id = musicAlbum.getId();
        if(("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
            int musicSingerId = musicAlbum.getMusicSingerId();
            musicAlbumService.save(musicAlbum, mid, ttl, adr, size);

            // 关联上传的文件
            List<String> filePaths = new ArrayList<>();
            filePaths.add(musicAlbum.getThumb());
            filePaths.add(musicAlbum.getBanner());
            filePaths.addAll(Arrays.asList(adr));
            AttachIndex attachIndex = new AttachIndex();
            attachIndex.setModule("music");
            attachIndex.setHost("music_album-" + musicAlbum.getId());
            attachIndexService.save(filePaths, attachIndex);
            return "redirect:/music/MusicAlbum/list?sid=" + musicSingerId;
        }
        return null;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Integer id, @RequestParam("sid") Integer musicSingerId) {
        musicAlbumService.delete(id);
        return "redirect:/music/MusicAlbum/list?sid=" + musicSingerId;
    }

}
