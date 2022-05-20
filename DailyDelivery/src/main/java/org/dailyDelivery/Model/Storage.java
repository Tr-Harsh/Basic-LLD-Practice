package org.dailyDelivery.Model;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Storage {
    Map<String , List<Warehouse> > warehouses = new HashMap<>();

    public Storage(Map<String, List<Warehouse>> warehouses) {
        this.warehouses = warehouses;
    }

    public Storage() {
    }

    public Map<String, List<Warehouse>> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(Map<String, List<Warehouse>> warehouses) {
        this.warehouses = warehouses;
    }
}
