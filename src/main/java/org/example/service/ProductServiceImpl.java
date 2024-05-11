package org.example.service;

import org.example.Entity.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Products> getAllProducts() {
        String sql = "SELECT * FROM products";
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            Products product = new Products();
            product.setId(resultSet.getLong("id"));
            product.setName(resultSet.getString("name"));
            product.setDescription(resultSet.getString("description"));
            product.setPrice(resultSet.getDouble("price"));
            product.setQuantity(resultSet.getInt("quantity"));
            product.setCategory(resultSet.getString("category"));
            product.setImg_path(resultSet.getString("img_path"));
            return product;
        });
    }

    @Override
    public void addProduct(String productName, int price, String description, int quantity, String category, String imgPath) {
        String sql = "INSERT INTO products (name, price, description, quantity, category, img_path) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, productName, price, description, quantity, category, imgPath);
    }
}