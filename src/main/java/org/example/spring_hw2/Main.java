package org.example.spring_hw2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 1. Есть класс Product (id, название, цена). Товары хранятся в бине ProductRepository, в виде List<Product>, при старте в него нужно добавить 5 любых товаров.
 * 2. ProductRepository позволяет получить весь список или один товар по id. Создаем бин Cart, в который можно добавлять и удалять товары по id.
 * 3. Написать консольное приложение, позволяющее управлять корзиной.
 * 4. При каждом запросе корзины из контекста, должна создаваться новая корзина.
 */

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        ProductRepository productRepository = context.getBean("productRepository", ProductRepository.class);
        System.out.println(productRepository.getProductById(5));
        System.out.println(productRepository.getProducts());

        Cart cart1 = context.getBean("cart", Cart.class);
        Cart cart2 = context.getBean("cart", Cart.class);
        cart1.addProductById(1L, 2);
        cart1.addProductById(2L, 5);

        System.out.println(cart1);
        cart1.deletePartProductById(2L, 2);
        cart2.addProductById(3L, 20);
        System.out.println(cart1);
        System.out.println(cart2);

        context.close();
    }
}
