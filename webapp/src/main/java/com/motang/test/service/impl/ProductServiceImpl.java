package com.motang.test.service.impl;

import java.util.List;

import com.motang.test.domain.Product;
import com.motang.test.service.ProductService;

public class ProductServiceImpl implements ProductService{
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public void increasePrice(int percentage) {
        if (products != null) {
            for (Product product : products) {
                double newPrice = product.getPrice().doubleValue() * 
                                    (100 + percentage)/100;
                product.setPrice(newPrice);
            }
        }
    }

}
