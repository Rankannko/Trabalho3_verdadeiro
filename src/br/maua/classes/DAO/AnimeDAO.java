package br.maua.classes.DAO;

import br.maua.classes.Anime;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimeDAO implements DAO<Anime>, DAOFields {
    private Connection connection;
    private String myDBConnectionString = "jdbc:sqlite:AnimesDatabase.db";

    public AnimeDAO() {
        try {
            this.connection = DriverManager.getConnection(this.myDBConnectionString);
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

    }
    @Override
    public List<Anime> get(String condition) {
        ArrayList animes = new ArrayList();

        try {
            Statement statement = this.connection.createStatement();
            ResultSet result = statement.executeQuery(this.getSelectConditionalString(this.getTableName()) + condition);

            while(result.next()) {
                Anime anime = new Anime(result.getString("URL"), result.getString("nome"), result.getString("Sinopse"), result.getInt("Episodios"), result.getFloat("Nota"));
                animes.add(anime);
            }

            result.close();
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return animes;
    }


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

    @Override
    public void update(Anime anime) {
    try{
        PreparedStatement preparedStatement = this.connection.prepareStatement(this.getUpdateString(this.getTableName()));
        preparedStatement.setString(1, anime.getURL());
        preparedStatement.setString(2, anime.getNome());
        preparedStatement.setString(3, anime.getSinopse());
        preparedStatement.setInt(4, anime.getEpisodios());
        preparedStatement.setFloat(5, anime.getNota());
        int var3 = preparedStatement.executeUpdate();
    } catch (Exception var4) {
        var4.printStackTrace();
        }
    }

    @Override
    public void delete(Anime anime) {

    }

    @Override
    public void create(Anime anime) {

    }

    @Override
    public String getTableName() {
        return null;
    }

    @Override
    public String getDeleteString(String table) {
        return null;
    }

    @Override
    public String getUpdateString(String table) {
        return null;
    }

    @Override
    public String getInsertString(String table) {
        return null;
    }

    @Override
    public String getSelectAllString(String table) {
        return null;
    }

    @Override
    public String getSelectConditionalString(String table) {
        return null;
    }
}
