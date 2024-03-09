package com.solution.grapeApp.entities.requests;

import java.util.List;

import com.solution.grapeApp.entities.Order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Order order;
    private List<ProductDTO> products;
}
