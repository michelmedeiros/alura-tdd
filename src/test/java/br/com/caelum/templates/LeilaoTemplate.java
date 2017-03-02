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

import static br.com.caelum.templates.LanceTemplate.getLancesCrescentes;

/**
 * Created by miche on 28/02/2017.
 */
public class LeilaoTemplate implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(Leilao.class).addTemplate("leilão com um lance", new Rule() {{
            add("descricao", "Mac Book Pro 15");
            add("lances", has(1).of(Lance.class, "lance válido"));
        }});

        Fixture.of(Leilao.class).addTemplate("leilão com lances crescentes", new Rule() {{
            add("descricao", "X-BOX");
            add("lances", getLancesCrescentes());
        }});

        Fixture.of(Leilao.class).addTemplate("leilão com lances randômicos", new Rule() {{
            add("descricao", random("X-BOX", "Nintendo 64", "Mega Drive", "Playstation 3"));
            add("lances", has(10).of(Lance.class, "lance válido"));
        }});
    }
}
