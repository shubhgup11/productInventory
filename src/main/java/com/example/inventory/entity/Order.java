package com.example.inventory.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ORDER_ID_SEQ_GEN")
    @SequenceGenerator(name = "ORDER_ID_SEQ_GEN",sequenceName = "ORDER_ID_SEQ",allocationSize = 1)
    @Column(name = "ORDER_ID")
    private Long orderId;

    @Column(name = "PAYMENT_ID")
    String paymentId;

    @Column(name = "ORDER_DATE")
    LocalDateTime orderDate;
}
