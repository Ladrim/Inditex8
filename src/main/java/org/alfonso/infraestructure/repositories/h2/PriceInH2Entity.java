package org.alfonso.infraestructure.repositories.h2;

public class PriceInH2Entity
{
    private final String priceId;
    private final String brandId;
    private final String startDate;
    private final String endDate;
    private final String currencyType;
    private final Float amount;
    private final Integer priority;
    private final String productId;

    public PriceInH2Entity(String priceId, String brandId, String startDate, String endDate, String currencyType,
                           Float amount, Integer priority, String productId)
    {
        this.priceId = priceId;
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.currencyType = currencyType;
        this.amount = amount;
        this.priority = priority;
        this.productId = productId;
    }

    public String getPriceId()
    {   return priceId;}

    public String getBrandId()
    {   return brandId;}

    public String getStartDate()
    {   return startDate;}

    public String getEndDate()
    {   return endDate;}

    public String getCurrencyType()
    {   return currencyType;}

    public Float getAmount()
    {   return amount;}

    public Integer getPriority()
    {   return priority;}

    public String getProductId()
    {   return productId;}
}
