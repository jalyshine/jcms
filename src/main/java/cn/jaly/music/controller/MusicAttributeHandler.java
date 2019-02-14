package cn.jaly.music.controller;

import cn.jaly.music.entity.MusicAttribute;
import cn.jaly.music.service.MusicAttributeService;
import cn.jaly.utils.annotations.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/music/MusicAttribute/")
public class MusicAttributeHandler {

    @Autowired
    private MusicAttributeService musicAttributeService;

    @RequestMapping("list")
    public String list(Map<String, Object> request,
                       @RequestParam("name") String name) {
        List<MusicAttribute> musicAttributes = musicAttributeService.getByAttrName(name);
        request.put("musicAttributes", musicAttributes);
        return "music/music_attribute_list";
    }

    @Token(save = true)
    @RequestMapping(value = "{input}", method = RequestMethod.GET)
    public String input(@PathVariable("input") String input, Map<String, Object> request,
                        @RequestParam(value = "name", required = false) String name,
                        @RequestParam(value = "id", required = false) Integer id) {
        if(("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
            MusicAttribute musicAttribute = new MusicAttribute();
            if (id == null) {
                musicAttribute.setAttrName(name);
            } else {
                musicAttribute = musicAttributeService.getById(id);
            }
            request.put("musicAttribute", musicAttribute);
            return "music/music_attribute_input";
        }
        return null;
    }

    @Token(remove = true)
    @RequestMapping(value = "{save}", method = RequestMethod.POST)
    public String save(@PathVariable("save") String save, MusicAttribute musicAttribute) {
        Integer id = musicAttribute.getId();
        if(("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
            String name = musicAttribute.getAttrName();
            musicAttributeService.save(musicAttribute);
            return "redirect:/music/MusicAttribute/list?name=" + name;
        }
        return null;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Integer id, @RequestParam("name") String name) {
        musicAttributeService.delete(id);
        return "redirect:/music/MusicAttribute/list?name=" + name;
    }
}
