package kg.zarlykov.kelsinbek.payrollmanagementsystem.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChainMVC(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/register").permitAll()
                        .requestMatchers("/css/**", "/font-awesome/**", "/images/**", "/js/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/client/**").hasRole("CLIENT")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler(authenticationSuccessHandler())
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            boolean isAdmin = authentication.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
            boolean isEmployee = authentication.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_EMPLOYEE"));
            boolean isDirector = authentication.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_DIRECTOR"));
            boolean isManager = authentication.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_MANAGER"));
            boolean isAccounter = authentication.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ACCOUNTER"));

            if (isAdmin) {
                response.sendRedirect("/admin/home");
            } else if (isDirector) {
                response.sendRedirect("/director/home");
            } else if (isManager) {
                response.sendRedirect("/manager/home");
            } else if (isAccounter) {
                response.sendRedirect("/accounter/home");
            } else if (isEmployee) {
                response.sendRedirect("/employee/home");
            } else {
                response.sendRedirect("/default");
            }
        };
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withUsername("admin")
                .password("{noop}admin123")
                .roles("ADMIN")
                .build();

        UserDetails employee = User.withUsername("employee")
                .password("{noop}emp456")
                .roles("EMPLOYEE")
                .build();

        UserDetails director = User.withUsername("director")
                .password("{noop}dir789")
                .roles("DIRECTOR")
                .build();

        UserDetails manager = User.withUsername("manager")
                .password("{noop}man101")
                .roles("MANAGER")
                .build();

        UserDetails accounter = User.withUsername("accounter")
                .password("{noop}acc202")
                .roles("ACCOUNTER")
                .build();

        InMemoryUserDetailsManager managerInstance = new InMemoryUserDetailsManager(
                admin, employee, director, manager, accounter
        );

        return managerInstance;
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

}


