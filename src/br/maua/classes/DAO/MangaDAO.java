package br.maua.classes.DAO;

import br.maua.classes.Anime;
import br.maua.classes.Manga;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MangaDAO implements DAO<Manga>, DAOFields{
    private Connection connection;
    private String myDBConnectionString = "jdbc:sqlite:DatabaseParaUsar.db";

    public MangaDAO() {
        try {
            this.connection = DriverManager.getConnection(this.myDBConnectionString);
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

    }

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

    @Override
    public void update(Manga manga) {
        try{
            PreparedStatement preparedStatement = this.connection.prepareStatement(this.getUpdateString(this.getTableName()));
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

    @Override
    public void delete(Manga manga) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(getDeleteString(getTableName()));
            preparedStatement.setString(1, manga.getNome());
            preparedStatement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

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

    @Override
    public String getTableName() {
        return "mangas";
    }

    @Override
    public String getDeleteString(String table) {
        return "DELETE FROM "+ table +" WHERE Id = ?";
    }

    @Override
    public String getUpdateString(String table) {
        return "UPDATE "+ table +" URL = ?, Nome = ?, Tipo = ? , Sinopse = ?, Capitulos = ?, Volumes = ? , Notas = ? WHERE Nome = ?;";
    }

    @Override
    public String getInsertString(String table) {
        return "INSERT INTO "+ table + " (Url, nome, Tipo, Sinopse, Capitulos, Volumes, Notas) VALUES (?, ?, ?, ?, ?, ?, ?);";
    }

    @Override
    public String getSelectAllString(String table) {
        return "SELECT * FROM " + table;
    }

    @Override
    public String getSelectConditionalString(String table) {
        return "SELECT * FROM " + table + " WHERE Nome like";
    }
}
