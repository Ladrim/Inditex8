package org.alfonso.infraestructure.rest.product.request;

import java.util.List;

public class CreateProductRequest
{
    private final String id;
    private final String name;
    private final List<PriceRequest> priceRequests;

    public CreateProductRequest(String id, String name, List<PriceRequest> priceRequests)
    {
        this.id = id;
        this.name = name;
        this.priceRequests = priceRequests;
    }

    public String getId()
    {   return id;}

    public String getName()
    {   return name;}

    public List<PriceRequest> getPriceDTOS()
    {   return priceRequests;}
}
