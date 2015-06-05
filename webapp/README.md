webapp
===========

A J2EE project with Maven build, that contains seven sub-maven module:

* common (a base classes set helps other module)
* domain (model for DAO or web layer)
    * webapp-domain
* dao (DAO layer, focus on DB access) 
    * webapp-dao
* manager (communicate with the third application, like communicate with other system by web service. also transaction management here)
    * webapp-manager
* service (business layer)
    * webapp-service
* controller (http request controller here, focus on validate and convert domain into business layer)
    * webapp-controller
* web (view layer)
    * webapp-web

web/webapp-web/src/main/webapp/WEB-INF/web.xml
------------

+ load the Spring configuration files by listener, `<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>`, **`classpath*:spring/spring-*.xml`** 
+ load the SpringMVC configuration files by servlet,`<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>`, **`classpath*:springmvc/spring-*.xml`**
+ load the log4j configuration file by listener, `<listener-class>org.springframework.web.util.Log4jConfigListener</listener-class>`, **`classpath:log4j.xml`**
+ Druid monitor by DruidStatView servlet, `<servlet-class>com.alibaba.druid.support.http.StatViewServlet</servlet-class>`
+ project encoding by filter,  `<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>`