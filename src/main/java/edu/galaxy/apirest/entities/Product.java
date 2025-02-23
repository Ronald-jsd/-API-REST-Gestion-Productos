package edu.galaxy.apirest.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Product")
@Table(name = "tbl_products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    private String description;
    private Integer stock;
    private Double precio;
    private String image;
    private Boolean state;
}
