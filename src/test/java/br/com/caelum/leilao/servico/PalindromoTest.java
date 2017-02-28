package br.com.caelum.leilao.servico;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by miche on 28/02/2017.
 */
@Slf4j
@RunWith(SpringRunner.class)
public class PalindromoTest {

    @Test
    public void deveValidaPalidromo() {
        Palindromo palindromo = new Palindromo();
        boolean resultado = palindromo.ehPalindromo("Anotaram a data da maratona");
        assertEquals(resultado, Boolean.TRUE);
    }

    @Test
    public void deveIdentificarPalindromoEFiltrarCaracteresInvalidos() {
        Palindromo p = new Palindromo();

        boolean resultado = p.ehPalindromo(
                "Socorram-me subi no onibus em Marrocos");
        assertTrue(resultado);
    }

    @Test
    public void deveIdentificarSeNaoEhPalindromo() {
        Palindromo p = new Palindromo();

        boolean resultado = p.ehPalindromo(
                "E preciso amar as pessoas como se nao houvesse amanha");
        assertFalse(resultado);
    }

}