package com.myretail.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Objects;

/**
 * Product POJO
 *
 * Created by MVW on 7/2/2018.
 */
@JsonDeserialize(builder = Product.Builder.class)
public class Product {

    /**
     * Product id
     */
    private String productId;

    /**
     * Product name
     */
    private String name;

    /**
     * Product price
     */
    private Price price;

    private Product(Builder builder) {
        this.productId = builder.productId;
        this.name = builder.name;
        this.price = builder.price;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public Price getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId) &&
                Objects.equals(name, product.name) &&
                Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, name, price);
    }

    public static final class Builder {
        private String productId;
        private String name;
        private Price price;

        @JsonProperty("productId")
        public Builder productId(String productId) {
            this.productId = productId;
            return this;
        }

        @JsonProperty("name")
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        @JsonProperty("price")
        public Builder price(Price price) {
            this.price = price;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
