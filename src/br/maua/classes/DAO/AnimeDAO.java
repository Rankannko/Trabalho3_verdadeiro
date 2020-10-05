package br.maua.classes.DAO;

import br.maua.classes.Anime;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**Funçao DAO, para utilizar a classe Anime como um banco de dados SQL
 * @Author João Pedro de Pauda Santoro Azevedo RA: 18.02277-4 e-mail: azevedomasterjp27@hotmail.com
 * @since 04/10
 * @version 1.0
 */
public class AnimeDAO implements DAO<Anime>, DAOFields {
    private Connection connection;
    private String myDBConnectionString = "jdbc:sqlite:DatabaseParaUsar.db";

    /**
     * Função geradora, com uma connecção para o banco de dados.
     */
    public AnimeDAO() {
        try {
            this.connection = DriverManager.getConnection(this.myDBConnectionString);
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

    }

    /**Função basica para retornar um unico item do banco de dados.
     * @param condition
     * @return
     */
    @Override
    public Anime get(String condition) {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(this.getSelectConditionalString(this.getTableName()) +"\""+ condition+"\"");
            while(result.next()) {
                Anime anime = new Anime(
                        result.getString("URL"),
                        result.getString("Nome"),
                        result.getString("Sinopse"),
                        result.getInt("Episodios"),
                        result.getFloat("Nota")
                );
                result.close();
                return anime;
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }
        return null;
    }


    /**Função para retornar todos os items do banco de dados.
     * @return
     */
    @Override
    public List<Anime> getAll() {
        ArrayList animes = new ArrayList();

        try {
            Statement statement = this.connection.createStatement();
            ResultSet result = statement.executeQuery(this.getSelectAllString(this.getTableName()));
            while(result.next()) {
                Anime anime = new Anime(result.getString("URL"), result.getString("nome"), result.getString("Sinopse"), result.getInt("Episodios"), result.getFloat("Nota"));
                animes.add(anime);
            }
            result.close();
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return animes;
    }


    /**
     * @param anime Funcão para criar um anime.
     */
    @Override
    public void create(Anime anime) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(getInsertString(getTableName()));
            preparedStatement.setString(1,anime.getURL());
            preparedStatement.setString(2,anime.getNome());
            preparedStatement.setString(3,anime.getSinopse());
            preparedStatement.setInt(4,anime.getEpisodios());
            preparedStatement.setFloat(5,anime.getNota());
            int retorno = preparedStatement.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @return Função com o nome da tabela
     */
    @Override
    public String getTableName() {
        return "Animes";
    }


    /**
     * @param table DAO para incerir o nome.
     * @return
     */
    @Override
    public String getInsertString(String table) {
        return "INSERT INTO "+ table + "(Url, nome, Sinopse, Episodios, Nota) VALUES (?, ?, ?, ?, ?);";
    }

    /**
     * @param table Função pra selecionar todos os items do banco de dados
     * @return
     */
    @Override
    public String getSelectAllString(String table) {
        return "SELECT * FROM " + table;
    }

    /**
     * @param table Função para selecionar um item em especifico do banco de dados.
     * @return
     */
    @Override
    public String getSelectConditionalString(String table) {
        return "SELECT * FROM " + table + " WHERE Nome LIKE";
    }
}
