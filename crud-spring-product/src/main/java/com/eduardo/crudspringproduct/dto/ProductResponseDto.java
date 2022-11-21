package com.eduardo.crudspringproduct.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.eduardo.crudspringproduct.model.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductResponseDto {

    public ProductResponseDto(Integer id, Integer idCategory, String description, LocalDate purchaseDate,
            BigDecimal price) {
        this.id = id;
        this.idCategory = idCategory;
        this.description = description;
        this.purchaseDate = purchaseDate;
        this.price = price;
    }

    @JsonProperty("_id")
    private Integer id;

    @JsonProperty("_idCategory")
    private Integer idCategory;

    private String description;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate purchaseDate;

    private BigDecimal price;

    public static ProductResponseDto convert(Product product) {
        return new ProductResponseDto(product.getId(), product.getCategory().getId(), product.getDescription(),
                product.getPurchaseDate(), product.getPrice());
    }
}
