package com.example.basicwebshop.models;

import java.util.ArrayList;
import java.util.List;

public class WebShop {
    private List<ShopItem> shopItems;

    public WebShop() {
        this.shopItems = new ArrayList<>();
    }

    public void add(ShopItem item) {
        shopItems.add(item);
    }

    public List<ShopItem> getShopItems() {
        return shopItems;
    }

    public void setShopItems(List<ShopItem> shopItems) {
        this.shopItems = shopItems;
    }
}
