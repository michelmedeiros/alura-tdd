package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

import java.util.Comparator;

/**
 * Created by miche on 28/02/2017.
 */
public class Avaliador {

    public Double maiorLance(Leilao leilao) {
        Double maiorLance  = leilao.getLances().stream()
                .mapToDouble(Lance::getValor).max().orElse(Double.NEGATIVE_INFINITY);
        return maiorLance;
    }

    public Double menorLance(Leilao leilao) {
        Double menorLance  = leilao.getLances().stream()
                .mapToDouble(Lance::getValor).min().orElse(Double.POSITIVE_INFINITY);
        return menorLance;
    }

    public Double lanceMedio(Leilao leilao) {
        Double mediaLance  = leilao.getLances().stream()
                .mapToDouble(Lance::getValor).average().orElse(Double.POSITIVE_INFINITY);
        return mediaLance;
    }
}
