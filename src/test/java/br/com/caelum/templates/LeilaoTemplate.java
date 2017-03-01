package br.com.caelum.templates;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import net.vidageek.mirror.config.Item;

import java.util.Arrays;
import java.util.List;

/**
 * Created by miche on 28/02/2017.
 */
public class LeilaoTemplate implements TemplateLoader {
    @Override
    public void load() {

        Fixture.of(Leilao.class).addTemplate("leilão com lances crescentes", new Rule() {{
            add("descricao", "X-BOX");
            add("lances", getLancesCrescentes());
        }});

        Fixture.of(Leilao.class).addTemplate("leilão com lances randômicos", new Rule() {{
            add("descricao", random("X-BOX", "Nintendo 64", "Mega Drive", "Playstation 3"));
            add("lances", has(10).of(Lance.class, "lance válido"));
        }});


        Fixture.of(Lance.class).addTemplate("lance válido", new Rule() {{
            add("valor", random(Double.class, range(100D, 3000D)));
            add("usuario", one(Usuario.class, "usuário válido"));
        }});

        Fixture.of(Lance.class).addTemplate("lance menor que 500", new Rule() {{
            add("valor", random(Double.class, range(1D, 499D)));
            add("usuario", one(Usuario.class, "usuário válido"));
        }});


        Fixture.of(Lance.class).addTemplate("lance entre 501 e 699", new Rule() {{
            add("valor", random(Double.class, range(501D, 699D)));
            add("usuario", one(Usuario.class, "usuário válido"));
        }});


        Fixture.of(Lance.class).addTemplate("lance entre 700 e 1000", new Rule() {{
            add("valor", random(Double.class, range(700D, 1000D)));
            add("usuario", one(Usuario.class, "usuário válido"));
        }});

        Fixture.of(Lance.class).addTemplate("lance entre 1001 e 2999", new Rule() {{
            add("valor", random(Double.class, range(1001D, 2999D)));
            add("usuario", one(Usuario.class, "usuário válido"));
        }});

        Fixture.of(Lance.class).addTemplate("lance entre 3000 e 5000", new Rule() {{
            add("valor", random(Double.class, range(3000D, 5000D)));
            add("usuario", one(Usuario.class, "usuário válido"));
        }});

        Fixture.of(Lance.class).addTemplate("lance até 5000", new Rule() {{
            add("valor", random(Double.class, range(3001D, 5000D)));
            add("usuario", one(Usuario.class, "usuário válido"));
        }});

        Fixture.of(Lance.class).addTemplate("lance maior que 5000", new Rule() {{
            add("valor", random(Double.class, range(5001D, 15000D)));
            add("usuario", one(Usuario.class, "usuário válido"));
        }});

        Fixture.of(Usuario.class).addTemplate("usuário válido", new Rule() {{
            add("id", random(Integer.class, range(1, 2000)));
            add("nome", random("José", "Maria", "Pedro", "João", "Ana", "Rafaela"));
        }});
    }

    private List<Lance> getLancesCrescentes() {
        Usuario ana = Usuario.builder().id(1).nome("Ana").build();
        Usuario jose = Usuario.builder().id(2).nome("José").build();
        Usuario pedro = Usuario.builder().id(3).nome("Pedro").build();
        Usuario joao = Usuario.builder().id(4).nome("João").build();
        Usuario jorge = Usuario.builder().id(4).nome("Jorge").build();

        Leilao leilao = new Leilao("X-BOX");
        leilao.propoe(new Lance(joao, 50D));
        leilao.propoe(new Lance(ana, 100D));
        leilao.propoe(new Lance(jose, 200D));
        leilao.propoe(new Lance(pedro, 300D));
        leilao.propoe(new Lance(jorge, 400D));
        leilao.propoe(new Lance(jose, 501D));
        leilao.propoe(new Lance(pedro, 1000D));
        leilao.propoe(new Lance(joao, 5000D));
        leilao.propoe(new Lance(jorge, 5001D));

        return leilao.getLances();
    }
}
