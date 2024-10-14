package com.vunv.ldapauthenticationdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.authentication.LdapAuthenticator;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.security.ldap.search.LdapUserSearch;
import org.springframework.security.ldap.userdetails.DefaultLdapAuthoritiesPopulator;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author vu
 * @created 14/10/2024
 */
@Configuration
@EnableWebSecurity
public class LdapSecurityConfig {

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize)-> authorize
                        .anyRequest()
                        .fullyAuthenticated())
                .formLogin(Customizer.withDefaults());
        http.authenticationProvider(ldapAuthenticationProvider());
        return http.build();
    }

    /**
     * Bind to LDAP using the manager user id and password specified in the Context Source
     * @return
     */
    @Bean
    public LdapContextSource contextSource() {
        LdapContextSource ldapContextSource = new LdapContextSource();
        ldapContextSource.setUrl("ldap://ldap.forumsys.com:389");
        ldapContextSource.setUserDn("cn=read-only-admin,dc=example,dc=com");
        ldapContextSource.setPassword("password");
        return ldapContextSource;
    }

    /**
     * the LdapAuthenticationProvider  is performing the authentication
     * @return
     */
    @Bean
    public AuthenticationProvider ldapAuthenticationProvider(){
        return new LdapAuthenticationProvider(this.ldapAuthenticator(), this.authoritiesPopulator());
    }

    /**
     * Perform a lookup on the user id (entered from the login screen)
     * Get the fully distinguished name of the user that matches
     * Use that user and the password (entered) to bind to LDAP again
     * Search for all of the groups the user is in based on the groupSearchFilter configuration
     * @return
     */
    @Bean
    public LdapUserSearch ldapUserSearch() {
        return new FilterBasedLdapUserSearch("ou=people,dc=example,dc=com", "(uid={0})", contextSource());
    }

    /**
     * determine what authorities are returned for the user
     * @return
     */
    @Bean
    public LdapAuthoritiesPopulator authoritiesPopulator(){
        return new DefaultLdapAuthoritiesPopulator(this.contextSource(), "ou=mathematicians,dc=example,dc=com");
    }

    @Bean
    public LdapAuthenticator ldapAuthenticator(){
        BindAuthenticator authenticator = new BindAuthenticator(contextSource());
        authenticator.setUserSearch(ldapUserSearch());
        authenticator.setUserDnPatterns(new String[]{"cn=read-only-admin,dc=example,dc=com"});
        return authenticator;
    }

}
