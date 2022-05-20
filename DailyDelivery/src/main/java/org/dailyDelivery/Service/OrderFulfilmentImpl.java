package org.dailyDelivery.Service;

import com.sun.xml.internal.xsom.XSUnionSimpleType;
import org.dailyDelivery.Dto.ItemRequest;
import org.dailyDelivery.Exceptions.OrderReservationException;
import org.dailyDelivery.Dto.OrderRequest;
import org.dailyDelivery.Model.Category;
import org.dailyDelivery.Model.Items;
import org.dailyDelivery.Model.Storage;
import org.dailyDelivery.Model.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class OrderFulfilmentImpl implements OrderFulfilmentService{

    @Autowired
    Storage storage;

    public OrderFulfilmentImpl(Storage storage) {
        this.storage = storage;
    }

    @Override
    public Boolean canFulfilOrder(OrderRequest orderRequest) {

        String DeliveryDate = orderRequest.getDeliveryDate();
        List<Warehouse> warehouseList = storage.getWarehouses().get(DeliveryDate);
        Warehouse warehouse = findWarehouseByID(warehouseList,orderRequest.getWarehouseId());
        if(warehouse!=null){
            Map<String, Integer> categoryQuantity = new HashMap<>();
            for(ItemRequest itemRequest : orderRequest.getItems()){
                Category category = findCategorybyName(warehouse.getCategories(), itemRequest.getCategory());
                if(category==null) {
                    System.out.println("Category is NULL " + itemRequest.getCategory());
                    return false;
                }

                Integer catQuant = categoryQuantity.getOrDefault(category.getName(), 0)+ itemRequest.getQuantity();
                categoryQuantity.put(category.getName(),catQuant);
                if(category.getLimit()>=categoryQuantity.getOrDefault(category.getName(),0)){
                    Items item = findItemByID(category.getItems(), itemRequest.getItemId());
                    if(item!=null){
                        if(item.getQuantity()<itemRequest.getQuantity()){
                            System.out.println("Item quantity is less in warehouse "
                                    + itemRequest.getQuantity() + ">" + item.getQuantity());
                            return false;
                        }
                    }
                    else {
                        System.out.println("Item is NULL " + itemRequest.getItemName());
                        return false;
                    }
                }
                else {
                    System.out.println("Category Quantity limit exceeded " + category.getLimit());
                    return false;
                }
            }
        }
        else {
            System.out.println("Warehouse is NULL " + orderRequest.getWarehouseId());
            return false;
        }

        return true;

    }

    private Items findItemByID(List<Items> items, Integer itemId) {
        for(Items item : items){
            if(item.getItemId().equals(itemId)) return  item;
        }
        return null;
    }

    private Category findCategorybyName(List<Category> categories, String category) {
        for(Category eachCategory : categories){
            if(eachCategory.getName().equals(category)) return  eachCategory;
        }
        return null;
    }

    private Warehouse findWarehouseByID(List<Warehouse> warehouseList, String warehouseID){
        for(Warehouse warehouse : warehouseList){
            if(warehouse.getWarehouseID().equals(warehouseID)) return warehouse;
        }
        return null;
    }

    @Override
    public void reserveOrder(OrderRequest orderRequest) throws OrderReservationException {
        if (canFulfilOrder(orderRequest)) {
            String DeliveryDate = orderRequest.getDeliveryDate();
            List<Warehouse> warehouseList = storage.getWarehouses().get(DeliveryDate);
            Warehouse warehouse = findWarehouseByID(warehouseList, orderRequest.getWarehouseId());
            if (warehouse != null) {
                for (ItemRequest itemRequest : orderRequest.getItems()) {
                    Category category = findCategorybyName(warehouse.getCategories(), itemRequest.getCategory());
                    Items item = findItemByID(category.getItems(), itemRequest.getItemId());
                    if(item.getQuantity()-itemRequest.getQuantity()<0) throw new OrderReservationException();
                    else item.setQuantity(item.getQuantity()-itemRequest.getQuantity());
                }
            }
        } else throw new OrderReservationException();
    }


}
