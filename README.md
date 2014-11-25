Spring Security Joomla Provider
======================

Overview
----------------------
This project provides implementation that allows Spring Security to use Joomla's user accounts. The main idea is to provide solution for enviroments where Joomla plays the main role and Spring/Java application needs to be integrated.

How is it done?
----------------------
1. User is entering website.
2. Joomla creates session cookie.
3. User is logging in.
4. Joomla saves information about cookie and username in database.

When user is entering secured controller/resource in Spring application, there is no need to ask about login/password since it is possible to use "verified" cookie as a token to authenticate Spring Security session. From that point information about user will be retrieved (*UserDetails*) and also groups that this user is a member of (*GrantedAuthorities*)...

Implementation uses *hibernate* to retrieve data. 

Usage
----------------------

There are two Maven projects:
* **spring-security-joomla-provider** - core library
* **spring-security-joomla-provider-example** - example application

Example application shows how to use core library and should give some hints related to context configuration.

1. Build & install core library
  
  ```
  cd spring-security-joomla-provider/ 
  mvn clean install
  ```
2. Configure database (config.properties)

  ```
  cd ../spring-security-joomla-provider-example/
  vim ./src/main/resources/config.properties
  ```
3. Run 

  ```
  mvn tomcat:run
  ```
4. Access

  The best idea to test this solution is to use Apache both to run PHP for Joomla and as a proxy for Tomcat (http://tomcat.apache.org/tomcat-7.0-doc/proxy-howto.html) under the same domain.



