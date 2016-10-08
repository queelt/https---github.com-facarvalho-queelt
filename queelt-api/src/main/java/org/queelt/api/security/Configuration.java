package org.queelt.api.security;

import org.queelt.security.config.WebSecurityConfiguration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@org.springframework.context.annotation.Configuration
@EnableWebSecurity
@Order(value=99)
public class Configuration extends WebSecurityConfiguration {

	@Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api-docs/**");
        web.ignoring().antMatchers(HttpMethod.POST ,"/api/user");
    }
}
