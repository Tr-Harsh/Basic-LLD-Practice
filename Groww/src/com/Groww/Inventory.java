package com.Groww;

import com.Groww.models.Product;

import java.util.List;
import java.util.Map;

public class Inventory {
    private Map<Product, Integer> productMap;

    public void addProduct(Product product){
        if(productMap.containsKey(product)){
            productMap.put(product,productMap.getOrDefault(product,0)+1);
        }
    }

    public void loadProducts(List<Product> products){
        for(Product p : products){
            if(productMap.containsKey(p)){
                productMap.put(p,productMap.getOrDefault(p,0)+1);
            }
        }
    }

    public Map<Product, Integer> getProductMap() {
        return productMap;
    }
}
