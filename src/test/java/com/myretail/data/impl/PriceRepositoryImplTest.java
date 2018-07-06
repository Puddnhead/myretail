package com.myretail.data.impl;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import com.myretail.domain.Currency;
import com.myretail.domain.Price;
import com.myretail.domain.Outcome;
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
    private Document productDocument;

    @Mock
    private UpdateResult updateResult;

    private static final String DB_NAME = "db";
    private static final String COLLECTION_NAME = "collection";
    private static final String PRODUCT_ID = "123";
    private static final Price PRICE = new Price(new BigDecimal(1000), Currency.USD);
    private static final Document PRICE_DOCUMENT =
            new Document(PriceRepositoryImpl.VALUE_PROPERTY, PRICE.getValue().doubleValue())
                    .append(PriceRepositoryImpl.CURRENCY_PROPERTY, PRICE.getCurrency().name());

    @Before
    public void setup() {
        ReflectionTestUtils.setField(priceRepository, "dbName", DB_NAME);
        ReflectionTestUtils.setField(priceRepository, "collectionName", COLLECTION_NAME);

        when(mongoClient.getDatabase(anyString())).thenReturn(mockDatabase);
        when(mockDatabase.getCollection(anyString())).thenReturn(mockCollection);
        when(mockCollection.find(any(Bson.class))).thenReturn(mockFindIterable);
        when(mockFindIterable.iterator()).thenReturn(mockCursor);

        when(mockCollection.updateOne(any(Bson.class), any(Bson.class))).thenReturn(updateResult);
    }

    @Test
    public void testGetExistingPrice() {
        when(mockCursor.hasNext()).thenReturn(true);
        when(mockCursor.next()).thenReturn(productDocument);
        when(productDocument.get(PriceRepositoryImpl.PRICE_PROPERTY, Document.class)).thenReturn(PRICE_DOCUMENT);

        Price price = priceRepository.getProductPrice(PRODUCT_ID);
        assertThat(price, is(PRICE));
    }

    @Test
    public void testGetPriceForMissingProductReturnsNull() {
        when(mockCursor.hasNext()).thenReturn(false);

        Price price = priceRepository.getProductPrice(PRODUCT_ID);
        assertThat(price, is(nullValue()));
    }

    @Test
    public void testUpdatePrice() {
        when(updateResult.getMatchedCount()).thenReturn(1L);
        Outcome outcome = priceRepository.updatePrice(PRODUCT_ID, PRICE);
        assertThat(outcome, is(Outcome.SUCCESS));
    }

    @Test
    public void testUpdatePriceNoMatchingProduct() {
        when(updateResult.getMatchedCount()).thenReturn(0L);
        Outcome outcome = priceRepository.updatePrice(PRODUCT_ID, PRICE);
        assertThat(outcome, is(Outcome.FAILURE));
    }
}