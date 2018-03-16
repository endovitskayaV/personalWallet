package ru.vsu.personalWallet.s5;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableTransactionManagement
public class SConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public TokenAuthenticationService tokenAuthenticationService() {
        return new TokenAuthenticationService();
    }

    @Bean
    public TokenHandler tokenHandler() {
        return new TokenHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //Ресурсы доступные анонимным пользователям
                .antMatchers("/", "/login").permitAll()
                //Все остальные доступны только после аутентификации
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService()), UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable();
    }
}
