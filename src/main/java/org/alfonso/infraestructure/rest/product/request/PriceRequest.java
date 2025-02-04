package org.alfonso.infraestructure.rest.product.request;

public class PriceRequest
{
    private final String brandId;
    private final String startDate;
    private final String endDate;
    private final String currencyType;
    private final Float amount;
    private final Integer priority;

    public PriceRequest(String brandId, String startDate, String endDate, String currencyType, Float amount, Integer priority)
    {
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.currencyType = currencyType;
        this.amount = amount;
        this.priority = priority;
    }

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
}
