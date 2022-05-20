package com.Groww;

import com.Groww.models.Product;

import java.util.Map;

public class NoCoinInserted implements State {

    Inventory inventory;
    VendingMachine vendingMachine;

    @Override
    public Product dispense(Product product) {
       throw new IllegalStateException("Cannot dispense");
    }

    @Override
    public void insertCoin(int productId, int amount) throws Exception {
        Map<Product, Integer> productMap = this.inventory.getProductMap();
        boolean found = false;
        for (Map.Entry<Product, Integer> entry : productMap.entrySet()) {
            if (entry.getKey().getProductId() == productId) {
                int price = entry.getKey().getPrice().getDenomination();
                if (price > amount) {
                    throw new Exception("Amount Insufficient");
                } else if (price < amount) {
                    int change = amount - price;
                    if(change>vendingMachine.getChangeAvailable()){
                        throw new Exception("change Insufficient");
                    }
                    else{
                        vendingMachine.setChangeAvailable(vendingMachine.getChangeAvailable() - change);
                    }
                } else {
                    if (entry.getValue() - 1 < 0) {
                        throw new Exception("Product count Insufficient");
                    } else {
                        found = true;
                        productMap.put(entry.getKey(), entry.getValue() - 1);
                    }
                }
            }
        }

        if(!found){
            throw new Exception("Product is unavailable");
        }
    }

}
