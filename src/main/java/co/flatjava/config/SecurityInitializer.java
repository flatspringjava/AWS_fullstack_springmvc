package co.flatjava.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@EnableWebSecurity
@Configuration
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer{
		
}
