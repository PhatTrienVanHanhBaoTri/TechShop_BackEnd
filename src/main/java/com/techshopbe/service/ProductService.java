package com.techshopbe.service;

import java.util.List;

import com.techshopbe.dto.UpdateProductDTO;
import com.techshopbe.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.techshopbe.dto.DetailedProductDTO;
import com.techshopbe.dto.ProductDTO;
import com.techshopbe.dto.RatingInfoDTO;
import com.techshopbe.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;
	public Product addProduct(Product product){
		productRepository.save(product);
		return product;
	}
	public DetailedProductDTO updateProduct(int productID, UpdateProductDTO product) {
		productRepository.udpateProductInfo(productID,
				product.getCategoryID(), product.getBrandID(),
				product.getProductName(), product.getProductPrice(),
				product.getShortDescrip(), product.getLongDescrip(),
				product.getStock(), product.getWarranty(),
				product.getSpecName1(), product.getSpec1(), product.getSpecName2(), product.getSpec2(), product.getSpecName3(), product.getSpec3(), product.getSpecName4(), product.getSpec4(),
				product.getShortTech(), product.getImages());

		return getDetailedProduct(productID);
	}

	public void deleteProduct(int productID) {
		productRepository.deleteById(productID);
	}

	public List<ProductDTO> getAll() {
		return productRepository.getAll();

	}

	public List<ProductDTO> getTrendingProducts() {
		List<ProductDTO> products = productRepository.findTrendingProducts();
		return products.stream().limit(8).toList();
	}

	public List<ProductDTO> getTopPurchasedProducts(int categoryID) {

		List<ProductDTO> productsByCategory = productRepository.findTopPurchasedByCategoryId(categoryID);
		return productsByCategory.stream().limit(5).toList();

	}

	public List<ProductDTO> getProductsByCategory(String categorySlug) {

		return productRepository.findByCategorySlug(categorySlug);

	}

	public DetailedProductDTO getDetailedProduct(int productID) {
		DetailedProductDTO detailedProduct = productRepository.findDetailedProductByProductID(productID);
		if (detailedProduct.getStock() > 0)
			detailedProduct.setStockStatus("in-stock");
		else if (detailedProduct.getStock() == 0)
			detailedProduct.setStockStatus("out-of-stock");
		return detailedProduct;
	}

	public List<ProductDTO> getRelatedCategoryProducts(int productID) {
		List<ProductDTO> productsByCategory = productRepository.findRelatedProductsByCategory(productID);
		return productsByCategory.stream().limit(4).toList();
	}

	public List<ProductDTO> getRelatedBrandProducts(int productID) {
		List<ProductDTO> productsByBrand = productRepository.findRelatedProductsByBrand(productID);
		return productsByBrand.stream().limit(4).toList();
	}

	public void updateRating(int productID, float rate) {
		RatingInfoDTO ratingInfoDTO = productRepository.findRatingInfoByProductID(productID);
		int newTotalReviews = ratingInfoDTO.getTotalReviews() + 1;
		float newRating = (ratingInfoDTO.getProductRate() * ratingInfoDTO.getTotalReviews() + rate) / newTotalReviews;
		//System.out.println(newRating);
		//System.out.println(newTotalReviews);
		productRepository.updateRatingInfoByProductID(newRating, newTotalReviews, productID);
	}

}
