package cn.jaly.movie.controller;

import cn.jaly.movie.entity.MovieAttribute;
import cn.jaly.movie.service.MovieAttributeService;
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
@RequestMapping("/movie/MovieAttribute/")
public class MovieAttributeHandler {

    @Autowired
    private MovieAttributeService movieAttributeService;

    @RequestMapping("list")
    public String list(Map<String, Object> request,
                       @RequestParam("name") String name) {
        List<MovieAttribute> movieAttributes = movieAttributeService.getByAttrName(name);
        request.put("movieAttributes", movieAttributes);
        return "movie/movie_attribute_list";
    }

    @Token(save = true)
    @RequestMapping(value = "{input}", method = RequestMethod.GET)
    public String input(@PathVariable("input") String input, Map<String, Object> request,
                        @RequestParam(value = "name", required = false) String name,
                        @RequestParam(value = "id", required = false) Integer id) {
        if(("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
            MovieAttribute movieAttribute = new MovieAttribute();
            if (id == null) {
                movieAttribute.setAttrName(name);
            } else {
                movieAttribute = movieAttributeService.getById(id);
            }
            request.put("movieAttribute", movieAttribute);
            return "movie/movie_attribute_input";
        }
        return null;
    }

    @Token(remove = true)
    @RequestMapping(value = "{save}", method = RequestMethod.POST)
    public String save(@PathVariable("save") String save, MovieAttribute movieAttribute) {
        Integer id = movieAttribute.getId();
        if(("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
            String name = movieAttribute.getAttrName();
            movieAttributeService.save(movieAttribute);
            return "redirect:/movie/MovieAttribute/list?name=" + name;
        }
        return null;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Integer id, @RequestParam("name") String name) {
        movieAttributeService.delete(id);
        return "redirect:/movie/MovieAttribute/list?name=" + name;
    }
}
