package com.techshopbe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductDTO {
    int categoryID;
    int brandID;
    String productName; int productPrice;
    String shortDescrip;
    String longDescrip;
    int stock;
    int warranty;
    String specName1;
    String spec1;
    String specName2;
    String spec2;
    String specName3;
    String spec3;
    String specName4;
    String spec4;
    String shortTech;
    String images;
}
