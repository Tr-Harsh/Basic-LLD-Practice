package org.dailyDelivery.Dto;

import java.util.List;

public class OrderRequest {
    private String customerId;
    private String warehouseId;
    private String deliveryDate;
    private List<ItemRequest> items;

    public OrderRequest() {
    }

    public OrderRequest(String customerId, String warehouseId, String deliveryDate, List<ItemRequest> items) {
        this.customerId = customerId;
        this.warehouseId = warehouseId;
        this.deliveryDate = deliveryDate;
        this.items = items;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public List<ItemRequest> getItems() {
        return items;
    }

    public void setItems(List<ItemRequest> items) {
        this.items = items;
    }
}
