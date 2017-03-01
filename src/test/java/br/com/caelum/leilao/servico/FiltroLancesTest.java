package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static br.com.six2six.fixturefactory.Fixture.from;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Created by miche on 28/02/2017.
 */
@Slf4j
@RunWith(SpringRunner.class)
public class FiltroLancesTest {
    @BeforeClass
    public static void setUp() {
        FixtureFactoryLoader.loadTemplates("br.com.caelum.templates");
    }

    @Test
    public void deveRemoverLancesMenoresQue500() {
        List<Lance> lances = from(Lance.class).gimme(5, "lance menor que 500");
        FiltroLances filtro = new FiltroLances();
        lances = filtro.filtra(lances);
        assertThat(lances.size(), equalTo(0));
    }

    @Test
    public void deveRemoverLancesEntre700e1000() {
        List<Lance> lances = from(Lance.class).gimme(5, "lance entre 700 e 1000");
        FiltroLances filtro = new FiltroLances();
        lances = filtro.filtra(lances);
        assertThat(lances.size(), equalTo(0));
    }


    @Test
    public void deveRemoverLancesEntre3000e5000() {
        List<Lance> lances = from(Lance.class).gimme(5, "lance entre 3000 e 5000");
        FiltroLances filtro = new FiltroLances();
        lances = filtro.filtra(lances);
        assertThat(lances.size(), equalTo(0));
    }

    @Test
    public void deveFiltrarLancesMaioresQue5000() {
        List<Lance> lances = from(Lance.class).gimme(5, "lance maior que 5000");
        FiltroLances filtro = new FiltroLances();
        lances = filtro.filtra(lances);
        assertThat(lances.size(), equalTo(5));
    }

    @Test
    public void deveFiltrarLancesEntre1001a2999() {
        List<Lance> lances = from(Lance.class).gimme(5, "lance entre 1001 e 2999");
        FiltroLances filtro = new FiltroLances();
        lances = filtro.filtra(lances);
        assertThat(lances.size(), equalTo(5));
    }

    @Test
    public void deveSelecionarLancesNosValoresMinimosEMaximos() {
        Usuario joao = new Usuario("Joao");

        FiltroLances filtro = new FiltroLances();
        Leilao leilao = Fixture.from(Leilao.class).gimme("leilão com lances crescentes");
        List<Lance> lances = filtro.filtra(leilao.getLances());

        assertEquals(2, lances.size());
        assertThat(lances, hasItem(hasProperty("valor", equalTo(501D))));
        assertThat(lances, hasItem(hasProperty("valor", equalTo(5001D))));
    }

    @Test
    public void deveFiltrarLancesEntre501a699() {
        List<Lance> lances = from(Lance.class).gimme(5, "lance entre 501 e 699");
        FiltroLances filtro = new FiltroLances();
        lances = filtro.filtra(lances);
        assertThat(lances.size(), equalTo(5));
    }

}