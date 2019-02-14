package cn.jaly.content.entity;

import cn.jaly.admin.entity.Site;
import com.google.gson.Gson;

import java.util.List;

public class Category {
    private Integer id;

    private String name;

    private String dirName;

    private Byte type;

    private String banner;

    private String thumb;

    private String icon;

    private String description;

    private Integer hits;

    private Integer listOrder;

    private String meta;

    private String style;

    private String url;

    private Integer parentId;

    private Integer modelId;

    private Integer workFlowId;

    private Integer siteId;

    private MetaOption metaOption;

    private StyleOption styleOption;

    private Byte level;

    private Model model;

    private WorkFlow workFlow;

    private Site site;

    private String privacyString;

    private List<Category> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName == null ? null : dirName.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner == null ? null : banner.trim();
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb == null ? null : thumb.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
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

    public Integer getListOrder() {
        return listOrder;
    }

    public void setListOrder(Integer listOrder) {
        this.listOrder = listOrder;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta == null ? null : meta.trim();
        this.metaOption = new Gson().fromJson(meta, MetaOption.class);
    }

    public MetaOption getMetaOption() {
        return metaOption;
    }

    public void setMetaOption(MetaOption metaOption) {
        this.metaOption = metaOption;
        this.meta = new Gson().toJson(metaOption);
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style == null ? null : style.trim();
        this.styleOption = new Gson().fromJson(style, StyleOption.class);
    }

    public StyleOption getStyleOption() {
        return styleOption;
    }

    public void setStyleOption(StyleOption styleOption) {
        this.styleOption = styleOption;
        this.style = new Gson().toJson(styleOption);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getWorkFlowId() {
        return workFlowId;
    }

    public void setWorkFlowId(Integer workFlowId) {
        this.workFlowId = workFlowId;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public WorkFlow getWorkFlow() {
        return workFlow;
    }

    public void setWorkFlow(WorkFlow workFlow) {
        this.workFlow = workFlow;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    public String getPrivacyString() {
        return privacyString;
    }

    public void setPrivacyString(String privacyString) {
        this.privacyString = privacyString;
    }
}