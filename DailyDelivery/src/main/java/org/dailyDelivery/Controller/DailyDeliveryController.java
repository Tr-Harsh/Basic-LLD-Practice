package org.dailyDelivery.Controller;

import org.dailyDelivery.Dto.ItemRequest;
import org.dailyDelivery.Dto.OrderRequest;
import org.dailyDelivery.Dto.ResponseWrapper;
import org.dailyDelivery.Model.Category;
import org.dailyDelivery.Model.Items;
import org.dailyDelivery.Model.Storage;
import org.dailyDelivery.Model.Warehouse;
import org.dailyDelivery.Service.OrderFulfilmentImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("delivery")
public class DailyDeliveryController {

    @Autowired
    OrderFulfilmentImpl orderFulfilment;

    @PostMapping("saveOrder")
    public ResponseEntity<Object> saveOrder(@RequestBody OrderRequest orderRequest){
        orderFulfilment = saveOrderUtil(orderRequest);
        if(orderFulfilment!=null){
            return ResponseEntity
                    .created(URI.create(orderRequest.getCustomerId()))
                    .body(new ResponseWrapper("SUCCESS","Order saved"));
        }
        return ResponseEntity
                .created(URI.create(orderRequest.getCustomerId()))
                .body(new ResponseWrapper("FAILURE","Order not saved"));

    }

    @PostMapping("canFulfilorder")
    public ResponseEntity<Object> canFulfilOrder(@RequestBody OrderRequest orderRequest){
        if(this.orderFulfilment.canFulfilOrder(orderRequest)){
            return ResponseEntity
                    .created(URI.create(orderRequest.getCustomerId()))
                    .body(new ResponseWrapper("SUCCESS","Order can be fulfilled"));
        }
        return ResponseEntity
                .created(URI.create(orderRequest.getCustomerId()))
                .body(new ResponseWrapper("FAILURE","Order cannot be fulfilled"));

    }

    @PostMapping("reserveOrder")
    public ResponseEntity<Object> canReserveOrder(@RequestBody OrderRequest orderRequest){
        try {
            this.orderFulfilment.reserveOrder(orderRequest);
            return ResponseEntity
                    .created(URI.create(orderRequest.getCustomerId()))
                    .body(new ResponseWrapper("SUCCESS","Order reserved"));
        }
        catch (Exception e){
            return ResponseEntity
                    .created(URI.create(orderRequest.getCustomerId()))
                    .body(new ResponseWrapper("FAILURE","Order cannot be reserved"));
        }
    }


    private OrderFulfilmentImpl saveOrderUtil(OrderRequest orderRequest) {

        try {
            Map<String, List<Items>> categoryMap = new HashMap<>();
            for(ItemRequest itemRequest : orderRequest.getItems()){
                Items item = new Items(itemRequest.getItemId(), itemRequest.getItemName(), itemRequest.getQuantity());
                categoryMap.computeIfAbsent(itemRequest.getCategory(), k-> new ArrayList<>()).add(item);
            }

            List<Category> categories = new ArrayList<>();
            categoryMap.forEach((catName,i)->{
                Category category = new Category(catName,150,i);
                categories.add(category);
            });

            String date = orderRequest.getDeliveryDate();
            Warehouse warehouse = new Warehouse(orderRequest.getWarehouseId(), categories);
            List<Warehouse> warehouses = new ArrayList<>();
            warehouses.add(warehouse);

            Map<String, List<Warehouse> > warehouseMap =  new HashMap<>();
            warehouseMap.put(date,warehouses);
            Storage storage = new Storage(warehouseMap);
            return new OrderFulfilmentImpl(storage);
        }
        catch (Exception e){
            return null;
        }
    }


}
