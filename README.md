# Getting Started
🎉 Authentication with Ldap Example

<img src="{https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white}" />

Here’s a Spring Security Active Directory example to show how I was finally able to get Spring Security to work with the Active Directory LDAP server.
using just 1 don't
* using LdapAuthenticationProvider
* using AuthenticationManagerBuilder

## Guides
🎉TestAccount🎉
* username: riemann
* password: password

🎉How it works:
1. Bind to LDAP using the manager user id and password specified in the ContextSource.
2. Perform a lookup on the user id (entered from the login screen) using the userSearch bean.
3. Get the fully distinguished name of the user that matches.
4. Use that user and the password (entered) to bind to LDAP again.
5. Search for all of the groups the user is in based on the groupSearchFilter configuration.


## Reference Documentation

🎉For further reference, please consider the following sections:
* [Authenticating a User with LDAP Spring Boot](https://spring.io/guides/gs/authenticating-ldap)
* [LDAP Authentication](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/ldap.html)
* [Online Ldap Server Test](https://www.forumsys.com/2022/05/10/online-ldap-test-server/)