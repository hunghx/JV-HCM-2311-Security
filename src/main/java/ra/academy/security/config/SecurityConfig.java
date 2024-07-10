package ra.academy.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ra.academy.security.jwt.JwtAuthenticationFilter;

import java.util.Arrays;

// cấu hinh security
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    // định nghĩa bean
    // authentication manager : authenticate()
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new  BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("http://localhost:5173");
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","PATCH","DELETE"));
        configuration.setAllowCredentials(false);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean // phân quyền
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(cors ->cors.configurationSource(corsConfigurationSource())) // chia sẻ tài nguyên
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // phi trạng thai
                // xử lí lỗi :
                .exceptionHandling(handler ->
                        handler.accessDeniedHandler(new AccessDeniedCustomHandler()) // ko có quyen
                                .authenticationEntryPoint(new AuthentiactionEntryPointCustom())
                        )
                .authorizeHttpRequests( // cấu hình phân quyền theo đường dẫn
                        auth -> auth.requestMatchers("/api.com/v1/auth/**").permitAll() // đăng nhập đăng kí
                                .requestMatchers("/api.com/v1/admin/**").hasAuthority("ROLE_ADMIN") // chỉ co quyê admin
                                .requestMatchers("/api.com/v1/user/**").hasAuthority("ROLE_USER")
                                .requestMatchers("/api.com/v1/mod/**").hasAuthority("ROLE_MODERATOR")
                                .requestMatchers("/api.com/v1/user-mod/**").hasAnyAuthority("ROLE_USER", "ROLE_MODERATOR")
                                .requestMatchers("/api.com/v1/public/**").permitAll()
                                .anyRequest().authenticated() // còn lại thì phải được xác thực
                );
        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
