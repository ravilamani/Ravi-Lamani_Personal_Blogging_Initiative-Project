package com.blog.Config;

import com.blog.JWT.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    private JwtRequestFilter jwtRequestFilter;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()

                // which URL who can access
                .antMatchers(HttpMethod.GET,"/api/posts/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/auth/**").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement()
                .and().cors();

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }

/*    @Bean
    public UserDetailsService userDetailsService() {
        // In-memory user details service with a user
        // Note: Password should be encoded using a proper encoder in a real application
        UserDetails user = User.withUsername("user").password(passwordEncoder().encode("raccoon")).roles("USER").build();
        UserDetails admin = User.withUsername("admin").password(passwordEncoder().encode("lamani")).roles("ADMIN").build();

        return new InMemoryUserDetailsManager(user,admin);
        return new InMemoryUserDetailsManager(user,admin);
        return new InMemoryUserDetailsManager(user,admin);
        return new InMemoryUserDetailsManager(user,admin);
        return new InMemoryUserDetailsManager(user,admin);
    }*/


}
