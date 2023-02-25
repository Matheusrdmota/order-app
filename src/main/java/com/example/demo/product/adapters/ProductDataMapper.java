package com.example.demo.product.adapters;

import com.example.demo.product.entities.ProductCategoryEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "product")
@NoArgsConstructor
@Data
public class ProductDataMapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long barCode;
    @Column
    private Double price;
    @Column
    private ProductCategoryEnum category;
    @Column
    private String productName;
    @Column
    private String description;
    @Column
    private String urlImage;
    @Column
    private Long stockQuantity;
}
