package com.myretail.service.rest;

import com.myretail.data.api.PriceRepository;
import com.myretail.service.api.ProductNameService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * REST tests for the Product Service
 *
 * Created by MVW on 7/3/2018.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private ProductNameService productNameService;

    private static final String PRODUCT_ID = "abc";
    private static final String PRODUCT_NAME = "Building Made out of Napkins";
    private static final BigDecimal PRICE = new BigDecimal(99.99).setScale(2, RoundingMode.HALF_UP);

    @Test
    public void testGetProduct() throws Exception {
        when(priceRepository.getProductPrice(PRODUCT_ID)).thenReturn(PRICE);
        when(productNameService.getProductName(PRODUCT_ID)).thenReturn(PRODUCT_NAME);

        this.mockMvc.perform(get("/product/" + PRODUCT_ID)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(PRODUCT_NAME))
                .andExpect(jsonPath("$.price").value(PRICE));

    }

    @Test
    public void testGetProductNoPriceInformationReturns404() throws Exception {
        when(priceRepository.getProductPrice(PRODUCT_ID)).thenReturn(null);

        this.mockMvc.perform(get("/product/" + PRODUCT_ID)).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    public void testGetProductNoNameInformationReturns404() throws Exception {
        when(priceRepository.getProductPrice(PRODUCT_ID)).thenReturn(PRICE);
        when(productNameService.getProductName(PRODUCT_ID)).thenReturn(null);

        this.mockMvc.perform(get("/product/" + PRODUCT_ID)).andDo(print()).andExpect(status().isNotFound());
    }

}