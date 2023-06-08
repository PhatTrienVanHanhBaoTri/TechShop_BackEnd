package com.techshopbe.controller;

import java.io.IOException;
import java.util.List;

import com.techshopbe.dto.UpdateProductDTO;
import com.techshopbe.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import com.techshopbe.dto.DetailedProductDTO;
import com.techshopbe.dto.ProductDTO;
import com.techshopbe.service.ProductService;


@RestController
@Slf4j
@RequestMapping("api/v1/product")
public class ProductController {
	@Autowired
	private ProductService productService;

	@PostMapping(value = "")
	public ResponseEntity<Object> addNewProduct(@RequestBody Product product){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(product));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
		}
	}

	@PutMapping(value = "/{productID}")
	public ResponseEntity<Object> updateProduct(@RequestBody UpdateProductDTO product, @PathVariable int productID){
		try {
			return ResponseEntity.ok(productService.updateProduct(productID, product));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
		}
	}

	@DeleteMapping(value = "/{productID}")
	public ResponseEntity<Object> deleteProduct(@PathVariable int productID){
		try {
			productService.deleteProduct(productID);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("");
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
		}
	}

	@GetMapping(value = "")
	public Object index() {
		try {
			List<ProductDTO> productList = productService.getAll();
			return new ResponseEntity<List<ProductDTO>>(productList, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
		}
	}

	@GetMapping(value = "/{productID}")
	public Object getDetailedProduct(@PathVariable int productID) {
		try {
			DetailedProductDTO detailedProduct = productService.getDetailedProduct(productID);
			return new ResponseEntity<DetailedProductDTO>(detailedProduct, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
		}
	}

	@GetMapping(value = "/category/{categorySlug}")
	public Object getProductsByCategory(@PathVariable String categorySlug) {
		try {
			List<ProductDTO> productsByCategory = productService.getProductsByCategory(categorySlug);
			return new ResponseEntity<List<ProductDTO>>(productsByCategory, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
		}
	}

	@GetMapping(value = "/toppurchased/{categoryID}")
	public Object getTopPurchasedProducts(@PathVariable int categoryID) {
		try {
			List<ProductDTO> topPurchasedProducts = productService.getTopPurchasedProducts(categoryID);

			return new ResponseEntity<List<ProductDTO>>(topPurchasedProducts, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
		}
	}

	@GetMapping(value = "/trending")
	public Object getTrendingProducts() {
		try {
			List<ProductDTO> trendingProducts = productService.getTrendingProducts();

			return new ResponseEntity<List<ProductDTO>>(trendingProducts, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
		}
	}

	@GetMapping(value = "related-category/{productID}")
	public Object getRelatedCategoryProducts(@PathVariable int productID) {
		
		try {
			List<ProductDTO> relatedCategoryProducts = productService.getRelatedCategoryProducts(productID);

			return new ResponseEntity<List<ProductDTO>>(relatedCategoryProducts, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
		}
	}
	
	@GetMapping(value = "related-brand/{productID}")
	public Object getRelatedBrandProducts(@PathVariable int productID) {
		
		try {
			List<ProductDTO> relatedBrandProducts = productService.getRelatedBrandProducts(productID);

			return new ResponseEntity<List<ProductDTO>>(relatedBrandProducts, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
		}
	}
	

}
