package br.com.caelum.leilao.servico;

/**
 * Created by Michel Medeiros on 28/02/2017.
 */

import br.com.caelum.leilao.dominio.Lance;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;


public class FiltroLances {

    public List<Lance> filtra(List<Lance> lances) {
        removeLancesMenoresQue500(lances);
        removeLancesEntre700e1000(lances);
        removeLanceEntre3000e5000(lances);

        return lances;
    }

    private void removeLanceEntre3000e5000(List<Lance> lances) {
        if(CollectionUtils.isNotEmpty(lances)) {
            lances.removeIf(lance -> lance.getValor() >= 3000D &&  lance.getValor() <= 5000D);
        }
    }

     private void removeLancesEntre700e1000(List<Lance> lances) {
        if(CollectionUtils.isNotEmpty(lances)) {
            lances.removeIf(lance -> lance.getValor() >= 700D && lance.getValor() <= 1000D);
        }
    }

    private void removeLancesMenoresQue500(List<Lance> lances) {
        if(CollectionUtils.isNotEmpty(lances)) {
            lances.removeIf(lance -> lance.getValor() <= 500D);
        }
    }
}
