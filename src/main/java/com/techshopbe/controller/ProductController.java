package com.techshopbe.controller;

import java.util.List;

import com.techshopbe.dto.UpdateProductDTO;
import com.techshopbe.entity.Product;
import com.techshopbe.service.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.techshopbe.dto.DetailedProductDTO;
import com.techshopbe.dto.ProductDTO;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1/product")
public class ProductController {
	private final ProductService productService;

	@PostMapping(value = "")
	@PreAuthorize("hasAuthority('1')")
	@SecurityRequirement(name = "Bearer Authentication")
	public ResponseEntity<Object> addNewProduct(@RequestBody Product product){
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(product));
	}

	@PutMapping(value = "/{productID}")
	@PreAuthorize("hasAuthority('1')")
	@SecurityRequirement(name = "Bearer Authentication")
	public ResponseEntity<Object> updateProduct(@RequestBody UpdateProductDTO product, @PathVariable int productID){
		return ResponseEntity.ok(productService.updateProduct(productID, product));
	}

	@DeleteMapping(value = "/{productID}")
	@PreAuthorize("hasAuthority('1')")
	@SecurityRequirement(name = "Bearer Authentication")
	public ResponseEntity<Object> deleteProduct(@PathVariable int productID){
		productService.deleteProduct(productID);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("");
	}

	@GetMapping(value = "")
	public Object index() {
		List<ProductDTO> productList = productService.getAll();
		return new ResponseEntity<List<ProductDTO>>(productList, HttpStatus.OK);
	}

	@GetMapping(value = "/{productID}")
	public Object getDetailedProduct(@PathVariable int productID) {
		DetailedProductDTO detailedProduct = productService.getDetailedProduct(productID);
		return new ResponseEntity<DetailedProductDTO>(detailedProduct, HttpStatus.OK);
	}

	@GetMapping(value = "/category/{categorySlug}")
	public Object getProductsByCategory(@PathVariable String categorySlug) {
		List<ProductDTO> productsByCategory = productService.getProductsByCategory(categorySlug);
		return new ResponseEntity<List<ProductDTO>>(productsByCategory, HttpStatus.OK);
	}

	@GetMapping(value = "/toppurchased/{categoryID}")
	public Object getTopPurchasedProducts(@PathVariable int categoryID) {
		List<ProductDTO> topPurchasedProducts = productService.getTopPurchasedProducts(categoryID);
		return new ResponseEntity<List<ProductDTO>>(topPurchasedProducts, HttpStatus.OK);
	}

	@GetMapping(value = "/trending")
	public Object getTrendingProducts() {
		List<ProductDTO> trendingProducts = productService.getTrendingProducts();
		return new ResponseEntity<List<ProductDTO>>(trendingProducts, HttpStatus.OK);
	}

	@GetMapping(value = "/related-category/{productID}")
	public Object getRelatedCategoryProducts(@PathVariable int productID) {
		List<ProductDTO> relatedCategoryProducts = productService.getRelatedCategoryProducts(productID);
		return new ResponseEntity<List<ProductDTO>>(relatedCategoryProducts, HttpStatus.OK);
	}
	
	@GetMapping(value = "/related-brand/{productID}")
	public Object getRelatedBrandProducts(@PathVariable int productID) {
		List<ProductDTO> relatedBrandProducts = productService.getRelatedBrandProducts(productID);
		return new ResponseEntity<List<ProductDTO>>(relatedBrandProducts, HttpStatus.OK);
	}
}
