//package ru.vsu.personalWallet.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
//import ru.vsu.personalWallet.service.UserService;
//
//@Configuration
//@EnableWebMvcSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private UserService userService;
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//       httpSecurity.authorizeRequests().anyRequest().authenticated();
//               //.antMatcher("/**").authorizeRequests();
//       //oauth2Login();
//    }
//}
