package com.example.inventory.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "PRODUCT_INVENTORY")
@Getter
@Setter
@Builder
public class ProductInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "PRODUCT_INVENTORY_SEQUENCE_GEN")
    @SequenceGenerator(name = "PRODUCT_INVENTORY_SEQUENCE_GENERATOR",sequenceName = "PRODUCT_INVENTORY_SEQ",allocationSize = 1)
    @Column(name = "PRODUCT_INVENTORY_ID")
    Long productInventoryId;

    @Column(name = "AVAILABLE")
    Boolean available;

    @Column(name = "UNAVAILABLE_TILL")
    LocalDateTime unavailableTill;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    Order order;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_ID",nullable = false)
    Product product;


}
