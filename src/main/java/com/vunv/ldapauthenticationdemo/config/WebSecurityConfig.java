//package com.vunv.ldapauthenticationdemo.config;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.context.annotation.Bean;
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// * @author vu
// * @created 14/10/2024
// */
//
//@Configuration
//public class WebSecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((authorize) -> authorize
//                        .anyRequest().fullyAuthenticated()
//                )
//                .formLogin(Customizer.withDefaults());
//
//        return http.build();
//    }
//
//    @Autowired
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .ldapAuthentication()
////                .userDetailsContextMapper(inetOrgPersonContextMapper())
//                .userSearchFilter("(uid={0})")
//                .userSearchBase("dc=example,dc=com")
//                .groupSearchBase("ou=mathematicians,dc=example,dc=com")
//                .groupSearchFilter("cn={0}")
//                .contextSource()
//                .url("ldap://ldap.forumsys.com")
//                .port(389)
//                .managerDn("cn=read-only-admin,dc=example,dc=com")
//                .managerPassword("password");
//    }
//
//}