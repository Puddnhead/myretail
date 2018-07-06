package com.myretail.server;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Filter that checks for a request header that only the master of the universie would include in their http request
 *
 * Created by MVW on 7/4/2018.
 */
@Component
public class MasterOfTheUniverseFilter implements Filter {

    public static final String HEADER_NAME = "Universes-Mastered";
    public static final String HEADER_VALUE = "This One";

    @Override
    public void init(FilterConfig filterConfig) {
        // do nothing
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String header = httpServletRequest.getHeader(HEADER_NAME);
            if (HEADER_VALUE.equals(header)) {
                // If they say they are then clearly they are
                RequestValues.setIsMasterOfTheUniverse(true);
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            // We need to reset the thread local because this thread may be reused in a later request
            RequestValues.setIsMasterOfTheUniverse(false);
        }
    }

    @Override
    public void destroy() {

    }
}
