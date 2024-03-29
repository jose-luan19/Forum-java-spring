package br.com.alura.forum.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.alura.forum.modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${forum.jwt.expiration}")
	private String expiration;
	
	@Value("${forum.jwt.secret}")
	private String senha;

	private static Long idUsuario;

	public String gerarToken(Authentication authenticate) {
		Usuario logado = (Usuario) authenticate.getPrincipal();
		Date hoje = new Date();
		Date dataExpericacao = new Date(hoje.getTime() + Long.parseLong(expiration));
		return Jwts.builder()
				.setIssuer("API do Forum Alura")
				.setSubject(logado.getId().toString())
				.setIssuedAt(hoje)
				.setExpiration(dataExpericacao)
				.signWith(SignatureAlgorithm.HS256, senha)
				.compact();
	}

	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.senha)
			.parseClaimsJws(token);
			return true;
			
		} catch (Exception e) {
			return false;
		}
		
		
	}

	public Long getIdUsuario(String token) {
		Claims body = Jwts.parser().setSigningKey(this.senha)
		.parseClaimsJws(token).getBody();
		idUsuario = Long.parseLong(body.getSubject());
		return idUsuario;
	}

	public static Long getIdUsuario(){
		return idUsuario;
	}

}
