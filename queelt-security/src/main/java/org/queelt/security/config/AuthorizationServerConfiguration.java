package org.queelt.security.config;


import org.queelt.security.entrypoint.RestAuthenticationEntryPoint;
import org.queelt.security.service.MongoTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;;

@Configuration
public class AuthorizationServerConfiguration {

    private static final String RESOURCE_ID = "restservice";

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(final ResourceServerSecurityConfigurer resources) {
            resources.resourceId(RESOURCE_ID);
        }

        @Override
        public void configure(final HttpSecurity http) throws Exception {
            http.authorizeRequests().antMatchers("/").permitAll().anyRequest().authenticated()
                    .and()
                    .logout()
                    .logoutUrl("logout")
                    .invalidateHttpSession(true);
            http
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        }

    }

    @Configuration
    @EnableAuthorizationServer
    protected static class OAuth2ServerConfiguration extends
            AuthorizationServerConfigurerAdapter {
    	
        @Autowired
        private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
        @Autowired
        @Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;
        @Autowired
        private MongoTokenService tokenStore;
        
        @Override
        public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
            oauthServer.authenticationEntryPoint
                    (restAuthenticationEntryPoint)
                    .allowFormAuthenticationForClients();
        }

        @Override
        public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    		endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager);
        }

        @Override
        public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
            
        	//clients
        	clients
                    .inMemory()
                    .withClient("clientapp")
                    .authorizedGrantTypes("password", "refresh_token")
                    .authorities("USER")
                    .scopes("read", "write")
                    .resourceIds(RESOURCE_ID)
                    .secret("clientapp");
                    //.accessTokenValiditySeconds(10);
        	
        	
        }

        /**
         * Token services.
         *
         * @return the default token services
         */
        @Bean
        public DefaultTokenServices tokenServices() {
            DefaultTokenServices tokenServices = new DefaultTokenServices();
            tokenServices.setSupportRefreshToken(true);
            tokenServices.setTokenStore(this.tokenStore);
            return tokenServices;
        }


    }

}
