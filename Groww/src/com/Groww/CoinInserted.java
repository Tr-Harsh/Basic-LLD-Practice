package com.Groww;

import com.Groww.State;
import com.Groww.models.Product;

public class CoinInserted implements State {

    Inventory inventory;
    VendingMachine vendingMachine;

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public Product dispense(Product product) throws Exception {
        throw new Exception("cannot dispense");
    }

    @Override
    public void insertCoin(int productId, int amount) throws Exception {
        throw new Exception("Cannot insert coin");
    }
}
