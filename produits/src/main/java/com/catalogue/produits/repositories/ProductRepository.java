package com.catalogue.produits.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.catalogue.produits.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	List<Product> findByCategoryId(Long CategoryId);

}
