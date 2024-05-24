package org.example.service;
import org.example.entity.Products;

import java.util.List;

public interface ProductService {
    List<Products> getAllProducts();

    void addProduct(String productname, int price, String description, int quantity, String category, String imgPath);
    void deleteProduct(int productId);
}
