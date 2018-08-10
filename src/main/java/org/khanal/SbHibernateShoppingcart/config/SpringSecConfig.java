package org.khanal.SbHibernateShoppingcart.config;

import org.khanal.SbHibernateShoppingcart.services.dao.UserDetailsService;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class SpringSecConfig extends WebSecurityConfigurerAdapter implements InitializingBean {
    private AuthenticationManagerBuilder authenticationManagerBuilder;
    private UserDetailsService userDetailsService;

    @Autowired
    public SpringSecConfig(AuthenticationManagerBuilder authenticationManagerBuilder, UserDetailsService
            userDetailsService) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userDetailsService = userDetailsService;
    }

    @Bean("passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        } catch (Exception e) {
            throw new BeanInitializationException("Security configuration failed...", e);
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/admin/orderList", "/admin/accountInfo").access("hasAnyRole" +
                "('ROLE_EMPLOYEE', 'ROLE_MANAGER')");
        http.authorizeRequests().antMatchers("/admin/product").access("hasRole('ROLE_MANAGER')");
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
        http.authorizeRequests().and().formLogin().loginProcessingUrl("/_spring_security_check").loginPage
                ("/admin/login").defaultSuccessUrl("/admin/accountinfo").failureUrl("/admin/login?error=true")
                .usernameParameter("userName").passwordParameter("password").and().logout().logoutUrl
                ("/admin/logout").logoutSuccessUrl("/");
    }




































}
