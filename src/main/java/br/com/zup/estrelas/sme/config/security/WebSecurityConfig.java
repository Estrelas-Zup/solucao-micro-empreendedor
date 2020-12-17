package br.com.zup.estrelas.sme.config.security;

import java.util.Arrays;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import br.com.zup.estrelas.sme.service.impl.AutenticacaoServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${endpoints.cors.allow-credentials}")
	private String allowCredentials;

	@Value("${endpoints.cors.allowed-origins}")
	private String allowedOrigins;

	@Value("${endpoints.cors.allowed-methods}")
	private String allowedMethods;

	@Value("${endpoints.cors.allowed-headers}")
	private String allowedHeaders;

	@Value("${endpoints.cors.exposed-headers}")
	private String exposedHeaders;

	@Value("${endpoints.cors.max-age}")
	private String maxAge;
	
    @Autowired
    private AutenticacaoViaTokenFilter jwtRequestFilter;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private AutenticacaoServiceImpl autenticacaoService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors();
    	httpSecurity.csrf().disable().authorizeRequests()
                .antMatchers("/authenticate", "/v2/api-docs", "/configuration/ui",
                        "/swagger-resources/**", "/configuration/**", "/swagger-ui.html",
                        "/webjars/**")
                .permitAll().anyRequest().authenticated().and().exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtRequestFilter, AbstractPreAuthenticatedProcessingFilter.class);

    }
    
    @Bean
	public Filter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(Boolean.valueOf(allowCredentials));
		config.setMaxAge(Long.valueOf(maxAge));
		Arrays.stream(allowedOrigins.split(",")).forEach(config::addAllowedOrigin);
		Arrays.stream(allowedHeaders.split(",")).forEach(config::addAllowedHeader);
		Arrays.stream(allowedMethods.split(",")).forEach(config::addAllowedMethod);
		Arrays.stream(exposedHeaders.split(",")).forEach(config::addExposedHeader);
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}
