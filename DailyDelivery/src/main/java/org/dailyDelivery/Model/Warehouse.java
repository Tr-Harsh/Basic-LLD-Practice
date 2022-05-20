package org.dailyDelivery.Model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Warehouse {
    private String warehouseID;
    private List<Category> categories;

    public Warehouse(String warehouseID, List<Category> categories) {
        this.warehouseID = warehouseID;
        this.categories = categories;
    }

    public Warehouse() {
    }

    public String getWarehouseID() {
        return warehouseID;
    }

    public void setWarehouseID(String warehouseID) {
        this.warehouseID = warehouseID;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
