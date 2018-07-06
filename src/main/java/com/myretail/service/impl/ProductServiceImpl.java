package com.myretail.service.impl;

import com.myretail.data.api.PriceRepository;
import com.myretail.domain.Outcome;
import com.myretail.domain.Price;
import com.myretail.domain.Product;
import com.myretail.security.RequiresUniverseMastery;
import com.myretail.service.api.ProductNameService;
import com.myretail.service.api.ProductService;
import com.myretail.service.exception.InvalidUpdateException;
import com.myretail.service.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Service implementation for {@link com.myretail.service.api.ProductService}
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

    /**
     * @see com.myretail.service.api.ProductService#getProduct(String)
     */
    @Override
    public Product getProduct(String productId) {
        Price price = priceRepository.getProductPrice(productId);
        if (price == null) {
            throw new ProductNotFoundException("Error fetching product price information");
        }

        String productName = productNameService.getProductName(productId);
        if (StringUtils.isEmpty(productName)) {
            throw new ProductNotFoundException("Error fetching product name information");
        }

        return new Product.Builder()
            .productId(productId)
            .name(productName)
            .price(price)
            .build();
    }

    @RequiresUniverseMastery
    @Override
    public Product updatePrice(Product product) {
        Outcome outcome = priceRepository.updatePrice(product.getProductId(), product.getPrice());
        if (outcome.equals(Outcome.FAILURE)) {
            throw new InvalidUpdateException("Invalid product update criteria");
        }

        return product;
    }
}
