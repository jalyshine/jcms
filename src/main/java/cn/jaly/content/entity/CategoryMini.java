package cn.jaly.content.entity;

public class CategoryMini {

    private Integer id;
    private String name;
    private Byte level;

    public CategoryMini() {

    }

    public CategoryMini(Integer id, String name, Byte level) {
        this.id = id;
        this.name = name;
        this.level = level;
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

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }
}
