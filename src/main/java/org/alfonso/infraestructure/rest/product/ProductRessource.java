package org.alfonso.infraestructure.rest.product;

import org.alfonso.domain.PriceUseCase;
import org.alfonso.domain.ProductUseCase;
import org.alfonso.domain.price.Price;
import org.alfonso.domain.product.Product;
import org.alfonso.domain.product.ProductId;
import org.alfonso.infraestructure.rest.product.request.CreatePriceRequest;
import org.alfonso.infraestructure.rest.product.request.CreateProductRequest;
import org.alfonso.infraestructure.rest.product.request.PriceRequest;
import org.alfonso.infraestructure.rest.product.response.ProductResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class ProductRessource
{
    private final ProductUseCase productUseCase;
    private final PriceUseCase priceUseCase;
    private final ProductRessourceAdapter adapter;

    public ProductRessource(ProductUseCase productUseCase, PriceUseCase priceUseCase, ProductRessourceAdapter adapter)
    {
        this.productUseCase = productUseCase;
        this.priceUseCase = priceUseCase;
        this.adapter = adapter;
    }

    @PostMapping("product/")
    public ProductResponse addProduct(@RequestBody CreateProductRequest request)
    {
        Product product = adapter.toDomain(request);
        productUseCase.addProduct(product);
        return adapter.toRessource(product);
    }

    @PostMapping("product/addPrice/")
    public void addPrice(@RequestBody CreatePriceRequest request)
    {
        ProductId productId = new ProductId(UUID.fromString(request.getProductId()));
        Price price = adapter.toRessource(request);

        productUseCase.addPrice(productId, price);
    }

    @GetMapping("product/{id}")
    public ProductResponse findProduct(@PathVariable String id)
    {
        ProductId productId = new ProductId(UUID.fromString(id));

        return productUseCase.findProduct(productId)
            .map(adapter::toRessource)
            .orElse(null);
    }

    @DeleteMapping("product/{id}")
    public void deleteProduct(@PathVariable String id)
    {
        ProductId productId = new ProductId(UUID.fromString(id));
        productUseCase.deleteProduct(productId);
    }
}
