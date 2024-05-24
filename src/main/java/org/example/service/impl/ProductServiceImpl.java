package org.example.service.impl;

import org.example.entity.Products;
import org.example.service.ProductService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
            product.setImgPath(resultSet.getString("img_path"));
            return product;
        });
    }

    @Override
    public void addProduct(String productName, int price, String description, int quantity, String category, String imgPath) {
        if (productName == null || productName.isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (price < 0 || quantity < 0) {
            throw new IllegalArgumentException("Price and quantity must be non-negative");
        }

        try {
            String sql = "INSERT INTO products (name, price, description, quantity, category, img_path) VALUES (?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, productName, price, description, quantity, category, imgPath);
        } catch (DataAccessException ex) {
            throw new ServiceException("Error accessing data", ex);
        }
    }

    @Override
    public void deleteProduct(int productId) {
        try {
            String sql = "DELETE FROM products WHERE id = ?";
            jdbcTemplate.update(sql, productId);
        } catch (DataAccessException ex) {
            throw new ServiceException("Error accessing data", ex);
        }
    }
}