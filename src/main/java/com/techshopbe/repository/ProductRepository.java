package com.techshopbe.repository;

import java.util.List;


import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.techshopbe.dto.DetailedProductDTO;
import com.techshopbe.dto.ProductDTO;
import com.techshopbe.dto.RatingInfoDTO;
import com.techshopbe.entity.Product;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Integer> {
//	@SQLInsert(sql = "insert into PRODUCT values (:rate)")
//	ProductDTO insertNewProduct(float rate, int totalReviews, int productID);

	@Query("SELECT new com.techshopbe.dto.ProductDTO(p.productID, c.categoryName, b.brandName, p.productRate, p.productName, p.productPrice, p.shortDescrip, p.longDescrip,p.stock, p.warranty, p.purchased, p.specName1, p.spec1, p.specName2, p.spec2, p.specName3, p.spec3, p.specName4, p.spec4, c.categorySlug, p.images) FROM Product p, Category c, Brand b WHERE p.categoryID = c.categoryID AND p.brandID = b.brandID")
	List<ProductDTO> getAll();

	@Query("SELECT new com.techshopbe.dto.ProductDTO(p.productID, c.categoryName, b.brandName, p.productRate, p.productName, p.productPrice, p.shortDescrip, p.longDescrip,p.stock, p.warranty, p.purchased, p.specName1, p.spec1, p.specName2, p.spec2, p.specName3, p.spec3, p.specName4, p.spec4, c.categorySlug, p.images) FROM Product p, Category c, Brand b WHERE p.categoryID = c.categoryID AND p.brandID = b.brandID AND c.categoryID = ?1 ORDER BY p.purchased DESC")
	List<ProductDTO> findTopPurchasedByCategoryId(int categoryID);

	@Query("SELECT new com.techshopbe.dto.ProductDTO(p.productID, c.categoryName, b.brandName, p.productRate, p.productName, p.productPrice, p.shortDescrip, p.longDescrip,p.stock, p.warranty, p.purchased, p.specName1, p.spec1, p.specName2, p.spec2, p.specName3, p.spec3, p.specName4, p.spec4, c.categorySlug, p.images) FROM Product p, Category c, Brand b WHERE p.categoryID = c.categoryID AND p.brandID = b.brandID ORDER BY p.purchased DESC")
	List<ProductDTO> findTrendingProducts();

	@Query("SELECT new com.techshopbe.dto.ProductDTO(p.productID, c.categoryName, b.brandName, p.productRate, p.productName, p.productPrice, p.shortDescrip, p.longDescrip,p.stock, p.warranty, p.purchased, p.specName1, p.spec1, p.specName2, p.spec2, p.specName3, p.spec3, p.specName4, p.spec4, c.categorySlug, p.images) FROM Product p, Category c, Brand b WHERE p.categoryID = c.categoryID AND p.brandID = b.brandID AND c.categorySlug = ?1")
	List<ProductDTO> findByCategorySlug(String categorySlug);

	@Query("SELECT new com.techshopbe.dto.DetailedProductDTO(p.productID, p.categoryID, p.brandID, c.categoryName, b.brandName, p.productRate, p.productName, p.productPrice, p.shortDescrip, p.longDescrip, p.stock, p.warranty, p.purchased, p.specName1, p.spec1, p.specName2, p.spec2, p.specName3, p.spec3, p.specName4, p.spec4, p.shortTech, p.totalReviews, p.images) FROM Product p, Category c, Brand b WHERE p.categoryID = c.categoryID AND p.brandID = b.brandID AND p.productID = ?1")
	DetailedProductDTO findDetailedProductByProductID(int productID);

	@Query("SELECT p.productPrice FROM Product p where p.productID = ?1")
	int findProductPriceByProductID(int id);

	@Query("SELECT new com.techshopbe.dto.ProductDTO(p.productID, c.categoryName, b.brandName, p.productRate, p.productName, p.productPrice, p.shortDescrip, p.longDescrip,p.stock, p.warranty, p.purchased, p.specName1, p.spec1, p.specName2, p.spec2, p.specName3, p.spec3, p.specName4, p.spec4, c.categorySlug, p.images) FROM Product p, Category c, Brand b WHERE p.categoryID = c.categoryID AND p.brandID = b.brandID AND p.productID != ?1 AND c.categoryID = (SELECT c.categoryID FROM Product p where p.productID = ?1) ")
	List<ProductDTO> findRelatedProductsByCategory(int productID);
	
	@Query("SELECT new com.techshopbe.dto.ProductDTO(p.productID, c.categoryName, b.brandName, p.productRate, p.productName, p.productPrice, p.shortDescrip, p.longDescrip,p.stock, p.warranty, p.purchased, p.specName1, p.spec1, p.specName2, p.spec2, p.specName3, p.spec3, p.specName4, p.spec4, c.categorySlug, p.images) FROM Product p, Category c, Brand b WHERE p.categoryID = c.categoryID AND p.brandID = b.brandID AND p.productID != ?1 AND b.brandID = (SELECT b.brandID FROM Product p where p.productID = ?1) ")
	List<ProductDTO> findRelatedProductsByBrand(int productID);
	
	@Query("SELECT new com.techshopbe.dto.RatingInfoDTO(p.productRate, p.totalReviews) FROM Product p WHERE p.productID = ?1")
	RatingInfoDTO findRatingInfoByProductID(int productID);

	@Modifying
    @Query("UPDATE Product p SET p.productRate = ?1, p.totalReviews = ?2 WHERE p.productID = ?3")
    int updateRatingInfoByProductID(float rate, int totalReviews, int productID);

}
