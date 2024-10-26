package com.control.wepink.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {

    private String name;
    private Long quantity;
    private Timestamp changeDate;
    private String image;
}
