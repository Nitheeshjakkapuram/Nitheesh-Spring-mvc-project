package com.springbootexamples.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springbootexamples.entity.ProductEntity;
import com.springbootexamples.model.ProductModel;
import com.springbootexamples.service.ProductService;

@Controller
	public class ProductController {

	    @Autowired
	    private ProductService productService;
	    
	    @GetMapping("/productform")
	    public String getproductForm() {
	    	return "add-product";
	    	
	    }
	    @PostMapping("/saveProduct")
	    public String save( ProductModel productModel) {
	    	
	        productService.saveProductDetails(productModel);
	        return "success";
	    }
	    @GetMapping("/getallproducts")
	    public String getAllproducts (Model model) {
	    	List<ProductEntity>products=productService.getAllproducts();
	    	model.addAttribute("products",products);
	    	return "product-list";
	    	
	    }
	    @GetMapping("/getsearchform")
	    public String  getsearchform()
	    {
			return "search-product";
	    	
	    }
	    @PostMapping("/searchid")
	    public String searchById(@RequestParam Long id,Model model)
	    {
	    	ProductEntity product=productService.searchById(id);
	    	model.addAttribute("product",product);
	    	return "search-product";
	    }
	    @GetMapping("/delete/{id}")
	    public String deleteproductById(@PathVariable("id")Long id)
	    {
	    	productService.deleteproductById(id);
	    	return "redirect:/getallproducts";
	    }
	    @GetMapping("/edit/{id}")
	    public String showEditProductPage(@PathVariable Long id, Model model) {
	        ProductModel product = productService.editProductById(id); 
	        model.addAttribute("product", product);
	        model.addAttribute("id", id);
	        return "edit-product";
	    }

	    @PostMapping("/savepriduct/{id}")
	    public String showEditForm(@PathVariable Long id,ProductModel productModel) {
	        productService.updateProduct(id,productModel); 
	        return "redirect:/getallproducts";
	    }
	}


