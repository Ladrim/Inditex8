package org.alfonso.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseProperties
{
    @Value("${database.h2.url}")
    private String url;

    @Value("${database.h2.driverClassName}")
    private String driverClassName;

    @Value("${database.h2.username}")
    private String username;

    @Value("${database.h2.password}")
    private String password;

    public String getUrl()
    {   return url;}

    public String getDriverClassName()
    {   return driverClassName;}

    public String getUsername()
    {   return username;}

    public String getPassword()
    {   return password;}
}
