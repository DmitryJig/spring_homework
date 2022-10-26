package org.example.spring_hw2;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {

    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public Product getProductById(long id) {
        return products.stream().filter(p -> p.getId() == id).findFirst().get();
    }

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        products.add(new Product(1L, "Carrot", 20));
        products.add(new Product(2L, "Tomato", 50));
        products.add(new Product(3L, "Potato", 150));
        products.add(new Product(4L, "Meat", 700));
        products.add(new Product(5L, "Cheese", 1000));
    }


}
