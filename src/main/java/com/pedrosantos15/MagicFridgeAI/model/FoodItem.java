package com.pedrosantos15.MagicFridgeAI.model;

import com.pedrosantos15.MagicFridgeAI.enums.CategoryEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "food_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;
}
