package org.alfonso.domain.price;

import java.util.Objects;
import java.util.UUID;

public class BrandId
{
    private final UUID value;

    public BrandId(UUID value)
    {
        this.value = value;
    }

    public BrandId()
    {
        this.value = UUID.randomUUID();
    }

    public UUID getValue()
    {   return value;}

    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass()) return false;
        BrandId brandId = (BrandId) o;
        return Objects.equals(value, brandId.value);
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(value);
    }
}
