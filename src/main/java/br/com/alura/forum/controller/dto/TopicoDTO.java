package br.com.alura.forum.controller.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.alura.forum.modelo.Topico;

public class TopicoDTO {

	private Long id;
	private String titulo;
	private String mensagem;
	private String curso;
	private LocalDateTime dataCriacao;
	
	public TopicoDTO(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.curso = topico.getCurso().getNome();
		this.dataCriacao = topico.getDataCriacao();
	}
	

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}


	public static Page<TopicoDTO> converte(Page<Topico> topicos) {
		return topicos.map(TopicoDTO::new);
	}


	public String getCurso() {
		return curso;
	}


	public void setCurso(String curso) {
		this.curso = curso;
	}
}