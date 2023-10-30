package com.example.inventory.fsd;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponse {
    List<Product> products;
    int currentPage;
    int totalPage;
}
