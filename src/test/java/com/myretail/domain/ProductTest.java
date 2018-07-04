package com.myretail.domain;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

/**
 * Unit test for {@link Product}
 *
 * Created by MVW on 7/3/2018.
 */
public class ProductTest {

    @Test
    public void testEqualsHashcode() {
        EqualsVerifier.forClass(Product.class);
    }
}