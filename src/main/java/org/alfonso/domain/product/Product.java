package org.alfonso.domain.product;

import org.alfonso.domain.price.BrandId;
import org.alfonso.domain.price.Price;

import java.util.*;

public class Product
{
    private final ProductId id;
    private final ProductName name;
    private final List<Price>prices;

    public Product(ProductId id, ProductName name, List<Price>prices)
    {
        this.id = id;
        this.name = name;
        this.prices = prices;
    }

    public ProductId getId()
    {   return id; }

    public ProductName getName()
    {   return name; }

    public List<Price> getPrices()
    {
        return new ArrayList<>(prices);
    }

    public Optional<Price> findPrice(BrandId brandId, Date date)
    {
        return prices.stream()
            .filter(it-> it.getBrandId().equals(brandId))
            .filter(it-> it.getDateInRange().isInRange(date))
            .max(Comparator.comparing(it-> it.getPriority().getValue()));
    }

    public Product changeName(ProductName newName)
    {

        return new Product(id, newName, prices);
    }

    public Product addPrice(Price price)
    {
        List<Price>newPrices = new ArrayList<>(prices);
        newPrices.add(price);
        return new Product(id, name, newPrices);
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(prices, product.prices);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, name, prices);
    }
}
