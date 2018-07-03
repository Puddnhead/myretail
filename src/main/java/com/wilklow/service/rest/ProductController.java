package com.wilklow.service.rest;

import com.wilklow.domain.Product;
import com.wilklow.exceptions.ProductNotFoundException;
import com.wilklow.service.client.ProductService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Rest layer for {@link ProductService}
 *
 * Created by MVW on 7/2/2018.
 */
@RestController
@RequestMapping("/product")
public class ProductController implements ProductService {

    /**
     * Fetch a product by id
     *
     * @param id product id
     * @return a product for the given id
     * @throws com.wilklow.exceptions.ProductNotFoundException
     */
    @RequestMapping("/{id}")
    public @ResponseBody Product getProduct(@PathVariable String id) {
        return new Product("1", "blah", new BigDecimal(100));
    }
}
