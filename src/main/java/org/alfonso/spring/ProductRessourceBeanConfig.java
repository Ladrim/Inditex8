package org.alfonso.spring;

import org.alfonso.domain.PriceUseCase;
import org.alfonso.domain.ProductUseCase;
import org.alfonso.infraestructure.rest.product.ProductRessource;
import org.alfonso.infraestructure.rest.product.ProductRessourceAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductRessourceBeanConfig
{
    @Bean
    public ProductRessource productRessource(ProductUseCase productUseCase, PriceUseCase priceUseCase, ProductRessourceAdapter adapter)
    {
        return new ProductRessource(productUseCase, priceUseCase ,adapter);
    }
}
