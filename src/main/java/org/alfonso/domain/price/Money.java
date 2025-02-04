package org.alfonso.domain.price;

public class Money
{
    private final CurrencyType type;
    private final Float amount;

    public Money(CurrencyType type, Float amount)
    {
        this.type = type;
        this.amount = amount;
    }

    public CurrencyType getType()
    {   return type;}

    public Float getAmount()
    {   return amount;}
}
