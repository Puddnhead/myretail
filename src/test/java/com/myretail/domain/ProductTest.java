package com.myretail.domain;

import org.junit.Test;

import static org.junit.Assert.*;
import nl.jqno.equalsverifier.EqualsVerifier;

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