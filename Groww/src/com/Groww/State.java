package com.Groww;

import com.Groww.models.Product;

public interface State {
    Product dispense(Product product) throws Exception;
    void insertCoin(int productId, int amount) throws Exception;
}
