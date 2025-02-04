package org.alfonso.infraestructure.repositories.h2;

import org.alfonso.application.DateFormatter;
import org.alfonso.domain.price.*;
import org.alfonso.domain.product.Product;
import org.alfonso.domain.product.ProductId;
import org.alfonso.domain.product.ProductName;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ProductInH2Adapter
{
    private final DateFormatter dateFormatter;

    public ProductInH2Adapter(DateFormatter dateFormatter)
    {
        this.dateFormatter = dateFormatter;
    }

    public ProductInH2Entity toEntity(Product product)
    {
        String productId = product.getId().getValue().toString();
        String productName = product.getName().getValue();

        return new ProductInH2Entity(productId, productName);
    }

    public PriceInH2Entity toEntity(Price price, Product product)
    {
        String priceId = price.getPriceId().getValue().toString();
        String brandId = price.getBrandId().getValue().toString();
        String startDate = dateFormatter.parseDate(price.getDateInRange().getStartDate());
        String endDate = dateFormatter.parseDate(price.getDateInRange().getEndDate());
        String currencyType = price.getMoney().getType().getValue();
        Float amount = price.getMoney().getAmount();
        Integer priority = price.getPriority().getValue();
        String productId = product.getId().getValue().toString();

        return new PriceInH2Entity(priceId, brandId, startDate, endDate, currencyType, amount, priority, productId);
    }

    public List<PriceInH2Entity>toPriceEntities(Product product) {

        return product.getPrices().stream()
            .map( it -> toEntity(it, product))
            .toList();
    }

    public Price toDomain(PriceInH2Entity priceInH2Entity)
    {
        PriceId priceId = new PriceId(UUID.fromString(priceInH2Entity.getPriceId()));
        BrandId brandId = new BrandId(UUID.fromString(priceInH2Entity.getBrandId()));
        Date startDate = dateFormatter.parseString(priceInH2Entity.getStartDate());
        Date endDate = dateFormatter.parseString(priceInH2Entity.getEndDate());
        DateInRange dateInRange = new DateInRange(startDate, endDate);
        CurrencyType currencyType = CurrencyType.valueOf(priceInH2Entity.getCurrencyType());
        Float amount = priceInH2Entity.getAmount();
        Priority priority = new Priority(priceInH2Entity.getPriority());
        Money money = new Money(currencyType, amount);

        return new Price(priceId, brandId, dateInRange, money, priority);
    }

    public Product toDomain(ProductInH2Entity productInH2Entity, List<PriceInH2Entity> priceInH2Entities)
    {
        ProductId productId = new ProductId(UUID.fromString(productInH2Entity.getProductId()));
        ProductName productName = new ProductName(productInH2Entity.getProductName());
        List<Price> prices = priceInH2Entities.stream().map(this::toDomain).toList();

        return new Product(productId, productName, prices);
    }
}
