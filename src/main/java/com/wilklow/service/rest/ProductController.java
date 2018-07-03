package com.wilklow.service.rest;

import com.wilklow.domain.Product;
import com.wilklow.service.client.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest layer for {@link ProductService}
 *
 * Created by MVW on 7/2/2018.
 */
@RestController
@RequestMapping("/product")
public class ProductController implements ProductService {

    private ProductService productService;

    public ProductController(@Qualifier("productServiceImpl") @NonNull ProductService productService) {
        this.productService = productService;
    }

    /**
     * Fetch a product by id
     *
     * @param productId product id
     * @return a product for the given id
     */
    @RequestMapping("/{productId}")
    public @ResponseBody Product getProduct(@PathVariable String productId) {
        return productService.getProduct(productId);
    }
}
