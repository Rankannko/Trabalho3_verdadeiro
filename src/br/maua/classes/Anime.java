package br.maua.classes;

public class Anime {
    public Anime(String URL, String nome, String sinopse, int episodios, float nota) {
        this.URL = URL;
        Nome = nome;
        Sinopse = sinopse;
        Episodios = episodios;
        Nota = nota;
    }
    public String toString() {
        return "Manga{" +
                ", URL='" + URL + '\'' +
                ", Nome='" + Nome + '\'' +
                ", Sinopse= " + Sinopse + '\'' +
                ", Episodios=' " + Episodios + '\'' +
                ", Nota= " + Nota +
                '}';
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

    public Anime(String nome) {this(null, nome,null,0,0);
    }

    private String URL, Nome, Sinopse;
    private int Episodios;
    private float Nota;
}
