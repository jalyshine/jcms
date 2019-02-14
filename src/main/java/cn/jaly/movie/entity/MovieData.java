package cn.jaly.movie.entity;

import cn.jaly.utils.caches.ListOfJson;
import com.google.gson.Gson;

import java.util.List;

public class MovieData {
    private Integer movieId;

    private Integer readPoint;

    private Integer hits;

    private Boolean allowComment;

    private String commonPath;

    private String items;

    private List<MovieItem> movieItems;

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getReadPoint() {
        return readPoint;
    }

    public void setReadPoint(Integer readPoint) {
        this.readPoint = readPoint;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Boolean getAllowComment() {
        return allowComment;
    }

    public void setAllowComment(Boolean allowComment) {
        this.allowComment = allowComment;
    }

    public String getCommonPath() {
        return commonPath;
    }

    public void setCommonPath(String commonPath) {
        this.commonPath = commonPath == null ? null : commonPath.trim();
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items == null ? null : items.trim();
        Gson gson = new Gson();
        this.movieItems = gson.fromJson(items, new ListOfJson<MovieItem>(MovieItem.class));
    }

    public List<MovieItem> getMovieItems() {
        return movieItems;
    }

    public void setMovieItems(List<MovieItem> movieItems) {
        this.movieItems = movieItems;
        Gson gson = new Gson();
        this.items = gson.toJson(movieItems);
    }
}