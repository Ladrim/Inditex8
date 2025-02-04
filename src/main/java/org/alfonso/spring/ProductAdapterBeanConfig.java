package org.alfonso.spring;

import org.alfonso.application.DateFormatter;
import org.alfonso.infraestructure.repositories.h2.ProductInH2Adapter;
import org.alfonso.infraestructure.rest.product.ProductRessourceAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductAdapterBeanConfig
{
    @Bean
    public ProductRessourceAdapter productRessourceAdapter(DateFormatter dateFormatter)
    {
        return new ProductRessourceAdapter(dateFormatter);
    }

    @Bean
    public ProductInH2Adapter productInH2Adapter(DateFormatter dateFormatter)
    {
        return new ProductInH2Adapter(dateFormatter);
    }
}
