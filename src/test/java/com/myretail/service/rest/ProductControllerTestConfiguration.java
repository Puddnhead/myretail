package com.myretail.service.rest;

import com.myretail.data.api.PriceRepository;
import com.myretail.service.api.ProductNameService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * Test Spring configuration for {@link ProductControllerTest}
 *
 * Created by MVW on 7/3/2018.
 */
@Profile("test")
@Configuration
public class ProductControllerTestConfiguration {

    @Bean
    @Primary
    public PriceRepository priceRepository() {
        return Mockito.mock(PriceRepository.class);
    }

    @Bean
    @Primary
    public ProductNameService productNameService() {
        return Mockito.mock(ProductNameService.class);
    }
}
