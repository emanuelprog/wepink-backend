package com.control.wepink.dto;

import com.control.wepink.model.Product;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Base64;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDTO {

    private Long id;
    private String name;
    private Long quantity;
    private Timestamp changeDate;
    private String image;

    public static ProductResponseDTO fromModel(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .quantity(product.getQuantity())
                .changeDate(product.getChangeDate())
                .image(Base64.getEncoder().encodeToString(product.getImage()))
                .build();
    }
}
