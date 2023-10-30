package com.example.inventory.fsd;

import lombok.*;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderProduct {

    private Long productId;

    private int quantity;
}
