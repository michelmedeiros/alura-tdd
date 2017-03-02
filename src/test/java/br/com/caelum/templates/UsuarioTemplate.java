package br.com.caelum.templates;

import br.com.caelum.leilao.dominio.Usuario;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

/**
 * Created by Michel Medeiros on 01/03/2017.
 */
public class UsuarioTemplate implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(Usuario.class).addTemplate("usuário válido", new Rule() {{
            add("id", random(Integer.class, range(1, 2000)));
            add("nome", random("José", "Maria", "Pedro", "João", "Ana", "Rafaela"));
        }});
    }
}
