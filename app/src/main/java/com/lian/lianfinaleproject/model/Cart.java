package com.lian.lianfinaleproject.model;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

// Collection of items
public class Cart {
    protected List<Item> itemList;

    public Cart(List<Item> itemList) {
        this.itemList = itemList;
    }

    public Cart() {
        this.itemList = new ArrayList<>();
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public void addItem(Item item) {
        this.itemList.add(item);
    }

    @NonNull
    @Override
    public String toString() {
        return "Cart{" +
                "itemUserlist=" + itemList +
                '}';
    }
}
