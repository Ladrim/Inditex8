package org.alfonso.spring;

import org.alfonso.application.ProductRepository;
import org.alfonso.infraestructure.repositories.h2.ProductInH2Adapter;
import org.alfonso.infraestructure.repositories.h2.ProductInH2Repository;
import org.alfonso.infraestructure.repositories.memory.ProductInMemoryRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ProductRepositoryBeanConfig
{
    @Bean @Qualifier("inH2")
    public ProductRepository productInH2Repository(ProductInH2Adapter adapter, DataSource dataSource)
    {
        return new ProductInH2Repository(adapter, dataSource);
    }

    @Bean @Qualifier("inMemory")
    public ProductRepository productInMemoryRepository()
    {
        return new ProductInMemoryRepository();
    }
}
