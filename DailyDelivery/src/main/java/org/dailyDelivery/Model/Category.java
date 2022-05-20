package org.dailyDelivery.Model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Category {
    private String name;
    private Integer limit;
    private List<Items> items;

    public Category(String name, Integer limit, List<Items> items) {
        this.name = name;
        this.limit = limit;
        this.items = items;
    }

    public Category() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }
}
