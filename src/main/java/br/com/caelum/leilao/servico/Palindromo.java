package br.com.caelum.leilao.servico;

/**
 * Created by miche on 28/02/2017.
 */
public class Palindromo {

    public boolean ehPalindromo(String frase) {

        boolean resultado = false;
        String fraseFiltrada = frase.replace(" ", "").replace("-", "");
        String fraseInvertida = new StringBuilder(fraseFiltrada).reverse().toString();
        if(fraseFiltrada.equalsIgnoreCase(fraseInvertida)) {
            resultado = true;
        }
        return resultado;
    }
}
