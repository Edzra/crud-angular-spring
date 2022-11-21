package com.eduardo.crudspringproduct.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.eduardo.crudspringproduct.model.Category;
import com.eduardo.crudspringproduct.model.Product;
import com.eduardo.crudspringproduct.repository.CategoryRepository;
import com.eduardo.crudspringproduct.repository.ProductRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    /* Test get method */
    @Test
    void getList() throws Exception {
        mockMvc.perform(get("/api/products")).andDo(print()).andExpect(status().isOk());
    }

    /* Test get method by id */
    @Test
    void getProductById() throws Exception {
        // Creates a valid category for the product to reference.
        Category testCategory = new Category();
        testCategory.setName("TestCategory");

        // Save a product for which to test the getById method.
        categoryRepository.save(testCategory);

        // Save a product with that category.
        Product testProduct = new Product();

        testProduct.setPrice(new BigDecimal("0.00"));
        testProduct.setDescription("TestProduct");
        testProduct.setPurchaseDate(LocalDate.parse("11/11/2011", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        testProduct.setCategory(testCategory);

       
        productRepository.save(testProduct);

        // Performs an get by id of the testProduct and expects to find it.
        mockMvc.perform(get("/api/products/{id}", testProduct.getId()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$._id").value(testProduct.getId()))
                .andExpect(jsonPath("$.description").value(testProduct.getDescription()));
    }

    /* Test product creation by post method */
    @Test
    void createProduct() throws Exception {
        // Creates a valid category for the product to reference.
        Category testCategory = new Category();
        testCategory.setName("TestCategory");
        categoryRepository.save(testCategory);

        final String jsonNewProduct = String.format(
                "{\"description\":\"TestProduct\",\"purchaseDate\":\"19/11/2022\",\"price\":5.00,\"_idCategory\":%d}",
                testCategory.getId());

       
        // Performs the creation and expects the correct response.                
        mockMvc.perform(
                post("/api/products").contentType(MediaType.APPLICATION_JSON).content(jsonNewProduct))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$._id").exists())
                .andExpect(jsonPath("$.description").exists())
                .andExpect(jsonPath("$._idCategory").exists())
                .andExpect(jsonPath("$.purchaseDate").exists())
                .andExpect(jsonPath("$.price").exists());
    }

    /* Test product update by put method */
    @Test
    void updateProduct() throws Exception {
        // Creates a valid category for the product to reference.
        Category testCategory = new Category();
        testCategory.setName("TestCategory");
        categoryRepository.save(testCategory);

        // Save a product for which to test the update method.
        Product testProduct = new Product();

        testProduct.setPrice(new BigDecimal("0.00"));
        testProduct.setDescription("TestProduct");
        testProduct.setPurchaseDate(LocalDate.parse("11/11/2011", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        testProduct.setCategory(testCategory);

        // Saves the product.
        productRepository.save(testProduct);

        final String jsonProductUpdate = String.format(
                "{\"description\":\"TestProduct\",\"purchaseDate\":\"19/11/2022\",\"price\":5.00,\"_idCategory\":%d}",
                testCategory.getId());

        mockMvc.perform(put("/api/products/{id}", testProduct.getId()).contentType(MediaType.APPLICATION_JSON)
                .content(jsonProductUpdate))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").exists())
                .andExpect(jsonPath("$._idCategory").exists())
                .andExpect(jsonPath("$.purchaseDate").exists())
                .andExpect(jsonPath("$.price").exists());
    }

    /* Test delete method */
    @Test
    void deleteProduct() throws Exception {
        // Creates a valid category for the product to reference.
        Category testCategory = new Category();
        testCategory.setName("TestCategory");
        categoryRepository.save(testCategory);

        // Save a product for which to test the delete method.
        Product testProduct = new Product();

        testProduct.setPrice(new BigDecimal("0.00"));
        testProduct.setDescription("TestProduct");
        testProduct.setPurchaseDate(LocalDate.parse("11/11/2011", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        testProduct.setCategory(testCategory);

        // Saves the product.
        productRepository.save(testProduct);

        mockMvc.perform(delete("/api/products/{id}", testProduct.getId()))
                .andExpect(status().isNoContent());
    }
}
