package cn.jaly.content.entity;

import cn.jaly.utils.caches.ListOfJson;
import cn.jaly.utils.common.BasicUtils;
import com.google.gson.Gson;

import java.util.List;

public class ArticleData{
    private Integer articleId;

    private Boolean allowComment;

    private String allowGroups;

    private List<Integer> groupIds;

    private Integer hits;

    private Byte pageType;

    private Integer maxCharPerPage;

    private Integer readPoint;

    private String recommendPositions;

    private List<Integer> positionIds;

    private Integer copyFromId;

    private Integer voteId;

    private String content;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
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

    public Byte getPageType() {
        return pageType;
    }

    public void setPageType(Byte pageType) {
        this.pageType = pageType;
    }

    public Integer getMaxCharPerPage() {
        return maxCharPerPage;
    }

    public void setMaxCharPerPage(Integer maxCharPerPage) {
        this.maxCharPerPage = maxCharPerPage;
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
        this.positionIds = BasicUtils.deIdList(recommendPositions);
    }

    public List<Integer> getPositionIds() {
        return positionIds;
    }

    public void setPositionIds(List<Integer> positionIds) {
        this.positionIds = positionIds;
        this.recommendPositions = BasicUtils.enIdList(positionIds);
    }

    public Integer getCopyFromId() {
        return copyFromId;
    }

    public void setCopyFromId(Integer copyFromId) {
        this.copyFromId = copyFromId;
    }

    public Integer getVoteId() {
        return voteId;
    }

    public void setVoteId(Integer voteId) {
        this.voteId = voteId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}