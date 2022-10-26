package org.example.spring_hw2;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {

    private long id;
    private String name;
    private double price;
}
