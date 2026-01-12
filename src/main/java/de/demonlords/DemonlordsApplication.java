package de.demonlords;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;

import de.demonlords.auth.AuthFilter;

@SpringBootApplication
public class DemonlordsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemonlordsApplication.class, args);
	}
	
	@Bean
    public FilterRegistrationBean<AuthFilter> authFilterRegistration() {
        FilterRegistrationBean<AuthFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new AuthFilter());
        registration.addUrlPatterns("*.xhtml");
        registration.setName("AuthFilter");
        registration.setOrder(Ordered.LOWEST_PRECEDENCE - 1); // kurz vor FacesServlet
        return registration;
    }
	
	

}
