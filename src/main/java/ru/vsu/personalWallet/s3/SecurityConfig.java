package ru.vsu.personalWallet.s3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.stereotype.Service;

@Service
@EnableWebSecurity
public class  SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Override
//    public void configure(final AuthenticationManagerBuilder auth) throws Exception
//    {
//        auth.userDetailsService(this.participantService);
//    }
//
//    @Override
//    protected void configure(final HttpSecurity http) throws Exception
//    {
//
//        //Implementing Token based authentication in this filter
//        final TokenAuthenticationFilter tokenFilter = new TokenAuthenticationFilter();
//        http.addFilterBefore(tokenFilter, BasicAuthenticationFilter.class);
//
//        //Creating token when basic authentication is successful and the same token can be used to authenticate for further requests
//        final CustomBasicAuthenticationFilter customBasicAuthFilter = new CustomBasicAuthenticationFilter(this.authenticationManager() );
//        http.addFilter(customBasicAuthFilter);
//
//    }


    @Bean
    public RequestHeaderAuthenticationFilter requestHeaderAuthenticationFilter() throws Exception {
        RequestHeaderAuthenticationFilter requestHeaderAuthenticationFilter = new RequestHeaderAuthenticationFilter();
        requestHeaderAuthenticationFilter.setPrincipalRequestHeader("X-AUTH-TOKEN");
        requestHeaderAuthenticationFilter.setAuthenticationManager(authenticationManager());
        requestHeaderAuthenticationFilter.setExceptionIfHeaderMissing(false);

        return requestHeaderAuthenticationFilter;
    }


    @Bean
    public PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider() {
        PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider = new PreAuthenticatedAuthenticationProvider();
        preAuthenticatedAuthenticationProvider.setPreAuthenticatedUserDetailsService(new UserDetailsByNameServiceWrapper<>(userDetailsService()));

        return preAuthenticatedAuthenticationProvider;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(preAuthenticatedAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilter(requestHeaderAuthenticationFilter())
                .antMatcher("/**").authorizeRequests().anyRequest().authenticated();
    }

}