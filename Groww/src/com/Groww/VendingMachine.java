package com.Groww;

import com.Groww.models.Product;

import java.util.Map;

public class VendingMachine {

    CoinInserted coinInserted = new CoinInserted();
    Dispense dispense = new Dispense();
    NoCoinInserted noCoinInserted = new NoCoinInserted();
    Inventory inventory = new Inventory();
    State currentState = null;
    int changeAvailable = 0;

    public VendingMachine() {
        changeAvailable = 0;
        currentState = noCoinInserted;
    }

    public void setChangeAvailable(int changeAvailable) {
        this.changeAvailable = changeAvailable;
    }

    public int getChangeAvailable() {
        return changeAvailable;
    }

    public void insertCoin(int productId, int amount) throws Exception {
        currentState.insertCoin(productId, amount);
        currentState = changeState(currentState);
    }

    public void showProducts(){
        Map<Product, Integer> productMap = this.inventory.getProductMap();
        productMap.forEach((k,v)->{
            System.out.println(k.getName());
            System.out.println(k.getPrice().getDenomination());
        });
    }

    public State changeState(State currentState){
        if(currentState == noCoinInserted){
            return coinInserted;
        }
        if(currentState == coinInserted){
            return dispense;
        }
        else return noCoinInserted;
    }

}
