package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;
import static org.junit.Assert.*;

/**
 * Created by miche on 28/02/2017.
 */
@Slf4j
@RunWith(SpringRunner.class)
public class AvaliadorTest {
    @Test
    public void deveValidarLances() {
        Usuario ana = Usuario.builder().id(1).nome("Ana").build();
        Usuario jose = Usuario.builder().id(2).nome("José").build();
        Usuario pedro = Usuario.builder().id(3).nome("Pedro").build();
        Usuario joao = Usuario.builder().id(4).nome("João").build();

        Leilao leilao = new Leilao("Playstation 3");
        leilao.propoe(new Lance(ana, 200.0));
        leilao.propoe(new Lance(joao, 300.0));
        leilao.propoe(new Lance(jose, 400.0));
        leilao.propoe(new Lance(pedro, 100.0));

        Avaliador leiloeiro = new Avaliador();
        Double maiorLance = leiloeiro.maiorLance(leilao);
        Double menorLance = leiloeiro.menorLance(leilao);
        Double lanceMedio = leiloeiro.lanceMedio(leilao);

        Double maiorEsperado = 400D;
        Double menorEsperado = 100D;
        Double medioEsperado = 250D;

        assertEquals(maiorEsperado, maiorLance);
        assertEquals(menorEsperado, menorLance);
        assertEquals(medioEsperado, lanceMedio);
        log.info("Maior Lance {} ", maiorLance);
        log.info("Menor Lance {} ", menorLance);
        log.info("Lance Médio {} ", menorLance);
    }
}