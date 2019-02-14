package cn.jaly.music.controller;

import cn.jaly.content.entity.AttachIndex;
import cn.jaly.content.service.AttachIndexService;
import cn.jaly.music.entity.Music;
import cn.jaly.music.entity.MusicAttribute;
import cn.jaly.music.entity.MusicSpecial;
import cn.jaly.music.service.MusicAttributeService;
import cn.jaly.music.service.MusicService;
import cn.jaly.music.service.MusicSpecialService;
import cn.jaly.utils.annotations.Token;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/music/MusicSpecial/")
public class MusicSpecialHandler {

    @Autowired
    private MusicSpecialService musicSpecialService;

    @Autowired
    private MusicAttributeService musicAttributeService;

    @Autowired
    private MusicService musicService;

    @Autowired
    private AttachIndexService attachIndexService;

    @RequestMapping("list")
    public String queryForList(Map<String, Object> request,
                               @RequestParam(value = "tid", required = false) Integer typeId,
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
        List<MusicSpecial> musicSpecials = musicSpecialService.queryForList(typeId, keyword, order);
        PageInfo page = new PageInfo(musicSpecials);
        request.put("page", page);

        List<MusicAttribute> types = musicAttributeService.getByAttrName("type");
        request.put("types", types);
        return "music/music_special_list";
    }

    @ResponseBody
    @RequestMapping(value = "check", method = RequestMethod.POST)
    public String check(@RequestParam("ttl") String title) {
        Integer id = musicSpecialService.getIdByTitle(title);
        return id == null ? "0" : id.toString();
    }

    @Token(save = true)
    @RequestMapping(value = "{input}", method = RequestMethod.GET)
    public String input(@PathVariable("input") String input, Map<String, Object> request,
                        @RequestParam(value = "id", required = false) Integer id) {
        if (("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
            List<MusicAttribute> types = musicAttributeService.getByAttrName("type");
            request.put("types", types);

            MusicSpecial musicSpecial = new MusicSpecial();
            if (id != null) {
                musicSpecial = musicSpecialService.getById(id);
                List<Integer> musicIds = musicSpecial.getMusicIds();
                if (musicIds != null && !musicIds.isEmpty()) {
                    List<Music> musics = musicService.queryByIds(musicIds);
                    request.put("musics", musics);
                }
            }
            request.put("musicSpecial", musicSpecial);
            return "music/music_special_input";
        }
        return null;
    }

    @Token(remove = true)
    @RequestMapping(value = "{save}", method = RequestMethod.POST)
    public String save(@PathVariable("save") String save,
                       MusicSpecial musicSpecial, Integer[] mid) {
        Integer id = musicSpecial.getId();
        if (("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
            if (mid != null) {
                musicSpecial.setMusicIds(Arrays.asList(mid));
            }
            musicSpecialService.save(musicSpecial);

            // 关联上传的图片
            List<String> filePaths = new ArrayList<>();
            filePaths.add(musicSpecial.getThumb());
            filePaths.add(musicSpecial.getBanner());
            AttachIndex attachIndex = new AttachIndex();
            attachIndex.setModule("music");
            attachIndex.setHost("music_special-" + musicSpecial.getId());
            attachIndexService.save(filePaths, attachIndex);
            return "redirect:/music/MusicSpecial/list";
        }
        return null;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Integer id) {
        musicSpecialService.delete(id);
        return "redirect:/music/MusicSpecial/list";
    }

    @RequestMapping(value = "load", method = RequestMethod.GET)
    public String load(Map<String, Object> request,
                       @RequestParam(value = "stm", required = false) String stm,
                       @RequestParam(value = "edm", required = false) String edm,
                       @RequestParam(value = "kwd", required = false) String keyword,
                       @RequestParam(value = "ps", required = false) Integer ps,
                       @RequestParam(value = "pn", required = false) Integer pn) {
        if (ps == null) {
            ps = 20;
        }
        if (pn == null) {
            pn = 1;
        }
        PageHelper.startPage(pn, ps);
        List<Music> musics = musicService.queryForList(null, keyword, stm, edm, null);
        PageInfo page = new PageInfo(musics);
        request.put("page", page);
        return "music/music_special_load";
    }

    @RequestMapping(value = "load", method = RequestMethod.POST)
    public String load(Integer id, Integer[] ids) {
        if (ids != null) {
            MusicSpecial musicSpecial = musicSpecialService.getById(id);
            List<Integer> musicIds = musicSpecial.getMusicIds();
            if (musicIds == null || musicIds.isEmpty()) {
                musicIds = Arrays.asList(ids);
            } else {
                for (Integer sid : ids) {
                    if (!musicIds.contains(sid)) {
                        musicIds.add(sid);
                    }
                }
            }
            musicSpecial.setMusicIds(musicIds);
            musicSpecialService.save(musicSpecial);
        }
        return "redirect:/music/MusicSpecial/list";
    }
}
