package com.example.inventory.fsd;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Product {
    Long productId;

//            Product Type will contain product characteristics *characteristic of jeans ex. bottomwear,
    Long productTypeId;//ProductType -  Jeans, Shirt

    String productTypeCode;

    long inventoryCount;
}
