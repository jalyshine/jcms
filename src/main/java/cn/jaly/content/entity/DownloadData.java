package cn.jaly.content.entity;

import cn.jaly.utils.caches.ListOfJson;
import com.google.gson.Gson;

import java.util.List;

public class DownloadData {
    private Integer downloadId;

    private Boolean allowComment;

    private String allowGroups;

    private List<Integer> groupIds;

    private Integer hits;

    private Integer readPoint;

    private String recommendPositions;

    private List<Integer> positionIds;

    public Integer getDownloadId() {
        return downloadId;
    }

    public void setDownloadId(Integer downloadId) {
        this.downloadId = downloadId;
    }

    public Boolean getAllowComment() {
        return allowComment;
    }

    public void setAllowComment(Boolean allowComment) {
        this.allowComment = allowComment;
    }

    public String getAllowGroups() {
        return allowGroups;
    }

    public void setAllowGroups(String allowGroups) {
        this.allowGroups = allowGroups == null ? null : allowGroups.trim();
        Gson gson = new Gson();
        this.groupIds = gson.fromJson(allowGroups, new ListOfJson<Integer>(Integer.class));
    }

    public List<Integer> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<Integer> groupIds) {
        this.groupIds = groupIds;
        Gson gson = new Gson();
        this.allowGroups = gson.toJson(groupIds);
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Integer getReadPoint() {
        return readPoint;
    }

    public void setReadPoint(Integer readPoint) {
        this.readPoint = readPoint;
    }

    public String getRecommendPositions() {
        return recommendPositions;
    }

    public void setRecommendPositions(String recommendPositions) {
        this.recommendPositions = recommendPositions == null ? null : recommendPositions.trim();
        Gson gson = new Gson();
        this.positionIds = gson.fromJson(recommendPositions, new ListOfJson<Integer>(Integer.class));
    }

    public List<Integer> getPositionIds() {
        return positionIds;
    }

    public void setPositionIds(List<Integer> positionIds) {
        this.positionIds = positionIds;
        Gson gson = new Gson();
        this.recommendPositions = gson.toJson(positionIds);
    }
}