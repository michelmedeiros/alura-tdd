package br.com.caelum.leilao.dominio;

import lombok.*;

import java.util.Comparator;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Usuario {

	private Integer id;
	private String nome;
	
	public Usuario(String nome) {
		this(0, nome);
	}


}
