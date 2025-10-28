package com.droiddev.service;

import com.droiddev.model.Product;
import jakarta.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class ProductService implements Serializable {

    private static final long serialVersionUID = 1L;

    // Armazenamento em memória (único para toda a aplicação)
    private final List<Product> products = Collections.synchronizedList(new ArrayList<>());

    /** Adiciona um produto novo. */
    public void addProduct(Product product) {
        if (product == null) return;
        synchronized (products) {
            products.add(product);
        }
    }

    /** Retorna todos os produtos (cópia defensiva). */
    public List<Product> getAllProducts() {
        synchronized (products) {
            return new ArrayList<>(products);
        }
    }

    /** Remove um produto, se existir. */
    public void removeProduct(Product product) {
        if (product == null) return;
        synchronized (products) {
            products.remove(product);
        }
    }

    /** Atualiza um produto existente (baseado em igualdade de nome, por exemplo). */
    public void updateProduct(Product updated) {
        if (updated == null) return;
        synchronized (products) {
            for (int i = 0; i < products.size(); i++) {
                Product existing = products.get(i);
                if (existing.getName() != null && existing.getName().equals(updated.getName())) {
                    products.set(i, updated);
                    break;
                }
            }
        }
    }

    /** Limpa todos os produtos da memória (útil para testes). */
    public void clearAll() {
        synchronized (products) {
            products.clear();
        }
    }
}
