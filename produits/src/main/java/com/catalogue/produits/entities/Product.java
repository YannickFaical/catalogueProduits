package com.catalogue.produits.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private  String name;

    @Column(nullable = false)
    private double price;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    

    // Constructeurs
    public Product() {}

    public Product(String name, double price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }


	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
	public double getPrice() {
		// TODO Auto-generated method stub
		return this.price;
	}
	
	public Long getId() {
		// TODO
		return id;
	}
	
	public Category getCategory() {
		// TODO Auto-generated method stub
		return this.category;
	}
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name=name;
		
	}
	
	public void setPrice(double price) {
		// TODO Auto-generated method stub
		this.price=price;
		
	}
	
	public void setCategory(Category category) {
		// TODO Auto-generated method stub
		this.category=category;
		
	}
	
	




}
