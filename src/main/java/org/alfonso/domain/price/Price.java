package org.alfonso.domain.price;

public class Price
{
    private final PriceId priceId;
    private final BrandId brandId;
    private final DateInRange dateInRange;
    private final Money money;
    private final Priority priority;

    public Price(PriceId priceId, BrandId brandId, DateInRange dateInRange, Money money, Priority priority)
    {
        this.priceId = priceId;
        this.brandId = brandId;
        this.dateInRange = dateInRange;
        this.money = money;
        this.priority = priority;
    }

    public PriceId getPriceId()
    {   return priceId;}

    public BrandId getBrandId()
    {   return brandId;}

    public DateInRange getDateInRange()
    {   return dateInRange;}

    public Money getMoney()
    {   return money;}

    public Priority getPriority()
    {   return priority;}
}
