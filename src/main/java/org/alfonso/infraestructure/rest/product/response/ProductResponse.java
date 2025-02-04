package org.alfonso.infraestructure.rest.product.response;

import java.util.List;

public class ProductResponse
{
    private final String productId;
    private final String productName;
    private final List<PriceResponse> priceResponses;

    public ProductResponse(String productId, String productName, List<PriceResponse> priceResponses)
    {
        this.productId = productId;
        this.productName = productName;
        this.priceResponses = priceResponses;
    }

    public String getProductId()
    {   return productId;}

    public String getProductName()
    {   return productName;}

    public List<PriceResponse> getPriceResponseDTOS()
    {   return priceResponses;}
}
