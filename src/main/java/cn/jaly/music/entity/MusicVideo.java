package cn.jaly.music.entity;

import cn.jaly.utils.common.DateTimeUtils;

import java.util.Date;

public class MusicVideo {
    private Integer id;

    private String title;

    private String thumb;

    private String banner;

    private String url;

    private Integer timeLength;

    private String description;

    private Integer hits;

    private Boolean elite;

    private Date showDate;

    private String showDateStr;

    private Date updateTime;

    private Integer musicSingerId;

    private MusicSinger musicSinger;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(Integer timeLength) {
        this.timeLength = timeLength;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Boolean getElite() {
        return elite;
    }

    public void setElite(Boolean elite) {
        this.elite = elite;
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

    public Integer getMusicSingerId() {
        return musicSingerId;
    }

    public void setMusicSingerId(Integer musicSingerId) {
        this.musicSingerId = musicSingerId;
    }

    public MusicSinger getMusicSinger() {
        return musicSinger;
    }

    public void setMusicSinger(MusicSinger musicSinger) {
        this.musicSinger = musicSinger;
    }
}