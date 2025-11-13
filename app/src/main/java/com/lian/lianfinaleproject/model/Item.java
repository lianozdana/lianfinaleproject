package com.lian.lianfinaleproject.model;

public class Item {
    protected String id;
    protected String name;
    protected String type;
    protected String pic;
    protected String company;
    protected String size;
    protected boolean isChecked;

    public Item(String id, String name, String type, String pic, String company, String size, boolean isChecked) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.pic = pic;
        this.company = company;
        this.size = size;
        this.isChecked = isChecked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public Item() {
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", pic='" + pic + '\'' +
                ", company='" + company + '\'' +
                ", size='" + size + '\'' +
                ", isChecked=" + isChecked +
                '}';
    }
}
