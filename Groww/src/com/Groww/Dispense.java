package com.Groww;

import com.Groww.models.Product;

public class Dispense implements State{

    @Override
    public Product dispense(Product product) throws Exception {
        System.out.println(product.getName());
        return product;
    }

    @Override
    public void insertCoin(int productId, int amount) throws Exception {
        throw new IllegalStateException("Cannot insertCoin");
    }

}
