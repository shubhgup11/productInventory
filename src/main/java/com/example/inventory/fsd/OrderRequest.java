package com.example.inventory.fsd;

import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderRequest {

    List<OrderProduct> orderProductList;
}
