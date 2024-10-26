package com.control.wepink.model;

import com.control.wepink.dto.ProductRequestDTO;
import com.control.wepink.exception.BadRequestException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Base64;

@Table(name = "produtos", schema = "wepink")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "quantidade")
    private Long quantity;

    @Column(name = "data_alteracao")
    private Timestamp changeDate;

    @Column(name = "imagem")
    private byte[] image;

    public void plusQuantity(Long amount) {
        this.quantity += amount;
    }

    public void minusQuantity(Long amount) {
        if (amount != null && amount > 0) {
            if (this.quantity >= amount) {
                this.quantity -= amount;
            } else {
                throw new BadRequestException("Insufficient quantity for removal");
            }
        }
    }

    public static Product fromRequestDTO(ProductRequestDTO productRequestDTO) {
        return Product.builder()
                .name(productRequestDTO.getName())
                .quantity(productRequestDTO.getQuantity())
                .changeDate(new Timestamp(System.currentTimeMillis()))
                .image(Base64.getDecoder().decode(productRequestDTO.getImage()))
                .build();
    }
}
