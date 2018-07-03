package com.wilklow.service.impl;

import com.wilklow.data.api.PriceRepository;
import com.wilklow.domain.Product;
import com.wilklow.service.client.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Service implementation for {@link com.wilklow.service.client.ProductService}
 *
 * Created by MVW on 7/3/2018.
 */
@Component
@Qualifier("productServiceImpl")
public class ProductServiceImpl implements ProductService {

    private PriceRepository priceRepository;

    public ProductServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Product getProduct(String productId) {
        BigDecimal price = priceRepository.getProductPrice(productId);
        return new Product(productId, "DummyName", price);
    }
}
