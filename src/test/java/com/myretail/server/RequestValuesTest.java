package com.myretail.server;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Unit test for {@link RequestValues}
 *
 * Created by MVW on 7/7/2018.
 */
public class RequestValuesTest {

    @Test
    public void testRequestValues() throws Exception {
        Thread worker1 = new Thread(() -> {
            RequestValues.setMasterOfTheUniverse(true);
            assertThat(RequestValues.isMasterOfTheUniverse(), is(true));
        });

        Thread worker2 = new Thread(() ->
            assertThat(RequestValues.isMasterOfTheUniverse(), is(false))
        );

        worker1.start();
        worker2.start();
        worker1.join();
        worker2.join();
    }

}