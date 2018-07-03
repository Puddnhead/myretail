package com.myretail.service.impl;

import com.myretail.data.api.PriceRepository;
import com.myretail.domain.Product;
import com.myretail.service.client.ProductNameService;
import com.myretail.service.client.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Service implementation for {@link com.myretail.service.client.ProductService}
 *
 * Created by MVW on 7/3/2018.
 */
@Component
@Qualifier("productServiceImpl")
public class ProductServiceImpl implements ProductService {

    private PriceRepository priceRepository;

    private ProductNameService productNameService;

    public ProductServiceImpl(@NonNull PriceRepository priceRepository, @NonNull ProductNameService productNameService) {
        this.priceRepository = priceRepository;
        this.productNameService = productNameService;
    }

    @Override
    public Product getProduct(String productId) {
        BigDecimal price = priceRepository.getProductPrice(productId);
        String productName = productNameService.getProductName(productId);
        return new Product(productId, productName, price);
    }
}
