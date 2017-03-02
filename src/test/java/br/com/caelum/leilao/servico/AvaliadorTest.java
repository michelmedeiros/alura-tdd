package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static br.com.six2six.fixturefactory.Fixture.from;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertEquals;

/**
 * Created by miche on 28/02/2017.
 */
@Slf4j
@RunWith(SpringRunner.class)
public class AvaliadorTest {

    private Avaliador leiloeiro;

    @Before
    public void before() {
        leiloeiro = new Avaliador();
    }

    @BeforeClass
    public static void setUp() {
        FixtureFactoryLoader.loadTemplates("br.com.caelum.templates");
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void naoDeveValidarLeilaoSemLances() throws Exception{
        exception.expect(Exception.class);
        exception.expectMessage("Um leilão deve possuir pelo menos um lance");
        Leilao leilao = new Leilao("Leilão sem lances");
        leiloeiro.maiorLance(leilao);
    }

    @Test
    public void deveValidarApenasTresMaioresLances() throws Exception{
        Leilao leilao = from(Leilao.class).gimme("leilão com lances crescentes");
        List<Lance> lances = leiloeiro.maioresLances(3L, leilao);
        assertEquals(lances.size(), 3);
        assertThat(lances, hasItems(
                new Lance(Usuario.builder().id(3).nome("Pedro").build(), 1000D),
                new Lance(Usuario.builder().id(4).nome("João").build(), 5000D),
                new Lance(Usuario.builder().id(5).nome("Jorge").build(), 5001D)));
    }


    @Test
    public void deveValidarLancesRandomicos() throws Exception {

        Leilao leilao = from(Leilao.class).gimme("leilão com lances randômicos");

        Double maiorLance = leiloeiro.maiorLance(leilao);
        Double menorLance = leiloeiro.menorLance(leilao);

        assertThat(menorLance, Matchers.greaterThanOrEqualTo(100D));
        assertThat(maiorLance, Matchers.lessThanOrEqualTo(3000D));

        log.info("Maior Lance {} ", maiorLance);
        log.info("Menor Lance {} ", menorLance);
    }




    @Test
    public void deveValidarLancesEmOrdemCrescente() throws Exception {
        Leilao leilao = from(Leilao.class).gimme("leilão com lances crescentes");
        Double maiorLance = leiloeiro.maiorLance(leilao);
        Double menorLance = leiloeiro.menorLance(leilao);
        Double lanceMedio = leiloeiro.lanceMedio(leilao);

        Double maiorEsperado = 5001D;
        Double menorEsperado = 50D;
        Double medioEsperado = 1394.6666666666667D;

        assertEquals(maiorEsperado, maiorLance);
        assertEquals(menorEsperado, menorLance);
        assertEquals(medioEsperado, lanceMedio);
        log.info("Maior Lance {} ", maiorLance);
        log.info("Menor Lance {} ", menorLance);
        log.info("Lance Médio {} ", lanceMedio);
    }

}