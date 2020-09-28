package br.maua.classes;

public class Anime {
    public Anime(String URL, String nome, String sinopse, int episodios, float nota) {
        this.URL = URL;
        Nome = nome;
        Sinopse = sinopse;
        Episodios = episodios;
        Nota = nota;
    }

    public String getURL() {
        return URL;
    }

    public String getNome() {
        return Nome;
    }

    public String getSinopse() {
        return Sinopse;
    }

    public int getEpisodios() {
        return Episodios;
    }

    public float getNota() {
        return Nota;
    }

    private String URL, Nome, Sinopse;
    private int Episodios;
    private float Nota;
}
