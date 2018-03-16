//package ru.vsu.personalWallet.s2;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
//import ru.vsu.personalWallet.service.UserService;
//
//@Configuration
//@EnableWebMvcSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//       httpSecurity.authorizeRequests().anyRequest().authenticated();
//               //.antMatcher("/**").authorizeRequests();
//       //oauth2Login();
//
////        http.addFilterBefore(new TokenAuthenticationFilter(authenticationManager(), tokenAuthenticationEntryPoint(), header, ignoreFault), BasicAuthenticationFilter.class)
////                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////                .and()
////                .authorizeRequests().anyRequest().authenticated()
////                .and()
////                .httpBasic().disable()
////                .formLogin().disable()
////                .csrf().disable();
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.inMemoryAuthentication()
//                .withUser("user").password("password")
//                .and()
//                .withUser("admin").password("password");
//    }
//}
