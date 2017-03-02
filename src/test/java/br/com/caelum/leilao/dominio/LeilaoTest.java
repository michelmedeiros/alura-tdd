package br.com.caelum.leilao.dominio;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static br.com.six2six.fixturefactory.Fixture.from;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by Michel Medeiros on 01/03/2017.
 */
@Slf4j
@RunWith(SpringRunner.class)
public class LeilaoTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @BeforeClass
    public static void setUp() {
        FixtureFactoryLoader.loadTemplates("br.com.caelum.templates");
    }

    @Test
    public void naoDeveCriarLeilaoComLanceZeradoOuNegativo() throws Exception{
        exception.expect(Exception.class);
        exception.expectMessage("Não pode ser realizado lance zerado ou negativo.");
        Leilao leilao = new Leilao("Leilão sem lances");
        leilao.propoe(new Lance(Usuario.builder().nome("ana").build(), -100D));
    }

    @Test
    public void deveReceberUmLance() {
        Leilao leilao = Fixture.from(Leilao.class).gimme("leilão com um lance");
        assertThat(leilao.getLances().size(), is(1));
    }

    @Test
    public void deveReceberVariosLances() {
        Leilao leilao = from(Leilao.class).gimme("leilão com lances crescentes");
        assertThat(leilao.getLances().size(), is(9));
        assertThat(leilao.getLances(), hasItem(hasProperty("valor", equalTo(5001D))));
        assertThat(leilao.getLances(), hasItem(hasProperty("valor", equalTo(5000D))));
        assertThat(leilao.getLances(), hasItem(hasProperty("valor", equalTo(1000D))));
    }

    @Test
    public void permiteDobrarLanceUsuario() throws Exception {
        Leilao leilao = new Leilao("Novo IPad");
        leilao.propoe(new Lance(new Usuario("ana"), 1000D));
        leilao.propoe(new Lance(new Usuario("pedro"), 500D));
        leilao.propoe(new Lance(new Usuario("ana"), 1500D));
        leilao.propoe(new Lance(new Usuario("pedro"), 1200D));
        leilao.dobrarLance(new Usuario("ana"));
        assertThat(leilao.getLances().size(), is(5));
        assertThat(leilao.getLances(), hasItem(hasProperty("valor", equalTo(3000D))));
    }

    @Test
    public void naoDeveDobrarLanceUsuarioComApenasUmUnicoLance() throws Exception {
        Leilao leilao = new Leilao("Novo IPad");
        leilao.propoe(new Lance(new Usuario("ana"), 1000D));
        leilao.propoe(new Lance(new Usuario("pedro"), 500D));
        leilao.dobrarLance(new Usuario("ana"));
        assertThat(leilao.getLances().size(), is(2));
        assertThat(leilao.getLances(), hasItem(hasProperty("valor", equalTo(1000D))));
    }

    @Test
    public void naoDevePermitirLancesSimultaneosParaMesmaPessoa() throws Exception {
        exception.expect(Exception.class);
        exception.expectMessage("Não pode ser realizado dois lances consecutivos para o mesmo usuário.");
        Leilao leilao = new Leilao("Mac Book Pro");
        leilao.propoe(new Lance(new Usuario("ana"), 1000D));
        leilao.propoe(new Lance(new Usuario("ana"), 2000D));
        assertThat(leilao.getLances().size(), is(1));
        assertThat(leilao.getLances(), hasItem(hasProperty("valor", equalTo(1000D))));

    }

    @Test
    public void naoDevePermitir5LancesParaMesmaPessoa() throws Exception {
        exception.expect(Exception.class);
    exception.expectMessage("Não pode ser realizado mais de 5 lances por usuário.");
        Leilao leilao = new Leilao("Mac Book Pro");
        leilao.propoe(new Lance(new Usuario("ana"), 1000D));
        leilao.propoe(new Lance(new Usuario("pedro"), 1000D));
        leilao.propoe(new Lance(new Usuario("ana"), 2000D));
        leilao.propoe(new Lance(new Usuario("maria"), 1000D));
        leilao.propoe(new Lance(new Usuario("ana"), 3000D));
        leilao.propoe(new Lance(new Usuario("josé"), 1000D));
        leilao.propoe(new Lance(new Usuario("ana"), 4000D));
        leilao.propoe(new Lance(new Usuario("pedro"), 1000D));
        leilao.propoe(new Lance(new Usuario("ana"), 5000D));
        leilao.propoe(new Lance(new Usuario("jorge"), 1000D));
        leilao.propoe(new Lance(new Usuario("ana"), 500D));


        assertThat(leilao.getLances().size(), is(10));
        assertThat(leilao.getLances(), hasItem(hasProperty("valor", equalTo(1000D))));
        assertThat(leilao.getLances(), hasItem(hasProperty("valor", equalTo(2000D))));
        assertThat(leilao.getLances(), hasItem(hasProperty("valor", equalTo(3000D))));
        assertThat(leilao.getLances(), hasItem(hasProperty("valor", equalTo(4000D))));
        assertThat(leilao.getLances(), not(hasItem(hasProperty("valor", equalTo(500D)))));

    }

}