package org.alfonso.domain;

import org.alfonso.application.ProductRepository;
import org.alfonso.domain.price.Price;
import org.alfonso.domain.product.Product;
import org.alfonso.domain.product.ProductId;
import org.alfonso.domain.product.ProductName;

import java.util.Optional;

public class ProductUseCase
{
    private final ProductRepository repository;

    public ProductUseCase(ProductRepository repository)
    {
        this.repository = repository;
    }

    public void addProduct(Product product)
    {
        Optional<Product> productResult =  repository.find((product.getId()));

        if(productResult.isEmpty())
        {
            repository.save(product);
        }
        else
        {
            if(!product.equals(productResult.get()))
            {
                throw new RuntimeException("Ya existe un producto diferente con este ID");
            }
        }
    }

    public void addPrice(ProductId productId, Price price)
    {
        Optional<Product> optionalProduct = repository.find(productId);

        if(optionalProduct.isEmpty())
        {
            throw new RuntimeException("El producto no existe");
        }
        else
        {
            Product productWithNewPrice =  optionalProduct.map(it-> it.addPrice(price)).get();
            repository.save(productWithNewPrice);
        }
    }

    public void renameProduct(ProductId productId, ProductName productName)
    {
        Optional<Product> optionalProduct = repository.find(productId);

        if(optionalProduct.isEmpty())
        {
            throw new RuntimeException("El producto no existe");
        }
        else
        {
            Product productWithNewPrice =  optionalProduct.map(it-> it.changeName(productName)).get();
            repository.save(productWithNewPrice);
        }

    }

    public Optional<Product> findProduct(ProductId productId)
    {
        return  repository.find(productId);
    }

    public void deleteProduct(ProductId productId)
    {
        repository.delete(productId);
    }
}
