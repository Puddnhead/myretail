package com.myretail.service.rest;

import com.myretail.domain.Product;
import com.myretail.service.api.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

/**
 * Rest layer for {@link ProductService}
 *
 * Created by MVW on 7/2/2018.
 */
@RestController
@RequestMapping("/products")
public class ProductController implements ProductService {

    private ProductService productService;

    public ProductController(@Qualifier("productServiceImpl") @NonNull ProductService productService) {
        this.productService = productService;
    }

    /**
     * @see com.myretail.service.api.ProductService#getProduct(String)
     */
    @RequestMapping("/{productId}")
    @Override
    public @ResponseBody Product getProduct(@PathVariable String productId) {
        return productService.getProduct(productId);
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.PUT)
    @Override
    public @ResponseBody Product updatePrice(@RequestBody Product product) {
        return productService.updatePrice(product);
    }
}
