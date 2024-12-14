package com.catalogue.produits.services;




import org.springframework.stereotype.Service;

import com.catalogue.produits.entities.Product;
import com.catalogue.produits.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

  public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

 public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

 public Product addProduct(Product product) {
        return productRepository.save(product);
    }
}
