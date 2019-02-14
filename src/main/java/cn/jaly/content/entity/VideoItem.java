package cn.jaly.content.entity;

/**
 * 单个视频
 */
public class VideoItem {

    private String name;     // 视频名称
    private Long size;       // 视频大小
    private String url;      // 视频地址
    private String desc;     // 视频简介

    public VideoItem(String name, Long size, String url, String desc) {
        this.name = name;
        this.size = size;
        this.url = url;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "VideoItem{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", url='" + url + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
