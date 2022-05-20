package org.dailyDelivery;

import org.dailyDelivery.Dto.ItemRequest;
import org.dailyDelivery.Dto.OrderRequest;
import org.dailyDelivery.Exceptions.OrderReservationException;
import org.dailyDelivery.Model.Category;
import org.dailyDelivery.Model.Items;
import org.dailyDelivery.Model.Storage;
import org.dailyDelivery.Model.Warehouse;
import org.dailyDelivery.Service.OrderFulfilmentImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class App 
{
    public static void main( String[] args ) throws OrderReservationException {

/*
        Items apple = new Items(1,"apple",100);
        Items banana = new Items(2,"banana",50);
        List<Items> items1 = new ArrayList<>();
        items1.add(apple); items1.add(banana);

        Items juice = new Items(3,"juice",100);
        Items biscuit = new Items(4,"biscuit",10);
        List<Items> items2 = new ArrayList<>();
        items2.add(juice); items2.add(biscuit);

        Category FNV =  new Category("FNV",120,items1);
        Category grocery = new Category("grocery",150,items2);
        List<Category> categories = new ArrayList<>();
        categories.add(FNV); categories.add(grocery);

        Warehouse warehouse1 = new Warehouse("100",categories);
        String date = "2020-10-13";
        List<Warehouse> warehouses = new ArrayList<>();
        warehouses.add(warehouse1);

        Map<String, List<Warehouse> > warehouseMap =  new HashMap<>();
        warehouseMap.put(date,warehouses);
        Storage storage = new Storage(warehouseMap);
        OrderFulfilmentImpl orderFulfilment = new OrderFulfilmentImpl(storage);
*/

        SpringApplication.run(App.class,args);

        ///////////////////////////////////////////////////////////

/*
        ItemRequest itemRequest1 = new ItemRequest(1,"apple","FNV",100);
        ItemRequest itemRequest2 = new ItemRequest(2,"banana","FNV",10);
        ItemRequest itemRequest3 = new ItemRequest(3,"juice","grocery",100);
        List<ItemRequest> itemRequests = new ArrayList<>();
        itemRequests.add(itemRequest1);
        itemRequests.add(itemRequest2);
        itemRequests.add(itemRequest3);
        OrderRequest orderRequest = new OrderRequest("1","100",date,itemRequests);

        System.out.println(orderFulfilment.canFulfilOrder(orderRequest));
        try {
            orderFulfilment.reserveOrder(orderRequest);
        }
        catch (Exception e){
            System.out.println("Cannot fulfil request");
        }
        System.out.println(orderFulfilment.canFulfilOrder(orderRequest));
*/


    }
}
