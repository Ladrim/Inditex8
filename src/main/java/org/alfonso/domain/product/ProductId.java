package org.alfonso.domain.product;

import java.util.Objects;
import java.util.UUID;

public class ProductId
{
    private final UUID value;

    public ProductId(UUID value)
    {
        this.value = value;
    }

    public ProductId()
    {
        this.value = UUID.randomUUID();
    }

    public UUID getValue()
    {   return value;}

    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass()) return false;
        ProductId productId = (ProductId) o;
        return Objects.equals(value, productId.value);
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(value);
    }
}
