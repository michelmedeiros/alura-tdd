package br.com.caelum.leilao.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lance {

	private Usuario usuario;
	private double valor;
}
