package org.alfonso.spring;

import org.alfonso.application.DateFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DateFormatterBeanConfig
{
    @Bean
    public DateFormatter dateFormatter()
    {
        return new DateFormatter();
    }
}
