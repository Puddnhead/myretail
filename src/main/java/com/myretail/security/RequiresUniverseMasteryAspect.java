package com.myretail.security;

import com.myretail.server.RequestValues;
import com.myretail.service.exception.AuthorizationException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Aspect wherein we check for mastery of the universe
 *
 * Created by MVW on 7/4/2018.
 */
@Aspect
@Component
public class RequiresUniverseMasteryAspect {

    @Before("@annotation(RequiresUniverseMastery)")
    void requireUniverseMastery() {
        if(!RequestValues.isMasterOfTheUniverse()) {
            throw new AuthorizationException("You are not the master of the universe!");
        }
    }
}
