package com.myretail.service.impl;

import com.myretail.service.api.ProductNameService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Rest Client for fetching product names
 *
 * Created by MVW on 7/3/2018.
 */
@Component
public class ProductNameRestClient implements ProductNameService {

    @Value("${rest.productname.uri}")
    private String productNameRestUri;

    @Override
    public String getProductName(String productId) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(productNameRestUri + productId, String.class);
    }
}
