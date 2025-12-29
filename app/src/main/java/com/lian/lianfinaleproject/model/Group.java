package com.lian.lianfinaleproject.model;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Random;

public class Group {
    protected String id;
    protected String managerId; // user manager id
    protected List<String> userIdList; // list of user ids
    protected Cart cart;
    protected int code; // invite code

    public Group() {
        cart = new Cart();
    }

    public Group(String id, String managerId, List<String> userIdList, Cart cart, int code) {
        this.id = id;
        this.managerId = managerId;
        this.userIdList = userIdList;
        this.cart = cart;
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public List<String> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<String> userIdList) {
        this.userIdList = userIdList;
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

    @NonNull
    @Override
    public String toString() {
        return "Group{" +
                "id='" + id + '\'' +
                ", managerId='" + managerId + '\'' +
                ", userIdList=" + userIdList +
                ", cart=" + cart +
                ", code=" + code +
                '}';
    }

    public void addItemToCart(Item item) {
        this.cart.addItem(item);
    }


    public static int generateGroupCode() {
        Random random = new Random();
        int code = random.nextInt(1000);
        return code;
    }
}
