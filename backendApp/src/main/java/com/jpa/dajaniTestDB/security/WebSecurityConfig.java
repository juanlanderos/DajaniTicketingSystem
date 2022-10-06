

package com.jpa.dajaniTestDB.security;

import com.nimbusds.jose.shaded.json.JSONArray;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


/* BAELDUNG EXAMPLE

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .and()
                .authorizeRequests(authz -> authz.mvcMatchers("/")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .oauth2Login()
                .and()
                .logout()
                .logoutSuccessUrl("/");
    }*//*


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(a -> a
                        .anyRequest().authenticated()
                )
                .oauth2Login(l -> {
                   l.userInfoEndpoint().userAuthoritiesMapper(userAuthoritiesMapper());
                });
    }

    //extracts the groups that cognito users belong to
    @Bean
    public GrantedAuthoritiesMapper userAuthoritiesMapper() {
        return (authorities) -> {
            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
            Optional<OidcUserAuthority> awsAuthority;

            awsAuthority = (Optional<OidcUserAuthority>) authorities.stream()
                    .filter(grantedAuthority -> "ROLE_USER".equals(grantedAuthority.getAuthority()))
                    .findFirst();

            if (awsAuthority.isPresent()) {
                mappedAuthorities = ((JSONArray) awsAuthority.get().getAttributes().get("cognito:groups")).stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                        .collect(Collectors.toSet());
            }
            return mappedAuthorities;
        };
    }



*/
/*    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                //.and()
                .authorizeRequests(authz -> authz.mvcMatchers("/")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .oauth2Login()
                .and()
                .logout()
                .logoutSuccessUrl("/");
    }*//*


*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .anonymous().disable()
                .authorizeRequests()
                .antMatchers("/oauth/token").permitAll().and()
                .httpBasic();
    }

}


