package cn.jaly.content.entity;

import java.util.ArrayList;
import java.util.List;

public class ContentMenu {

    private Integer id;
    private Byte type;
    private String name;
    private String tableName;
    private Integer listOrder;
    private List<ContentMenu> children;

    public ContentMenu(Category category){
        this.children = new ArrayList<>();
        this.name = category.getName();
        this.id = category.getId();
        this.tableName = "";
        this.type = category.getType();
        this.listOrder = category.getListOrder();
        if(type == 1){
            Model model = category.getModel();
            if(model != null){
                this.tableName = model.getTableName();
            }
        }
    }

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
        this.name = name;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<ContentMenu> getChildren() {
        return children;
    }

    public void setChildren(List<ContentMenu> children) {
        this.children = children;
    }

    public Integer getListOrder() {
        return listOrder;
    }

    public void setListOrder(Integer listOrder) {
        this.listOrder = listOrder;
    }

}
