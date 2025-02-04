package org.alfonso.infraestructure.rest.product;

import org.alfonso.application.DateFormatter;
import org.alfonso.domain.price.*;
import org.alfonso.domain.product.Product;
import org.alfonso.domain.product.ProductId;
import org.alfonso.domain.product.ProductName;
import org.alfonso.infraestructure.rest.product.request.CreatePriceRequest;
import org.alfonso.infraestructure.rest.product.request.CreateProductRequest;
import org.alfonso.infraestructure.rest.product.request.PriceRequest;
import org.alfonso.infraestructure.rest.product.response.PriceResponse;
import org.alfonso.infraestructure.rest.product.response.ProductResponse;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ProductRessourceAdapter
{
    private final DateFormatter dateFormatter;

    public ProductRessourceAdapter(DateFormatter dateFormatter)
    {
        this.dateFormatter = dateFormatter;
    }

    public Product toDomain(CreateProductRequest request)
    {
        ProductId productId = new ProductId(UUID.fromString(request.getId()));
        ProductName productName = new ProductName(request.getName());
        List<Price> prices = request.getPriceDTOS().stream()
            .map(this::toDomain)
            .toList();

        return new Product(productId, productName, prices);
    }

    public Price toDomain(PriceRequest request)
    {
        PriceId priceId = new PriceId();
        BrandId brandId = new BrandId(UUID.fromString(request.getBrandId()));
        Date startDate = dateFormatter.parseString(request.getStartDate());
        Date endDate = dateFormatter.parseString(request.getEndDate());
        DateInRange dateInRange = new DateInRange(startDate, endDate);
        CurrencyType currencyType = CurrencyType.valueOf(request.getCurrencyType());
        Float amount = request.getAmount();
        Money money = new Money(currencyType, amount);
        Priority priority = new Priority(request.getPriority());

        return new Price(priceId, brandId, dateInRange, money, priority);
    }

    public ProductResponse toRessource(Product product)
    {
        String productId = product.getId().getValue().toString();
        String productName = product.getName().getValue();
        List<PriceResponse> priceResponses = product.getPrices().stream().map(this::toRessource).toList();

        return new ProductResponse(productId, productName, priceResponses);
    }

    public PriceResponse toRessource(Price price)
    {
        String priceId = price.getPriceId().getValue().toString();
        String brandId = price.getBrandId().getValue().toString();
        String startDate = dateFormatter.parseDate(price.getDateInRange().getStartDate());
        String endDate = dateFormatter.parseDate(price.getDateInRange().getEndDate());
        String currencyType = price.getMoney().getType().getValue();
        Float amount = price.getMoney().getAmount();
        Integer priority = price.getPriority().getValue();

        return new PriceResponse(priceId, brandId, startDate, endDate, currencyType, amount, priority);
    }

    public Price toRessource(CreatePriceRequest request)
    {
        PriceId priceId = new PriceId();
        BrandId brandId = new BrandId(UUID.fromString("bb9a2ac9-6858-4b22-b04d-75f665fd323d"));
        Date startDate = dateFormatter.parseString(request.getStartDate());
        Date endDate = dateFormatter.parseString(request.getEndDate());
        DateInRange dateInRange = new DateInRange(startDate, endDate);
        CurrencyType currencyType = CurrencyType.valueOf(request.getCurrencyType());
        Float amount = request.getAmount();
        Money money = new Money(currencyType, amount);
        Priority priority = new Priority(request.getPriority());

        return new Price(priceId, brandId, dateInRange, money, priority);
    }
}
