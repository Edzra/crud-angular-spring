package com.eduardo.crudspringproduct;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.eduardo.crudspringproduct.model.Category;
import com.eduardo.crudspringproduct.model.Product;
import com.eduardo.crudspringproduct.repository.CategoryRepository;
import com.eduardo.crudspringproduct.repository.ProductRepository;

@SpringBootApplication
public class CrudSpringProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringProductApplication.class, args);
	}

	// Simulate inital values on the database.
	@Bean
	CommandLineRunner initDatabase(CategoryRepository categoryRepository, ProductRepository productRepository) {
		return args -> {
			categoryRepository.deleteAll();
			productRepository.deleteAll();

			Category c1;
			Category c2;
			Category c3;
			Category c4;
			Category c5;

			c1 = new Category();
			c1.setName("Eletrodoméstico");

			c2 = new Category();
			c2.setName("Eletrônico");

			c3 = new Category();
			c3.setName("Alimento");

			c4 = new Category();
			c4.setName("Vestuário");

			c5 = new Category();
			c5.setName("Limpeza");

			categoryRepository.save(c1);
			categoryRepository.save(c2);
			categoryRepository.save(c3);
			categoryRepository.save(c4);
			categoryRepository.save(c5);

			final DateTimeFormatter acceptedDateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			Product p1 = new Product();
			p1.setDescription("Geladeira");
			p1.setPrice(new BigDecimal("4550.00"));
			p1.setPurchaseDate(LocalDate.parse("06/07/2020", acceptedDateFormat));
			p1.setCategory(c1);

			Product p2 = new Product();
			p2.setDescription("Televisão Smart 55");
			p2.setPrice(new BigDecimal("2999.99"));
			p2.setPurchaseDate(LocalDate.parse("02/02/2018", acceptedDateFormat));
			p2.setCategory(c2);

			Product p3 = new Product();
			p3.setDescription("Televisão Smart 32");
			p3.setPrice(new BigDecimal("1600."));
			p3.setPurchaseDate(LocalDate.parse("20/04/2017", acceptedDateFormat));
			p3.setCategory(c2);

			Product p4 = new Product();
			p4.setDescription("Refrigerante 2L");
			p4.setPrice(new BigDecimal("6.99"));
			p4.setPurchaseDate(LocalDate.parse("12/07/2021", acceptedDateFormat));
			p4.setCategory(c3);

			Product p5 = new Product();
			p5.setDescription("Pizza Congelada");
			p5.setPrice(new BigDecimal("16.00"));
			p5.setPurchaseDate(LocalDate.parse("11/09/2022", acceptedDateFormat));
			p5.setCategory(c3);

			Product p6 = new Product();
			p6.setDescription("Camisa");
			p6.setPrice(new BigDecimal("75.00"));
			p6.setPurchaseDate(LocalDate.parse("01/12/2019", acceptedDateFormat));
			p6.setCategory(c4);

			Product p7 = new Product();
			p7.setDescription("Calça Jeans");
			p7.setPrice(new BigDecimal("300.00"));
			p7.setPurchaseDate(LocalDate.parse("29/04/2018", acceptedDateFormat));
			p7.setCategory(c4);

			Product p8 = new Product();
			p8.setDescription("Boné");
			p8.setPrice(new BigDecimal("56.75"));
			p8.setPurchaseDate(LocalDate.parse("13/12/2015", acceptedDateFormat));
			p8.setCategory(c4);

			Product p9 = new Product();
			p9.setDescription("Sabão em Pó");
			p9.setPrice(new BigDecimal("8.50"));
			p9.setPurchaseDate(LocalDate.parse("13/06/2022", acceptedDateFormat));
			p9.setCategory(c5);

			Product p10 = new Product();
			p10.setDescription("Água Sanitária");
			p10.setPrice(new BigDecimal("12.30"));
			p10.setPurchaseDate(LocalDate.parse("18/12/2022", acceptedDateFormat));
			p10.setCategory(c5);

			Product p11 = new Product();
			p11.setDescription("Esponja");
			p11.setPrice(new BigDecimal("4.00"));
			p11.setPurchaseDate(LocalDate.parse("22/11/2015", acceptedDateFormat));
			p11.setCategory(c5);

			productRepository.save(p1);
			productRepository.save(p2);
			productRepository.save(p3);
			productRepository.save(p4);
			productRepository.save(p5);
			productRepository.save(p6);
			productRepository.save(p7);
			productRepository.save(p8);
			productRepository.save(p9);
			productRepository.save(p10);
			productRepository.save(p11);
		};
	}
}
