package com.catalogue.produits.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.catalogue.produits.entities.Category;
import com.catalogue.produits.repositories.CategoryRepository;

import jakarta.websocket.server.PathParam;

@Service
public class CategoryService {
	
	@Autowired
	private final CategoryRepository categoryRepository;
	
	
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository= categoryRepository;
	
	}
	
	public List<Category> getAllCategories(){
		
		return categoryRepository.findAll();
	}
	

    public void addCategory(Category category) {

        if (category.getName() == null || category.getName().isEmpty()) {
            throw new IllegalArgumentException("Category name must not be null or empty.");
        }
        categoryRepository.save(category);
    }
	
//	public Category deleteCategoryById(@PathVariable Long id){
	//	Optional<Category> existingCategory =categoryRepository.findById(id);
		
		 
	//}

}
