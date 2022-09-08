package com.codecool.dungeoncrawl.model;


import com.codecool.dungeoncrawl.game.Items.Item;

public class ItemModel extends BaseModel {
    private String itemName;
    private int x;
    private int y;
    private String itemDescription;
    private int itemValue;

    private boolean itemWore;

    public boolean isItemWore() {
        return itemWore;
    }

    public void setItemWore(boolean itemWore) {
        this.itemWore = itemWore;
    }

    public ItemModel(String itemName, int x, int y, boolean isWore){
        this.itemName = itemName;
        this.x = x;
        this.y = y;
        this.itemWore = isWore;
    }

    public ItemModel(Item item, boolean isWore) {
        this.itemName = item.getItemName();
        this.x = item.getX();
        this.y = item.getY();
        this.itemDescription = item.getItemDescription();
        this.itemValue = item.getItemValue();
        this.itemWore = isWore;

    }

    public int getX() {
        return x;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getItemValue() {
        return itemValue;
    }

    public void setItemValue(int itemValue) {
        this.itemValue = itemValue;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


}