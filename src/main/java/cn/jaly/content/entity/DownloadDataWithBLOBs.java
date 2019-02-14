package cn.jaly.content.entity;

import cn.jaly.utils.caches.ListOfJson;
import com.google.gson.Gson;

import java.util.List;

public class DownloadDataWithBLOBs extends DownloadData {
    private String content;

    private String items;

    private List<DownloadItem> downloadItems;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items == null ? null : items.trim();
        Gson gson = new Gson();
        this.downloadItems = gson.fromJson(items, new ListOfJson<DownloadItem>(DownloadItem.class));
    }

    public List<DownloadItem> getDownloadItems() {
        return downloadItems;
    }

    public void setDownloadItems(List<DownloadItem> downloadItems) {
        this.downloadItems = downloadItems;
        Gson gson = new Gson();
        this.items = gson.toJson(downloadItems);
    }
}