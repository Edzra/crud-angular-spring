package com.eduardo.crudspringproduct.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty("_id")
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category")
    private Category category;

    @Column(nullable = false)
    private String description;

    @Column(length = 50, nullable = false)
    private LocalDate purchaseDate;

    @Column(length = 50, nullable = false)
    private BigDecimal price;
}
