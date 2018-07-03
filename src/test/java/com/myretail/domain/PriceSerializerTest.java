package com.myretail.domain;

import com.fasterxml.jackson.core.JsonGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;

/**
 * Unit test for {@link PriceSerializer}
 *
 * Created by MVW on 7/3/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class PriceSerializerTest {

    private PriceSerializer priceSerializer = new PriceSerializer();

    @Mock
    private JsonGenerator mockJsonGenerator;

    @Captor
    private ArgumentCaptor<String> priceCaptor;

    @Before
    public void setup() throws Exception {
        doNothing().when(mockJsonGenerator).writeString(priceCaptor.capture());
    }

    @Test
    public void testSerializationRoundingUp() throws Exception {
        priceSerializer.serialize(new BigDecimal(10.14555), mockJsonGenerator, null);
        assertThat(priceCaptor.getValue(), is("10.15"));
    }


    @Test
    public void testSerializationRoundingDown() throws  Exception {
        priceSerializer.serialize(new BigDecimal(0.33333), mockJsonGenerator, null);
        assertThat(priceCaptor.getValue(), is("0.33"));
    }


    @Test
    public void testSerializationDollarsOnly() throws Exception {
        priceSerializer.serialize(new BigDecimal(1000), mockJsonGenerator, null);
        assertThat(priceCaptor.getValue(), is("1000.00"));
    }
}