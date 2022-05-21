package br.com.alura.forum.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
@Profile("dev")
public class DevSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
//	@Autowired
//	private AutenticacaoService autenticacaoService;
//	
//	@Autowired
//	private TokenService tokenService;
//	
//	@Autowired
//	private UsuarioRepository usuarioRepository;
//
//	// Configuração de autenticação
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
//	}

	// Configurações de autorização
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/**").permitAll()
//			.antMatchers(HttpMethod.GET, "/topicos/*").permitAll()
//			.antMatchers(HttpMethod.POST, "/auth").permitAll()
//			.antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
//			.antMatchers(HttpMethod.DELETE, "/topicos/*").hasRole("MODERADOR")
//			.anyRequest().authenticated()
			.and().csrf().disable();
//			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//			.and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
	}

	// Configurações de recursos estáticos (js, css, imagens, etc . . .)
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//	    web.ignoring()
//	        .antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
//	}
//	
//	
//	@Override
//	@Bean
//	protected AuthenticationManager authenticationManager() throws Exception {
//		return super.authenticationManager();
//	}

}
