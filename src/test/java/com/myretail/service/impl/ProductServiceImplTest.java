package com.myretail.service.impl;

import com.myretail.data.api.PriceRepository;
import com.myretail.domain.Currency;
import com.myretail.domain.Price;
import com.myretail.domain.Product;
import com.myretail.service.api.ProductNameService;
import com.myretail.service.exception.InvalidUpdateException;
import com.myretail.service.exception.ProductNotFoundException;
import com.myretail.util.Outcome;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link ProductServiceImpl}
 *
 * Created by MVW on 7/3/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private ProductNameService productNameService;

    private static final String PRODUCT_ID = "10000000";
    private static final String PRODUCT_NAME = "Really Big Jigsaw Puzzle";
    private static final Price PRICE = new Price(new BigDecimal("10000000"), Currency.MXN);

    @Test
    public void testGetProduct() {
        when(priceRepository.getProductPrice(PRODUCT_ID)).thenReturn(PRICE);
        when(productNameService.getProductName(PRODUCT_ID)).thenReturn(PRODUCT_NAME);

        Product product = productService.getProduct(PRODUCT_ID);
        assertThat(product, is(new Product(PRODUCT_ID, PRODUCT_NAME, PRICE)));
    }

    @Test(expected = ProductNotFoundException.class)
    public void testGetProductNoPrice() {
        when(priceRepository.getProductPrice(PRODUCT_ID)).thenReturn(null);
        productService.getProduct(PRODUCT_ID);
    }

    @Test(expected = ProductNotFoundException.class)
    public void testGetProductNoProductName() {
        when(priceRepository.getProductPrice(PRODUCT_ID)).thenReturn(PRICE);
        when(productNameService.getProductName(PRODUCT_ID)).thenReturn(null);
        productService.getProduct(PRODUCT_ID);
    }

    @Test
    public void testUpdatePrice() {
        when(priceRepository.updatePrice(PRODUCT_ID, PRICE)).thenReturn(Outcome.SUCCESS);
        Product product = new Product(PRODUCT_ID, PRODUCT_NAME, PRICE);
        Product updatedProduct = productService.updatePrice(product);
        assertThat(updatedProduct, is(product));
    }

    @Test(expected = InvalidUpdateException.class)
    public void testUpdatePriceNoMatchingProduct() {
        when(priceRepository.updatePrice(PRODUCT_ID, PRICE)).thenReturn(Outcome.FAILURE);
        Product product = new Product(PRODUCT_ID, PRODUCT_NAME, PRICE);
        productService.updatePrice(product);
    }
}