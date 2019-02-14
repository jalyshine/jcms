package cn.jaly.movie.entity;

import cn.jaly.utils.common.DateTimeUtils;

import java.util.Date;

public class Movie {
    private Integer id;

    private String title;

    private String director;

    private String actor;

    private String thumb;

    private String banner;

    private String description;

    private Byte quality;

    private Integer timeLength;

    private Date showDate;

    private String showDateStr;

    private Date updateTime;

    private Integer typeId;

    private Integer areaId;

    private MovieAttribute type;

    private MovieAttribute area;

    private MovieData movieData;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director == null ? null : director.trim();
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor == null ? null : actor.trim();
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb == null ? null : thumb.trim();
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner == null ? null : banner.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Byte getQuality() {
        return quality;
    }

    public void setQuality(Byte quality) {
        this.quality = quality;
    }

    public Integer getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(Integer timeLength) {
        this.timeLength = timeLength;
    }

    public Date getShowDate() {
        return showDate;
    }

    public void setShowDate(Date showDate) {
        this.showDate = showDate;
        this.showDateStr = DateTimeUtils.formatSimple(showDate);
    }

    public String getShowDateStr() {
        return showDateStr;
    }

    public void setShowDateStr(String showDateStr) {
        this.showDateStr = showDateStr;
        this.showDate = DateTimeUtils.parseSimple(showDateStr);
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public MovieAttribute getType() {
        return type;
    }

    public void setType(MovieAttribute type) {
        this.type = type;
    }

    public MovieAttribute getArea() {
        return area;
    }

    public void setArea(MovieAttribute area) {
        this.area = area;
    }

    public MovieData getMovieData() {
        return movieData;
    }

    public void setMovieData(MovieData movieData) {
        this.movieData = movieData;
    }
}