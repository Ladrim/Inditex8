package org.alfonso.infraestructure.repositories.memory;

import org.alfonso.application.ProductRepository;
import org.alfonso.domain.product.Product;
import org.alfonso.domain.product.ProductId;

import java.util.Optional;

public class ProductInMemoryRepository implements ProductRepository
{
    @Override
    public void save(Product product)
    {

    }

    @Override
    public Optional<Product> find(ProductId productId)
    {
        return Optional.empty();
    }

    @Override
    public void delete(ProductId productId)
    {

    }
}
