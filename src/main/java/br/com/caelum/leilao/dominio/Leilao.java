package br.com.caelum.leilao.dominio;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
public class Leilao {

	private String descricao;
	private List<Lance> lances;
	
	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}
	
	public void propoe(Lance lance) {
		if(CollectionUtils.isEmpty(lances) || (isUltimoLanceValido(lance) && isNumeroMaximoLancesPorPessoa(lance))){
			lances.add(lance);
		}

	}

	private boolean isNumeroMaximoLancesPorPessoa(Lance lance) {
		long numeroLancesPorPessoa = lances.stream()
				.filter(lanceExistente -> lanceExistente.getUsuario().equals(lance.getUsuario()))
				.count();
		return numeroLancesPorPessoa <= 4;
	}

	private boolean isUltimoLanceValido(Lance lance) {
		return !lances.get(lances.size() - 1).getUsuario().equals(lance.getUsuario());
	}

}
