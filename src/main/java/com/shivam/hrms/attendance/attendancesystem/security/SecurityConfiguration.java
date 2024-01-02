package com.shivam.hrms.attendance.attendancesystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration {

    // add support for JDBC ...
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        // define query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id, pw, active from members where user_id=?");

        // define query to retrieve the authorities/roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id=?");

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers(HttpMethod.GET).hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.POST).hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.POST).hasRole("MANAGER")
                                .requestMatchers(HttpMethod.POST).hasRole("ADMIN")
                                .requestMatchers("/**").hasRole("EMPLOYEE")                /*
                                .requestMatchers(HttpMethod.GET,"/employeesAttendance/listAttendance/**").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.GET,"/employeesAttendance/listAttendance/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST,"/").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.POST,"/employeesAttendance/listAttendance/**").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.POST,"/employeesAttendance/listAttendance/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST,"/employeesAttendance/search/**").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.GET,"/employeesAttendance/search/**").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.POST,"/employeesAttendance/searchByDate/**").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.GET,"/employeesAttendance/searchByDate/**").hasRole("MANAGER")*/
                )
                .formLogin(form ->
                        form
                                .loginPage("/employeesAttendance/showLoginPage")
                                .loginProcessingUrl("/authenticateTheUser")
                                .permitAll()
                )
                .logout(logout -> logout.permitAll()
                )
                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied")
                );
        return http.build();
    }
}