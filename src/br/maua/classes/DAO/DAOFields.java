package br.maua.classes.DAO;
/**Interface com as funções utilizadas nas clases DAO
 * @Author João Pedro de Pauda Santoro Azevedo RA: 18.02277-4 e-mail: azevedomasterjp27@hotmail.com
 * @since 04/10
 * @version 1.0
 */
public interface DAOFields {
    String getTableName();
    String getInsertString(String table);
    String getSelectAllString(String table);
    String getSelectConditionalString(String table);
}

