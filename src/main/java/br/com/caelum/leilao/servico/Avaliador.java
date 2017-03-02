package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by miche on 28/02/2017.
 */
public class Avaliador {

    public Double maiorLance(Leilao leilao) throws Exception {
        validarLeilao(leilao);
        List<Lance> lances = Optional.ofNullable(leilao.getLances()).get();
        return lances.stream()
                .mapToDouble(Lance::getValor).max().orElse(Double.NEGATIVE_INFINITY);
    }

    private void validarLeilao(Leilao leilao) throws Exception {
        if(CollectionUtils.isEmpty(leilao.getLances())) {
            throw new Exception("Um leilão deve possuir pelo menos um lance");
        }
    }

    public Double menorLance(Leilao leilao) {
        List<Lance> lances = Optional.ofNullable(leilao.getLances()).get();
        return lances.stream()
                .mapToDouble(Lance::getValor).min().orElse(Double.POSITIVE_INFINITY);
    }

    public Double lanceMedio(Leilao leilao) {
        List<Lance> lances = Optional.ofNullable(leilao.getLances()).get();
        return lances.stream()
                .mapToDouble(Lance::getValor).average().orElse(Double.POSITIVE_INFINITY);
    }

    public List<Lance> maioresLances(long quantidade, Leilao leilao) {
        List<Lance> lances = Optional.ofNullable(leilao.getLances()).get();
        return lances.stream()
                .sorted(Comparator.comparing(Lance::getValor).reversed())
                .limit(quantidade)
                .collect(Collectors.toList());
    }
}
