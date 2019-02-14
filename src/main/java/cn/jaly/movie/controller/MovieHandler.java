package cn.jaly.movie.controller;

import cn.jaly.content.entity.AttachIndex;
import cn.jaly.content.service.AttachIndexService;
import cn.jaly.movie.entity.Movie;
import cn.jaly.movie.entity.MovieAttribute;
import cn.jaly.movie.entity.MovieData;
import cn.jaly.movie.entity.MovieItem;
import cn.jaly.movie.service.MovieAttributeService;
import cn.jaly.movie.service.MovieService;
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
@RequestMapping("/movie/Movie/")
public class MovieHandler {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieAttributeService movieAttributeService;

    @Autowired
    private AttachIndexService attachIndexService;

    @RequestMapping("list")
    public String list(Map<String, Object> request,
                       @RequestParam(value = "aid", required = false) Integer areaId,
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
        List<Movie> movies = movieService.queryForList(keyword, areaId, typeId, order);
        PageInfo page = new PageInfo(movies);
        request.put("page", page);
        List<MovieAttribute> types = movieAttributeService.getByAttrName("type");
        List<MovieAttribute> areas = movieAttributeService.getByAttrName("area");
        request.put("types", types);
        request.put("areas", areas);
        return "movie/movie_list";
    }

    /**
     * 检查标题是否重名
     *
     * @param title
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "check", method = RequestMethod.POST)
    public String check(@RequestParam("ttl") String title) {
        Integer id = movieService.getIdByTitle(title);
        return id == null ? "0" : id.toString();
    }

    @Token(save = true)
    @RequestMapping(value = "{input}", method = RequestMethod.GET)
    public String input(@PathVariable("input") String input, Map<String, Object> request,
                        @RequestParam(value = "id", required = false) Integer id) {
        if(("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
            request.put("types", movieAttributeService.getByAttrName("type"));
            request.put("areas", movieAttributeService.getByAttrName("area"));
            request.put("paths", movieAttributeService.getByAttrName("path"));

            Movie movie = new Movie();
            if (id == null) {
                movie.setShowDate(new Date());
                movie.setTimeLength(0);
                MovieData movieData = new MovieData();
                movieData.setReadPoint(0);
                movie.setMovieData(movieData);
            } else {
                movie = movieService.getById(id);
            }
            request.put("movie", movie);
            return "movie/movie_input";
        }
        return null;
    }

    @Token(remove = true)
    @RequestMapping(value = "{save}", method = RequestMethod.POST)
    public String save(@PathVariable("save") String save,
                       Movie movie, String[] name, String[] url1, String[] url2) {
        Integer id = movie.getId();
        if(("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
            if (name != null) {
                List<MovieItem> movieItems = new ArrayList<>();
                for (int i = 0; i < name.length; i++) {
                    MovieItem movieItem = new MovieItem(name[i], url1[i], url2[i]);
                    movieItems.add(movieItem);
                }
                movie.getMovieData().setMovieItems(movieItems);
            }
            movieService.save(movie);

            // 关联上传的图片
            List<String> filePaths = new ArrayList<>();
            filePaths.add(movie.getThumb());
            filePaths.add(movie.getBanner());
            AttachIndex attachIndex = new AttachIndex();
            attachIndex.setModule("movie");
            attachIndex.setHost("movie-" + movie.getId());
            attachIndexService.save(filePaths, attachIndex);
            return "redirect:/movie/Movie/list";
        }
        return null;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Integer id) {
        movieService.delete(id);
        return "redirect:/movie/Movie/list";
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
                    movieService.delete(ids);
                    flag = true;
                    break;
                default: break;
            }
        }
        return flag?"redirect:/movie/Movie/list":null;
    }
}
