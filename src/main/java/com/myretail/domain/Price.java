package com.myretail.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.myretail.domain.serialization.PriceSerializer;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Pojo representing a price
 *
 * Created by MVW on 7/4/2018.
 */
public class Price {

    @JsonSerialize(using = PriceSerializer.class)
    private final BigDecimal value;

    private final Currency currency;

    @JsonCreator
    public Price(@JsonProperty("value") BigDecimal value, @JsonProperty("currencyCode") Currency currencyCode) {
        this.value = value;
        this.currency = currencyCode;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return Objects.equals(value, price.value) &&
                currency == price.currency;
    }

    @Override
    public int hashCode() {

        return Objects.hash(value, currency);
    }
}
