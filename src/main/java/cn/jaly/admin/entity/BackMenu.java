package cn.jaly.admin.entity;

import java.io.Serializable;
import java.util.List;

public class BackMenu implements Serializable{
    private Integer id;

    private String name;

    private String icon;

    private String module;

    private String entity;

    private String action;

    private String data;

    private Integer listOrder;

    private Boolean display;

    private Byte depth;

    private String path;

    private Integer parentId;

    private List<BackMenu> childs;

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module == null ? null : module.trim();
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity == null ? null : entity.trim();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }

    public Integer getListOrder() {
        return listOrder;
    }

    public void setListOrder(Integer listOrder) {
        this.listOrder = listOrder;
    }

    public Boolean getDisplay() {
        return display;
    }

    public void setDisplay(Boolean display) {
        this.display = display;
    }

    public Byte getDepth() {
        return depth;
    }

    public void setDepth(Byte depth) {
        this.depth = depth;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<BackMenu> getChilds() {
        return childs;
    }

    public void setChilds(List<BackMenu> childs) {
        this.childs = childs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BackMenu menu = (BackMenu) o;

        if (module != null ? !module.equals(menu.module) : menu.module != null) return false;
        if (entity != null ? !entity.equals(menu.entity) : menu.entity != null) return false;
        if (action != null ? !action.equals(menu.action) : menu.action != null) return false;
        return data != null ? data.equals(menu.data) : menu.data == null;
    }

    @Override
    public int hashCode() {
        int result = module != null ? module.hashCode() : 0;
        result = 31 * result + (entity != null ? entity.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BackMenu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", childs=" + childs +
                '}';
    }
}