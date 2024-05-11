package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.Entity.Products;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/index")
    public String getProducts(Model model) {
        List<Products> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/addproduct")
    public String showAddProductPage() {
        return "addproduct";
    }

    @PostMapping("/addedproduct")
    public String addProduct(HttpServletRequest request) {
        String productName = request.getParameter("productName");
        int price = Integer.parseInt(request.getParameter("productPrice"));
        String description = request.getParameter("productDescription");
        int quantity = Integer.parseInt(request.getParameter("productQuantity"));
        String category = request.getParameter("productCategory");
        String imgPath = request.getParameter("productImg_path");

        productService.addProduct(productName, price, description, quantity, category, imgPath);

        return "redirect:/index";
    }
}
