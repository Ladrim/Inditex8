package org.alfonso.spring;

import org.alfonso.application.ProductRepository;
import org.alfonso.domain.PriceUseCase;
import org.alfonso.domain.ProductUseCase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseBeanConfig
{
    @Bean
    public ProductUseCase productUseCase(@Qualifier("inH2")ProductRepository productRepository)
    {
        return new ProductUseCase(productRepository);
    }

    @Bean
    public PriceUseCase priceUseCase()
    {
        return new PriceUseCase();
    }
}
