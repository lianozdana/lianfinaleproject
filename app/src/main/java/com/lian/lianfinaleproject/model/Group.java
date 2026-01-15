package com.lian.lianfinaleproject.model;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Random;

public class Group {
    protected String id;

    protected  String name;
    protected User manager; // user manager id
    protected List<User> userList; // list of user ids
    protected Cart cart;
    protected int code; // invite code

    public Group() {
        cart = new Cart();
    }

    public Group(String id, String name, User manager, List<User> userList, Cart cart, int code) {
        this.id = id;
        this.name = name;
        this.manager = manager;
        this.userList = userList;
        this.cart = cart;
        this.code = code;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }



    public void addItemToCart(Item item) {
        this.cart.addItem(item);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int generateGroupCode() {
        Random random = new Random();
        int code = random.nextInt(1000);
        return code;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", manager=" + manager +
                ", userList=" + userList +
                ", cart=" + cart +
                ", code=" + code +
                '}';
    }
}
