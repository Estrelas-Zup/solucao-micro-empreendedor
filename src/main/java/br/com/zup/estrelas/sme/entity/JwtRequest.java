package br.com.zup.estrelas.sme.entity;

import java.io.Serializable;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JwtRequest implements Serializable {

private static final long serialVersionUID = 5926468583005150707L;

private String email;
private String senha;

public JwtRequest()
{
}

public JwtRequest(String email, String senha) {
	this.setSenha(senha);
	this.setEmail(email);
}

public String getEmail() {
	return email;
}

public String getSenha() {
	return senha;
}

public void setEmail(String email) {
	this.email = email;
}

public void setSenha(String senha) {
	this.senha = senha;
}

public UsernamePasswordAuthenticationToken converter() {
	return new UsernamePasswordAuthenticationToken(email, senha);
}

}