package com.motang.test.service;

import java.util.List;

import com.motang.test.domain.Product;

public interface ProductService {
    public void increasePrice(int percentage);

    public List<Product> getProducts();
    
    public void setProducts(List<Product> products);
}
