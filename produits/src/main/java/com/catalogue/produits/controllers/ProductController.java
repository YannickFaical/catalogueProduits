package com.catalogue.produits.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.catalogue.produits.entities.Category;
import com.catalogue.produits.entities.Product;
import com.catalogue.produits.repositories.ProductRepository;
import com.catalogue.produits.services.ProductService;


import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
	@Autowired
    private ProductRepository productRepository;
	
      //GET // récuperer la liste des produits
//	@GetMapping("/getProducts")
	@RequestMapping("/products")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Product> products(){
        List<Product> products = (List<Product>)productRepository.findAll();
        System.out.println("Produits récupérés : " + products);
        return products;
    }    
    
 
    
    @PostMapping("/addProduct")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Product> addProduct(@RequestBody Product newProduct) {
        System.out.println("Requête reçue pour ajouter un produit : " + newProduct);
        try {
            Product savedProduct = productRepository.save(newProduct);
            System.out.println("Produit sauvegardé avec succès : " + savedProduct);
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Erreur lors de l'ajout du produit : " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
 // 3. READ (Par ID): Récupérer un produit par son ID
    @GetMapping("products/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> productData = productRepository.findById(id);
        if (productData.isPresent()) {
            return new ResponseEntity<>(productData.get(), HttpStatus.OK); // 200 OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }
    
    // 3. READ (Par ID): Récupérer la liste des produits par categorie
    @GetMapping("getProductByIdCategory/{categoryId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Product>> getProductByIdCategory(@PathVariable (required = false) Long categoryId) {
    
    	 List<Product> productData;

    	 
    	    if (categoryId == null  ) {
    	        // Si categoryId est null, retourner tous les produits
    	        return ResponseEntity.ok(productRepository.findAll());
    	    } else {
    	         productData = productRepository.findByCategoryId(categoryId);
    	        if (productData != null && !productData.isEmpty()) {
    	            return ResponseEntity.ok(productData);
    	        } else {
    	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
    	        }
    	}
    	    
    }


    	


        
      
    
    
    
 // 4. UPDATE: Modifier un produit existant
    @PutMapping("/updateproduct/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Optional<Product> productData = productRepository.findById(id);

        if (productData.isPresent()) {
            Product existingProduct = productData.get();
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setCategory(updatedProduct.getCategory());
         

            Product savedCategory = productRepository.save(existingProduct);
            return new ResponseEntity<>(savedCategory, HttpStatus.OK); // 200 OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }
    
 // 5. DELETE: Supprimer un produit par son ID
    @DeleteMapping("/deleteProduct/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long id) {
        try {
            System.out.println("Vérification de l'existence du produit avec ID: " + id);
            if (!productRepository.existsById(id)) {
                System.out.println("produit non trouvé avec ID: " + id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
            }
            productRepository.deleteById(id);
            System.out.println("Produit supprimé avec succès avec ID: " + id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } catch (Exception e) {
            System.err.println("Erreur lors de la suppression du produit avec ID " + id + ": " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }
}
