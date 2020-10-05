package br.maua.classes.DAO;

import java.util.List;
/** Interface com o basico de DAO, para ser utilizado em todas as classes DAO
 * @Author João Pedro de Pauda Santoro Azevedo RA: 18.02277-4 e-mail: azevedomasterjp27@hotmail.com
 * @since 04/10
 * @version 1.0
 */
public interface DAO <T>{
    T get(String condition);
    List<T> getAll();
    void create(T t);
}
