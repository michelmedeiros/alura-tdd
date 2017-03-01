package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by miche on 28/02/2017.
 */
public class Avaliador {

    public Double maiorLance(Leilao leilao) {
        List<Lance> lances = Optional.ofNullable(leilao.getLances()).get();
        Double maiorLance  = lances.stream()
                .mapToDouble(Lance::getValor).max().orElse(Double.NEGATIVE_INFINITY);
        return maiorLance;
    }

    public Double menorLance(Leilao leilao) throws Exception{
        List<Lance> lances = Optional.ofNullable(leilao.getLances()).get();
        Double menorLance  = lances.stream()
                .mapToDouble(Lance::getValor).min().orElse(Double.POSITIVE_INFINITY);
        return menorLance;
    }

    public Double lanceMedio(Leilao leilao) throws Exception {
        List<Lance> lances = Optional.ofNullable(leilao.getLances()).get();
        Double mediaLance  = lances.stream()
                .mapToDouble(Lance::getValor).average().orElse(Double.POSITIVE_INFINITY);
        return mediaLance;
    }

    public List<Lance> maioresLances(long quantidade, Leilao leilao) throws Exception{
        List<Lance> lances = Optional.ofNullable(leilao.getLances()).get();
        List<Lance> maioresLances = lances.stream()
                .sorted(Comparator.comparing(Lance::getValor).reversed())
                .limit(quantidade)
                .collect(Collectors.toList());
        return maioresLances;
    }
}
