package org.khanal.SbHibernateShoppingcart.config;

import org.khanal.SbHibernateShoppingcart.services.security.jwt.JwtAuthenticationEntryPoint;
import org.khanal.SbHibernateShoppingcart.services.security.jwt.JwtAuthorizationTokenFilter;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SpringSecConfig extends WebSecurityConfigurerAdapter implements InitializingBean {
    private AuthenticationManagerBuilder authenticationManagerBuilder;
    private UserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;
    private JwtAuthorizationTokenFilter authorizationTokenFilter;
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.route.authentication.path}")
    private String authenticationPath;

    @Autowired
    public SpringSecConfig(AuthenticationManagerBuilder authenticationManagerBuilder, UserDetailsService
            userDetailsService, PasswordEncoder passwordEncoder, JwtAuthorizationTokenFilter
                                   authorizationTokenFilter, JwtAuthenticationEntryPoint unauthorizedHandler) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.authorizationTokenFilter = authorizationTokenFilter;
        this.unauthorizedHandler = unauthorizedHandler;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        } catch (Exception e) {
            throw new BeanInitializationException("Security configuration failed...", e);
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/h2-console/**/**", "/auth/**").permitAll().anyRequest()
                .authenticated();
        http.addFilterBefore(authorizationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //disable page caching
        http.headers().frameOptions().sameOrigin().cacheControl();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.POST, authenticationPath).and().ignoring().antMatchers(HttpMethod.GET,
                "/", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js");
    }


}
