package com.example.inventory.fsd;

import com.example.inventory.entity.Product;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PreparedOrderProduct {

    Product product;

    int quantity;
}
