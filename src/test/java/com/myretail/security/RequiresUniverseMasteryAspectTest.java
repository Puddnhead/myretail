package com.myretail.security;

import com.myretail.server.RequestValues;
import com.myretail.service.exception.AuthorizationException;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * Unit test for {@link RequiresUniverseMasteryAspect}
 *
 * Created by MVW on 7/5/2018.
 */
public class RequiresUniverseMasteryAspectTest {

    private RequiresUniverseMasteryAspect requiresUniverseMasteryAspect = new RequiresUniverseMasteryAspect();

    @Test
    public void testMasterOfTheUniverseDoesNothing() {
        RequestValues requestValues = new RequestValues();
        ReflectionTestUtils.invokeMethod(requestValues, "setIsMasterOfTheUniverse", true);
        requiresUniverseMasteryAspect.requireUniverseMastery();
    }

    @Test(expected = AuthorizationException.class)
    public void testEverybodyElseThrowsAuthorizationException() {
        RequestValues requestValues = new RequestValues();
        ReflectionTestUtils.invokeMethod(requestValues, "setIsMasterOfTheUniverse", false);
        requiresUniverseMasteryAspect.requireUniverseMastery();
    }

}