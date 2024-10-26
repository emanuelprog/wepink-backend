package com.control.wepink.controller;

import com.control.wepink.dto.ProductRequestDTO;
import com.control.wepink.service.ProductService;
import com.control.wepink.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return ResponseUtil.generateResponse("Product list loaded successfully!", HttpStatus.OK, productService.find());
    }

    @PostMapping
    public ResponseEntity<Object> addProduct(@RequestBody ProductRequestDTO data) {
        return ResponseUtil.generateResponse("Product registered successfully!", HttpStatus.CREATED, productService.add(data));
    }

    @PutMapping("/plus/{id}/{amount}")
    public ResponseEntity<Object> plusQuantityProduct(@PathVariable Long id, @PathVariable Long amount) {
        return ResponseUtil.generateResponse("Successfully increased stock!", HttpStatus.OK, productService.addQuantity(id, amount));
    }

    @PutMapping("/minus/{id}/{amount}")
    public ResponseEntity<Object> minusQuantityProduct(@PathVariable Long id, @PathVariable Long amount) {
        return ResponseUtil.generateResponse("Stock successfully reduced!", HttpStatus.OK, productService.removeQuantity(id, amount));
    }
}
