package org.example.spring_hw2;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
@Scope(scopeName = "prototype")
public class Cart {

    private Map<Long, Integer> productCart; // храним в корзине id продукта и его количество для простоты целочисленное
    private final ProductRepository productRepository;

    public Cart(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void init() {
        productCart = new HashMap<>();
    }

    public void addProductById(Long id, Integer count) {
        productCart.put(id, count);
    }

    public void deletePartProductById(Long id, Integer count) {
        if (!productCart.containsKey(id)) {
            System.out.println("товара в корзине не существует");
            return;
        }
        Integer currentAmount = productCart.get(id);
        String productName = productRepository.getProductById(id).getName();
        if (count >= currentAmount) { // если хотим убрать из корзины больше чем в ней есть
            productCart.remove(id);
            System.out.println("Товар " + productName + " удален из корзины полностью");
            return;
        }
        Integer amount = currentAmount - count;
        System.out.println("Уменьшили количество " + productName
                + " было " + currentAmount + " стало " + amount);
        productCart.replace(id, amount);
    }

    public void deleteAllProductById(Long id) {
        System.out.println("Убрали весь товар " + productRepository.getProductById(id).getName() + " из корзины");
        productCart.remove(id);
    }

    public Map<Long, Integer> getProductCart() {
        return productCart;
    }

    public Map<Product, Integer> getProductsInCart() {
        Map<Product, Integer> products = new HashMap<>();
        productCart.forEach((k, v) -> products.put(productRepository.getProductById(k), v));
        return products;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("В корзине содержится: \n");
        getProductsInCart().forEach((k, v) -> stringBuilder.append("  ").append(k).append(" Количество ").append(v).append(" штук \n"));
        return stringBuilder.toString();
    }
}
