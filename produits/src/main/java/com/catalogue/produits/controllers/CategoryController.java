package com.catalogue.produits.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.catalogue.produits.entities.Category;
import com.catalogue.produits.repositories.CategoryRepository;
import com.catalogue.produits.services.CategoryService;


import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	
    private final CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    
    @GetMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

   
    
    @PostMapping("/add")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Category> addContact(@RequestBody Category newCategory){
    	try {
    		Category savedContact = categoryRepository.save(newCategory);
    		return new ResponseEntity<>(savedContact,HttpStatus.CREATED);
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    
    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable Long id) {
        try {
            System.out.println("Vérification de l'existence de la categorie avec ID: " + id);
            if (!categoryRepository.existsById(id)) {
                System.out.println("Categorie non trouvé avec ID: " + id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);// 404 Not Found
            }
            categoryRepository.deleteById(id);
            System.out.println("categorie supprimé avec succès avec ID: " + id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } catch (Exception e) {
            System.err.println("Erreur lors de la suppression de la categorie avec ID " + id + ": " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }

}
