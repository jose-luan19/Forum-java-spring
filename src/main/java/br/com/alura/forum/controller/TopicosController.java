package br.com.alura.forum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.controller.dto.TopicoDTO;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;

@RestController
public class TopicosController {

	@RequestMapping("/topicos")

	public List<TopicoDTO> lista() {
		Topico topico = new Topico("Duvida", "Dúvida com Spring", new Curso("Spring", "Programação"));
		
		return TopicoDTO.converte(Arrays.asList(topico,topico));
	}
	
}
