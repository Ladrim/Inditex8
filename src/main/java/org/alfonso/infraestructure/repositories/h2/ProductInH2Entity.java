package org.alfonso.infraestructure.repositories.h2;

public class ProductInH2Entity
{
    private final String productId;
    private final String productName;

    public ProductInH2Entity(String productId, String productName)
    {
        this.productId = productId;
        this.productName = productName;
    }

    public String getProductId()
    {   return productId;}

    public String getProductName()
    {   return productName;}
}
