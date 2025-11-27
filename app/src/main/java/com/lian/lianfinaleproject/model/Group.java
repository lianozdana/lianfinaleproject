package com.lian.lianfinaleproject.model;

import java.util.List;

public class Group {
    protected String id;
    protected List<User> userlist;
    protected List<Item> itemlist;
    protected int code;
    protected User manager;

    public Group(String id, List<User> userlist, List<Item> itemlist, int code, User manager) {
        this.id = id;
        this.userlist = userlist;
        this.itemlist = itemlist;
        this.code = code;
        this.manager = manager;
    }

    public Group() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<User> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<User> userlist) {
        this.userlist = userlist;
    }

    public List<Item> getItemlist() {
        return itemlist;
    }

    public void setItemlist(List<Item> itemlist) {
        this.itemlist = itemlist;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id='" + id + '\'' +
                ", userlist=" + userlist +
                ", itemlist=" + itemlist +
                ", code=" + code +
                ", manager=" + manager +
                '}';
    }
}
