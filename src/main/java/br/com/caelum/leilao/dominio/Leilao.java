package br.com.caelum.leilao.dominio;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Slf4j
public class Leilao {

	private String descricao;
	private List<Lance> lances;
	
	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<>();
	}
	
	public void propoe(Lance lance) throws Exception {
        validarLance(lance);
        lances.add(lance);
	}

	private void validarLance(Lance lance) throws Exception {

        if(lance.getValor() <= 0) {
            throw new Exception("Não pode ser realizado lance zerado ou negativo.");
        }

	    if(CollectionUtils.isNotEmpty(lances)) {
            if(isUltimoLanceInvalido(lance)) {
                throw new Exception("Não pode ser realizado dois lances consecutivos para o mesmo usuário.");
            }

            if(isLancesExcedidoPorPessoa(lance)) {
                throw new Exception("Não pode ser realizado mais de 5 lances por usuário.");
            }
        }


	}

	private boolean isLancesExcedidoPorPessoa(Lance lance) {
		long numeroLancesPorPessoa = getNumeroLancesPorPessoa(lance.getUsuario());
		return numeroLancesPorPessoa >= 5;
	}

	private long getNumeroLancesPorPessoa(Usuario usuario) {
		return lances.stream()
                    .filter(lanceExistente -> lanceExistente.getUsuario().equals(usuario))
                    .count();
	}

	private boolean isUltimoLanceInvalido(Lance lance) {
		return lances.get(lances.size() - 1).getUsuario().equals(lance.getUsuario());
	}

	public void dobrarLance(Usuario usuario) throws Exception {
		if(CollectionUtils.isNotEmpty(lances)) {
			Lance ultimoLance = lances.stream()
					.filter(lance -> lance.getUsuario().equals(usuario))
					.reduce((first, second) -> second).get();
			long numeroLancesPorPessoa = getNumeroLancesPorPessoa(usuario);
			if(!isLancesExcedidoPorPessoa(ultimoLance) &&
                    !isUltimoLanceInvalido(ultimoLance) && numeroLancesPorPessoa > 1) {
				propoe(new Lance(usuario, ultimoLance.getValor() * 2));
			}
		}
	}
}
