package ma.projet.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;


@Configuration
@EnableWebSecurity
public class ProjectSecurityConfig  {

    /**
     * From Spring Security 5.7, the WebSecurityConfigurerAdapter is deprecated to encourage users
     * to move towards a component-based security configuration. It is recommended to create a bean
     * of type SecurityFilterChain for security related configurations.
     *
     * @param http
     * @return SecurityFilterChain
     * @throws Exception
     * 
     */
	@Bean
	public SpringTemplateEngine springTemplateEngine()
	{
	    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	    templateEngine.addTemplateResolver(htmlTemplateResolver());
	    return templateEngine;
	}

	@Bean
	public SpringResourceTemplateResolver htmlTemplateResolver()
	{
	    SpringResourceTemplateResolver emailTemplateResolver = new SpringResourceTemplateResolver();
	    emailTemplateResolver.setPrefix("classpath:/templates/");
	    emailTemplateResolver.setSuffix(".html");

	    return emailTemplateResolver;
	}

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        /**
         * Custom configurations as per our requirement
         */
    
                http.authorizeHttpRequests()
                .requestMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/myAccount",true)
                .permitAll();
          
                
                return http.build();
        
    }
    
   
    /*@Bean
    public InMemoryUserDetailsManager userDetailsService() {

        *//**
     * Approach 1 where we use withDefaultPasswordEncoder() method
     * while creating the user details
     *//*
     *//*UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("12345")
                .authorities("admin")
                .build();
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("12345")
                .authorities("read")
                .build();
        return new InMemoryUserDetailsManager(admin, user);*//*

     *//**
     * Approach 2 where we don't define password encoder
     * while creating the user details. Instead a separate
     * PasswordEncoder bean will be created.
     *//*
        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
        UserDetails admin = User.withUsername("admin").password("12345").authorities("admin").build();
        UserDetails user = User.withUsername("user").password("12345").authorities("read").build();
        userDetailsService.createUser(admin);
        userDetailsService.createUser(user);
        return userDetailsService;
    }*/

    /*@Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
	  return new JdbcUserDetailsManager(dataSource);
    }*/

    /**
     * NoOpPasswordEncoder is not recommended for production usage.
     * Use only for non-prod.
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
}
