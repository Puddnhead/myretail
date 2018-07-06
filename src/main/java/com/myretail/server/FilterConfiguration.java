package com.myretail.server;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Some custom servlet filter configuration
 *
 * Created by MVW on 7/4/2018.
 */
@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean masterOfTheUniverseFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MasterOfTheUniverseFilter());
        registration.addUrlPatterns("/*");
        registration.setName("masterOfTheUniverseFilter");
        registration.setOrder(1);
        return registration;
    }

}
