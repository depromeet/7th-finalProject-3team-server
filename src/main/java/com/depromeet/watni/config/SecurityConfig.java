package com.depromeet.watni.config;

import com.depromeet.watni.security.filter.AuthFilter;
import com.depromeet.watni.security.filter.AuthRequestMatcher;
import com.depromeet.watni.security.filter.SignInFilter;
import com.depromeet.watni.security.handler.AuthFailureHandler;
import com.depromeet.watni.security.handler.AuthSuccessHandler;
import com.depromeet.watni.security.handler.SignInFailureHandler;
import com.depromeet.watni.security.handler.SignInSuccessHandler;
import com.depromeet.watni.security.provider.AuthProvider;
import com.depromeet.watni.security.provider.SignInProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private AuthenticationProvider authenticationProvider;
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    private AuthenticationFailureHandler authenticationFailureHandler;
    private AuthenticationProvider signInProvider;
    private AuthenticationSuccessHandler signInSuccessHandler;
    private AuthenticationFailureHandler signInFailureHandler;

    public SecurityConfig(AuthProvider authenticationProvider,
                          AuthSuccessHandler authenticationSuccessHandler,
                          AuthFailureHandler authenticationFailureHandler,
                          SignInProvider signInProvider,
                          SignInSuccessHandler signInSuccessHandler,
                          SignInFailureHandler signInFailureHandler) {
        this.authenticationProvider = authenticationProvider;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.signInProvider = signInProvider;
        this.signInSuccessHandler = signInSuccessHandler;
        this.signInFailureHandler = signInFailureHandler;
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    private AbstractAuthenticationProcessingFilter generateApiFilter() throws Exception {
        // TODO GET /oauth/token
        AuthRequestMatcher authRequestMatcher = new AuthRequestMatcher(Arrays.asList("/api/member"), "/api/**");
        AbstractAuthenticationProcessingFilter apiFilter = new AuthFilter(authRequestMatcher, this.authenticationSuccessHandler, this.authenticationFailureHandler);
        apiFilter.setAuthenticationManager(super.authenticationManagerBean());
        return apiFilter;
    }

    private AbstractAuthenticationProcessingFilter generateSignInFilter() throws Exception {
        AbstractAuthenticationProcessingFilter signInFilter = new SignInFilter("/sign-in", this.signInSuccessHandler, this.signInFailureHandler);
        signInFilter.setAuthenticationManager(super.authenticationManagerBean());
        return signInFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(this.signInProvider)
                .authenticationProvider(this.authenticationProvider);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .mvcMatchers("/docs/**", "/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/h2-console/**",
                        "/docs/**"
                ).permitAll();
        http.csrf().ignoringAntMatchers("/h2-console/**");

        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.headers().frameOptions().disable();
        http
                .addFilterBefore(this.generateSignInFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(this.generateApiFilter(), UsernamePasswordAuthenticationFilter.class);

        // TODO csrf
        http
                .csrf()
                .disable();
    }
}
