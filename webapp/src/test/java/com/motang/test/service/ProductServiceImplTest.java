package com.motang.test.service;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.motang.test.domain.Product;
import com.motang.test.service.impl.ProductServiceImpl;

public class ProductServiceImplTest {
    private ProductServiceImpl productService;
    private List<Product> products;
    
    private static int PRODUCT_COUNT = 2;
    
    private static Double CHAIR_PRICE = new Double(20.50);
    private static String CHAIR_DESCRIPTION = "Chair";
    
    private static String TABLE_DESCRIPTION = "Table";
    private static Double TABLE_PRICE = new Double(150.10);         
        
    private static int POSITIVE_PRICE_INCREASE = 10;
    
    @Before
    protected void setUp() throws Exception {
        productService = new ProductServiceImpl();
        products = new ArrayList<Product>();
        
        // stub up a list of products
        Product product = new Product();
        product.setDescription("Chair");
        product.setPrice(CHAIR_PRICE);
        products.add(product);
        
        product = new Product();
        product.setDescription("Table");
        product.setPrice(TABLE_PRICE);
        products.add(product);
        
        productService.setProducts(products);
    }

    @Test
    public void testGetProductsWithNoProducts() {
        productService = new ProductServiceImpl();
        Assert.assertNull(productService.getProducts());
    }
    
    @Test
    public void testGetProducts() {
        List<Product> products = productService.getProducts();
        Assert.assertNotNull(products);        
        Assert.assertEquals(PRODUCT_COUNT, productService.getProducts().size());
    
        Product product = products.get(0);
        Assert.assertEquals(CHAIR_DESCRIPTION, product.getDescription());
        Assert.assertEquals(CHAIR_PRICE, product.getPrice());
        
        product = products.get(1);
        Assert.assertEquals(TABLE_DESCRIPTION, product.getDescription());
        Assert.assertEquals(TABLE_PRICE, product.getPrice());      
    }  
    
    public void testIncreasePriceWithNullListOfProducts() {
        try {
            productService = new ProductServiceImpl();
            productService.increasePrice(POSITIVE_PRICE_INCREASE);
        }
        catch(NullPointerException ex) {
            Assert.fail("Products list is null.");
        }
    }
    
    public void testIncreasePriceWithEmptyListOfProducts() {
        try {
            productService = new ProductServiceImpl();
            productService.setProducts(new ArrayList<Product>());
            productService.increasePrice(POSITIVE_PRICE_INCREASE);
        }
        catch(Exception ex) {
            Assert.fail("Products list is empty.");
        }           
    }
    
    public void testIncreasePriceWithPositivePercentage() {
        productService.increasePrice(POSITIVE_PRICE_INCREASE);
        double expectedChairPriceWithIncrease = 22.55;
        double expectedTablePriceWithIncrease = 165.11;
        
        List<Product> products = productService.getProducts();      
        Product product = products.get(0);
        Assert.assertEquals(expectedChairPriceWithIncrease, product.getPrice());
        
        product = products.get(1);      
        Assert.assertEquals(expectedTablePriceWithIncrease, product.getPrice());       
    }
}
