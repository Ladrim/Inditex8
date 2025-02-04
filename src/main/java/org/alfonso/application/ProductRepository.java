package org.alfonso.application;

import org.alfonso.domain.product.Product;
import org.alfonso.domain.product.ProductId;

import java.util.Optional;

public interface ProductRepository
{
    Optional<Product> find(ProductId productId);

    void delete(ProductId productId);

    void save(Product product);
}
