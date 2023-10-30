package com.example.inventory.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity(name = "product")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "PRODUCT_SEQ_GEN")
    @SequenceGenerator(name="PRODUCT_SEQ_GEN",sequenceName = "PRODUCT_SEQ",allocationSize = 1)
    @Column(name="PRODUCT_ID")
    Long productId;

    @Column(name = "PRODUCT_TYPE_ID")
//            Product Type will contain product characteristics *characteristic of jeans ex. bottomwear,
    Long productTypeId;//ProductType -  Jeans, Shirt

    @Column(name = "PRODUCT_TYPE_CODE")
    String productTypeCode;


}
