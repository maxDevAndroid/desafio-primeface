package com.teste.service;

import com.teste.model.Product;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class ProductService {
    // armazenamento em memória (singleton do container)
    private final List<Product> products = Collections.synchronizedList(new ArrayList<>());

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getAllProducts() {
        // devolve cópia para evitar alteração direta da lista
        synchronized (products) {
            return new ArrayList<>(products);
        }
    }
}
