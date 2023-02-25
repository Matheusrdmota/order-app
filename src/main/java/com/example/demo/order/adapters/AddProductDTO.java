package com.example.demo.order.adapters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProductDTO {
    private Long barCode;
    private Long quantity;
}
