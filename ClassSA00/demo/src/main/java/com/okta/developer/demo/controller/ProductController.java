package com.okta.developer.demo.controller;
import java.util.Collection;
import java.util.stream.Collectors;

import com.okta.developer.demo.Entity.Product;
import com.okta.developer.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @GetMapping("/product")
    public Collection<Product> product() {
        return productRepository.findAll().stream()
                .filter(this::isProduct)
                .collect(Collectors.toList());
    }
    private boolean isProduct(Product product){
        return product.getStatusProduct().equals("selling");
    }


}
