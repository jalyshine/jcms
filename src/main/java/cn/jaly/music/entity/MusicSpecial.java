package cn.jaly.music.entity;

import cn.jaly.utils.caches.ListOfJson;
import com.google.gson.Gson;

import java.util.Date;
import java.util.List;

public class MusicSpecial {
    private Integer id;

    private String title;

    private String thumb;

    private String banner;

    private String description;

    private Integer hits;

    private Boolean elite;

    private Date updateTime;

    private Integer typeId;

    private String items;

    private MusicAttribute type;

    private List<Integer> musicIds;

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

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items == null ? null : items.trim();
        Gson gson = new Gson();
        this.musicIds = gson.fromJson(items, new ListOfJson<Integer>(Integer.class));
    }

    public MusicAttribute getType() {
        return type;
    }

    public void setType(MusicAttribute type) {
        this.type = type;
    }

    public List<Integer> getMusicIds() {
        return musicIds;
    }

    public void setMusicIds(List<Integer> musicIds) {
        this.musicIds = musicIds;
        Gson gson = new Gson();
        this.items = gson.toJson(musicIds);
    }
}