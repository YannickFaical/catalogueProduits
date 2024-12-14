package com.catalogue.produits.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.catalogue.produits.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	

}
