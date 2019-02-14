package cn.jaly.content.entity;

public class DownloadItem {

    private String name; // 下载名称
    private Long size;   // 软件大小
    private String url;  // 下载地址

    public DownloadItem(String name, Long size, String url) {
        this.name = name;
        this.size = size;
        this.url = url;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
