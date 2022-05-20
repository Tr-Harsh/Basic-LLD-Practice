package org.dailyDelivery.Model;


import org.springframework.stereotype.Component;

@Component
public class Items {
    private Integer itemId;
    private String itemName;
    private Integer quantity;

    public Items() {
    }

    public Items(Integer itemId, String itemName, Integer quantity) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
