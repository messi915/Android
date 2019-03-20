package com.example.zeeshanaslam.fyp;

public class ItemModel {
    private int itemId;
    private String name;
    private String price;
    private boolean isSelected;

    public ItemModel(int itemId, String name, String price,boolean isSelected) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.isSelected =isSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
