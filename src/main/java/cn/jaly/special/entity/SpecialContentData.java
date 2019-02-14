package cn.jaly.special.entity;

public class SpecialContentData {
    private Integer specialContentId;

    private String author;

    private String authorUnit;

    private Integer hits;

    private Integer maxCharPerPage;

    private Byte pageType;

    private String title1;

    private String title2;

    private String content;

    public Integer getSpecialContentId() {
        return specialContentId;
    }

    public void setSpecialContentId(Integer specialContentId) {
        this.specialContentId = specialContentId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getAuthorUnit() {
        return authorUnit;
    }

    public void setAuthorUnit(String authorUnit) {
        this.authorUnit = authorUnit == null ? null : authorUnit.trim();
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Integer getMaxCharPerPage() {
        return maxCharPerPage;
    }

    public void setMaxCharPerPage(Integer maxCharPerPage) {
        this.maxCharPerPage = maxCharPerPage;
    }

    public Byte getPageType() {
        return pageType;
    }

    public void setPageType(Byte pageType) {
        this.pageType = pageType;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1 == null ? null : title1.trim();
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2 == null ? null : title2.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String toString() {
        return "SpecialContentData{" +
                "specialContentId=" + specialContentId +
                ", author='" + author + '\'' +
                ", authorUnit='" + authorUnit + '\'' +
                ", hits=" + hits +
                ", maxCharPerPage=" + maxCharPerPage +
                ", pageType=" + pageType +
                ", title1='" + title1 + '\'' +
                ", title2='" + title2 + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}