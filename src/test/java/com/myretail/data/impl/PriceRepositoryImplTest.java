package com.myretail.data.impl;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Unit test for {@link PriceRepositoryImpl}
 *
 * Created by MVW on 7/3/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class PriceRepositoryImplTest {

    @InjectMocks
    private PriceRepositoryImpl priceRepository;

    @Mock
    private MongoClient mongoClient;

    @Mock
    private MongoDatabase mockDatabase;

    @Mock
    private MongoCollection<Document> mockCollection;

    @Mock
    private FindIterable<Document> mockFindIterable;

    @Mock
    private MongoCursor<Document> mockCursor;

    @Mock
    private Document document;

    private static final String DB_NAME = "db";
    private static final String COLLECTION_NAME = "collection";
    private static final String PRODUCT_ID = "123";

    @Before
    public void setup() {
        ReflectionTestUtils.setField(priceRepository, "dbName", DB_NAME);
        ReflectionTestUtils.setField(priceRepository, "collectionName", COLLECTION_NAME);

        when(mongoClient.getDatabase(anyString())).thenReturn(mockDatabase);
        when(mockDatabase.getCollection(anyString())).thenReturn(mockCollection);
        when(mockCollection.find(any(Bson.class))).thenReturn(mockFindIterable);
        when(mockFindIterable.iterator()).thenReturn(mockCursor);
    }

    @Test
    public void testGetExistingPrice() {
        when(mockCursor.hasNext()).thenReturn(true);
        when(mockCursor.next()).thenReturn(document);
        when(document.getDouble(PriceRepositoryImpl.PRICE_PROPERTY)).thenReturn(9.99);

        BigDecimal price = priceRepository.getProductPrice(PRODUCT_ID);
        assertThat(price, is(new BigDecimal(9.99)));
    }

    @Test
    public void testGetPriceForMissingProductReturnsNull() {
        when(mockCursor.hasNext()).thenReturn(false);

        BigDecimal price = priceRepository.getProductPrice(PRODUCT_ID);
        assertThat(price, is(nullValue()));
    }
}