package org.alfonso.domain.price;

public enum CurrencyType
{
    EUR("EUR");

    private final String value;

    CurrencyType(String value)
    {
        this.value = value;
    }

    public String getValue()
    {return value;}
}
