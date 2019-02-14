package cn.jaly.content.entity;

import java.io.Serializable;

public class WorkFlow  implements Serializable {
    private Integer id;

    private String name;

    private String description;

    private Boolean allowModify;

    private Byte steps;

    private Integer siteId;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Boolean getAllowModify() {
        return allowModify;
    }

    public void setAllowModify(Boolean allowModify) {
        this.allowModify = allowModify;
    }

    public Byte getSteps() {
        return steps;
    }

    public void setSteps(Byte steps) {
        this.steps = steps;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }
}