package cn.jaly.content.entity;

import java.util.Arrays;
import java.util.List;

public class AttachSetting {

    private Integer siteId;
    private Integer limitSize;     // 附件大小上限 单位KB
    private String allowExtension; // 允许上传的格式 jpg|gif|bmp|png...
    private Boolean enableMark;    // 启用水印
    private Integer markWidth;     // 水印宽度
    private Integer markHeight;    // 水印高度
    private String mark;           // 水印图片位置
    private Integer markOpacity;    // 水印透明度
    private Integer markQuality;   // 水印质量
    private Byte markPosition;     // 水印位置 0随机位置123上部456中部789下部

    /**
     * 初始化，若未设置，默认使用以下值
     */
    public AttachSetting(){
        this.limitSize = -1;
        this.allowExtension = "";
        this.enableMark =  false;
        this.markWidth = 0;
        this.markHeight = 0;
        this.mark = "";
        this.markOpacity = 0;
        this.markQuality = 0;
        this.markPosition = 0;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getLimitSize() {
        return limitSize;
    }

    public void setLimitSize(Integer limitSize) {
        this.limitSize = limitSize;
    }

    public String getAllowExtension() {
        return allowExtension;
    }

    public void setAllowExtension(String allowExtension) {
        this.allowExtension = allowExtension == null ? null : allowExtension.trim();
    }

    public List<String> getExtensionList(){
        if (allowExtension != null && !allowExtension.isEmpty()) {
            List<String> extList = Arrays.asList(allowExtension.split("\\|"));
            return extList;
        }
        return null;
    }

    public Boolean getEnableMark() {
        return enableMark;
    }

    public void setEnableMark(Boolean enableMark) {
        this.enableMark = enableMark;
    }

    public Integer getMarkWidth() {
        return markWidth;
    }

    public void setMarkWidth(Integer markWidth) {
        this.markWidth = markWidth;
    }

    public Integer getMarkHeight() {
        return markHeight;
    }

    public void setMarkHeight(Integer markHeight) {
        this.markHeight = markHeight;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }

    public Integer getMarkOpacity() {
        return markOpacity;
    }

    public void setMarkOpacity(Integer markOpacity) {
        this.markOpacity = markOpacity;
    }

    public Integer getMarkQuality() {
        return markQuality;
    }

    public void setMarkQuality(Integer markQuality) {
        this.markQuality = markQuality;
    }

    public Byte getMarkPosition() {
        return markPosition;
    }

    public void setMarkPosition(Byte markPosition) {
        this.markPosition = markPosition;
    }

    @Override
    public String toString() {
        return "AttachSetting{" +
                "siteId=" + siteId +
                ", limitSize=" + limitSize +
                ", allowExtension='" + allowExtension + '\'' +
                ", enableMark=" + enableMark +
                ", markWidth=" + markWidth +
                ", markHeight=" + markHeight +
                ", mark='" + mark + '\'' +
                ", markOpacity=" + markOpacity +
                ", markQuality=" + markQuality +
                ", markPosition=" + markPosition +
                '}';
    }
}