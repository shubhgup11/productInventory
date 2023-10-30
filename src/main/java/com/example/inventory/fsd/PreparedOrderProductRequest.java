package com.example.inventory.fsd;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PreparedOrderProductRequest {

    List<PreparedOrderProduct> preparedOrderProductList;

}
