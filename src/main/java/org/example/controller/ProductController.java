package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    @Autowired
    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/addproduct")
    public String showAddProductPage() {
        return "addproduct";
    }

    @PostMapping("/deleteproduct")
    public String handleDeleteProduct(HttpServletRequest request, Model model) {
        int productId = Integer.parseInt(request.getParameter("productId"));
        productService.deleteProduct(productId);
        return "redirect:";
    }


    @PostMapping("/addedproduct")
    public String handleAddProduct(HttpServletRequest request, Model model) {
        try {
            String productName = request.getParameter("productName");
            int price = Integer.parseInt(request.getParameter("productPrice"));
            String description = request.getParameter("productDescription");
            int quantity = Integer.parseInt(request.getParameter("productQuantity"));
            String category = request.getParameter("productCategory");
            String imgPath = request.getParameter("productImg_path");


            productService.addProduct(productName, price, description, quantity, category, imgPath);

            return "redirect:";
        } catch (NumberFormatException e) {
            model.addAttribute("error", "Invalid input for price or quantity. Please enter valid numbers.");
            return "addproduct";
        }
    }
}