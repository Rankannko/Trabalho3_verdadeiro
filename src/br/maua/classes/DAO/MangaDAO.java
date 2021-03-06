package br.maua.classes.DAO;

import br.maua.classes.Anime;
import br.maua.classes.Manga;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * @Author João Pedro de Pauda Santoro Azevedo RA: 18.02277-4 e-mail: azevedomasterjp27@hotmail.com
 * @since 04/10
 * @version 1.0
 */
public class MangaDAO implements DAO<Manga>, DAOFields{
    private Connection connection;
    private String myDBConnectionString = "jdbc:sqlite:DatabaseParaUsar.db";

    /**
     * Função geradora, com uma connecção para o banco de dados.
     */
    public MangaDAO() {
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
    public Manga get(String condition) {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(this.getSelectConditionalString(this.getTableName()) +"\""+ condition+"\"");
            while(result.next()) {
                Manga manga = new Manga(
                        result.getString("URL"),
                        result.getString("Nome"),
                        result.getString("Tipo"),
                        result.getString("Sinopse"),
                        result.getInt("Capitulos"),
                        result.getInt("Volumes"),
                        result.getFloat("Notas")
                );
                result.close();
                return manga;
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
    public List<Manga> getAll() {
        ArrayList mangas = new ArrayList();
        try {
            Statement statement = this.connection.createStatement();
            ResultSet result = statement.executeQuery(this.getSelectAllString(this.getTableName()));

            while(result.next()) {
                Manga manga = new Manga(
                        result.getString("URL"),
                        result.getString("Nome"),
                        result.getString("Tipo"),
                        result.getString("Sinopse"),
                        result.getInt("Capitulos"),
                        result.getInt("Volumes"),
                        result.getFloat("Notas"));
                mangas.add(manga);
            }
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mangas;
    }
    /**
     * @param manga Funcão para criar um manga.
     */
    @Override
    public void create(Manga manga) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(getInsertString(getTableName()));
            preparedStatement.setString(1, manga.getURL());
            preparedStatement.setString(2, manga.getNome());
            preparedStatement.setString(3,manga.getTipo());
            preparedStatement.setString(4, manga.getSinopse());
            preparedStatement.setInt(5,manga.getCapitulos());
            preparedStatement.setInt(6,manga.getVolumes());
            preparedStatement.setFloat(7, manga.getNota());
            int var3 = preparedStatement.executeUpdate();
        } catch (Exception var4) {
            var4.printStackTrace();
        }
    }
    /**
     * @return Função com o nome da tabela
     */
    @Override
    public String getTableName() {
        return "mangas";
    }
    /**
     * @param table DAO para incerir o nome.
     * @return
     */
    @Override
    public String getInsertString(String table) {
        return "INSERT INTO "+ table + " (Url, nome, Tipo, Sinopse, Capitulos, Volumes, Notas) VALUES (?, ?, ?, ?, ?, ?, ?);";
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
        return "SELECT * FROM " + table + " WHERE Nome like";
    }
}
