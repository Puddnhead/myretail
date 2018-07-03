package com.wilklow.service.rest;

import com.wilklow.domain.Product;
import com.wilklow.service.client.ProductService;

import java.math.BigDecimal;

/**
 * Rest layer for {@link ProductService}
 *
 * Created by MVW on 7/2/2018.
 */
public class ProductController implements ProductService {

    /**
     * Fetch a product by id
     *
     * @param id product id
     * @return a product for the given id
     * @throws com.wilklow.exceptions.ProductNotFoundException
     */
    public Product getProduct(String id) {
        return new Product("1", "blah", new BigDecimal(100));
    }
}
