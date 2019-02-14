package cn.jaly.music.entity;

import java.util.Date;

public class Music {
    private Integer id;

    private String title;

    private String url;

    private Integer size;

    private Integer timeLength;

    private Integer hits;

    private Boolean elite;

    private Date updateTime;

    private Integer musicAlbumId;

    private MusicAlbum musicAlbum;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(Integer timeLength) {
        this.timeLength = timeLength;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getMusicAlbumId() {
        return musicAlbumId;
    }

    public void setMusicAlbumId(Integer musicAlbumId) {
        this.musicAlbumId = musicAlbumId;
    }

    public MusicAlbum getMusicAlbum() {
        return musicAlbum;
    }

    public void setMusicAlbum(MusicAlbum musicAlbum) {
        this.musicAlbum = musicAlbum;
    }
}