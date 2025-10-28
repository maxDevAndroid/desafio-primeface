package com.teste.bean;

import com.teste.model.Product;
import com.teste.service.ProductService;
import com.teste.util.FacesUtil;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("productBean")
@SessionScoped
public class ProductBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Product product = new Product();

    @Inject
    private ProductService productService;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    public void save() {
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            FacesUtil.addErrorMessage("Nome do produto é obrigatório.");
            return;
        }
        if (product.getPrice() == null) {
            FacesUtil.addErrorMessage("Preço é obrigatório.");
            return;
        }

        productService.addProduct(new Product(product.getName().trim(), product.getPrice(), product.getCategory()));
        FacesUtil.addInfoMessage("Produto salvo com sucesso.");
        product = new Product(); // limpa formulário
    }
}
