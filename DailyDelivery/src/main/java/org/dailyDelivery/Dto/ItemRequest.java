package org.dailyDelivery.Dto;

public class ItemRequest {
    private Integer itemId;
    private String itemName;
    private String category;
    private Integer quantity;

    public ItemRequest() {
    }

    public ItemRequest(Integer itemId, String itemName, String category, Integer quantity) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.category = category;
        this.quantity = quantity;
    }

    public Integer getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getCategory() {
        return category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
