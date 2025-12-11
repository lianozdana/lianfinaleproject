package com.lian.lianfinaleproject.model;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    protected List<Item> itemUserlist;

    public Cart(List<Item> itemUserlist) {
        this.itemUserlist = itemUserlist;
    }

    public Cart() {
        this.itemUserlist = new ArrayList<>();
    }

    public List<Item> getItemUserlist() {
        return itemUserlist;
    }

    public void setItemUserlist(List<Item> itemUserlist) {
        this.itemUserlist = itemUserlist;
    }

    public void addItem(Item item) {
        this.itemUserlist.add(item);
    }

    @NonNull
    @Override
    public String toString() {
        return "Cart{" +
                "itemUserlist=" + itemUserlist +
                '}';
    }
}
