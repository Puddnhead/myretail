package com.myretail.domain;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

/**
 * Unit test for {@link Price}
 *
 * Created by MVW on 7/4/2018.
 */
public class PriceTest {

    @Test
    public void testEqualsHashcode() {
        EqualsVerifier.forClass(Price.class);
    }
}