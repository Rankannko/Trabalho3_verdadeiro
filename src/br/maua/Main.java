package br.maua;
import br.maua.classes.Menu;

/**Classe inicial do programa, chama a função menu que o banco de dados é solicitado.
 * @Author João Pedro de Pauda Santoro Azevedo RA: 18.02277-4 e-mail: azevedomasterjp27@hotmail.com
 * @since 04/10
 * @version 1.0
 */
public class Main{

    /**Função main, inicializadora do programa.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Menu menu = new Menu();
        menu.run();
    }
}
