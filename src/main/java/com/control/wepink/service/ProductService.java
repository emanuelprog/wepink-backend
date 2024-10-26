package com.control.wepink.service;

import com.control.wepink.dto.ProductRequestDTO;
import com.control.wepink.dto.ProductResponseDTO;
import com.control.wepink.exception.BadRequestException;
import com.control.wepink.exception.NotFoundException;
import com.control.wepink.model.Product;
import com.control.wepink.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponseDTO> find() {
            return productRepository.findAll().stream().map(ProductResponseDTO::fromModel).collect(Collectors.toList());
    }

    public ProductResponseDTO add(ProductRequestDTO data) {
        try {
            return ProductResponseDTO.fromModel(productRepository.save(Product.fromRequestDTO(data)));
        } catch (Exception e) {
            throw new BadRequestException("Unable to add product!");
        }
    }

    public ProductResponseDTO addQuantity(Long id, Long amount) {
        Product productDB = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not exists!"));

        productDB.plusQuantity(amount);

        productDB.setChangeDate(new Timestamp(System.currentTimeMillis()));

        return ProductResponseDTO.fromModel(productRepository.save(productDB));
    }

    public ProductResponseDTO removeQuantity(Long id, Long amount) {
        Product productDB = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not exists!"));

        productDB.minusQuantity(amount);

        productDB.setChangeDate(new Timestamp(System.currentTimeMillis()));

        return ProductResponseDTO.fromModel(productRepository.save(productDB));
    }
}
