package org.alfonso.domain.price;

import java.util.Objects;
import java.util.UUID;

public class PriceId
{
    private final UUID value;

    public PriceId(UUID value)
    {
        this.value = value;
    }

    public PriceId()
    {
        this.value = UUID.randomUUID();
    }

    public UUID getValue()
    {   return value;}

    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass()) return false;
        PriceId priceId1 = (PriceId) o;
        return Objects.equals(value, priceId1.value);
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(value);
    }
}
