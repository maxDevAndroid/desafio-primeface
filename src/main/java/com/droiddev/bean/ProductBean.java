package com.droiddev.bean;

import com.droiddev.model.Product;
import com.droiddev.service.ProductService;
import com.droiddev.util.FacesUtil;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("productBean")
@SessionScoped
public class ProductBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Product product = new Product();

    @Inject
    private ProductService productService;

    // Cache para exibição imediata na tela
    private List<Product> productsCache = new ArrayList<>();

    // --- Getters e Setters ---

    public Product getProduct() {
        if (product == null) {
            product = new Product();
        }
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getProducts() {
        if (productService != null) {
            productsCache = productService.getAllProducts();
        }
        return productsCache;
    }

    // --- Operações principais ---

    public void save() {
        if (product == null) {
            product = new Product();
        }

        if (product.getName() == null || product.getName().trim().isEmpty()) {
            FacesUtil.addErrorMessage("O nome do produto é obrigatório.");
            return;
        }

        if (product.getPrice() == null) {
            FacesUtil.addErrorMessage("O preço é obrigatório.");
            return;
        }

        try {
            Product newProduct = new Product(
                    product.getName().trim(),
                    product.getPrice(),
                    product.getCategory()
            );

            productService.addProduct(newProduct);
            FacesUtil.addInfoMessage("Produto salvo com sucesso.");

            // Atualiza lista e limpa o formulário
            product = new Product();
            productsCache = productService.getAllProducts();

        } catch (Exception e) {
            FacesUtil.addErrorMessage("Erro ao salvar produto: " + e.getMessage());
        }
    }

    public void edit(Product p) {
        if (p != null) {
            this.product = p;
        }
    }

    public void delete(Product p) {
        if (p != null && productService != null) {
            productService.removeProduct(p);
            FacesUtil.addInfoMessage("Produto removido com sucesso.");
            productsCache = productService.getAllProducts();
        }
    }
}
