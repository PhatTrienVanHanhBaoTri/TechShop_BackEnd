package com.techshopbe.service;

import java.util.List;

import com.techshopbe.dto.DetailedProductDTO;
import com.techshopbe.dto.ProductDTO;
import com.techshopbe.dto.UpdateProductDTO;
import com.techshopbe.entity.Product;

public interface ProductService {
	public Product addProduct(Product product);
	public DetailedProductDTO updateProduct(int productID, UpdateProductDTO product);
	public void deleteProduct(int productID);
	public List<ProductDTO> getAll();
	public List<ProductDTO> getTrendingProducts();
	public List<ProductDTO> getProductsByCategory(String categorySlug);
	public List<ProductDTO> getTopPurchasedProducts(int categoryID);
	public DetailedProductDTO getDetailedProduct(int productID);
	public List<ProductDTO> getRelatedCategoryProducts(int productID);
	public List<ProductDTO> getRelatedBrandProducts(int productID);
	public void updateRating(int productID, float rate);
}
