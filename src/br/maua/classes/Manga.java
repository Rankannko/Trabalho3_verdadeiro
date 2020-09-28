package br.maua.classes;

public class Manga {
    public Manga(String URL, String nome, String tipo, int capitulos, int volumes, float nota) {
        this.URL = URL;
        Nome = nome;
        Tipo = tipo;
        Capitulos = capitulos;
        Volumes = volumes;
        Nota = nota;
    }

    public String getURL() {
        return URL;
    }

    public String getNome() {
        return Nome;
    }

    public String getTipo() {
        return Tipo;
    }

    public int getCapitulos() {
        return Capitulos;
    }

    public int getVolumes() {
        return Volumes;
    }

    public float getNota() {
        return Nota;
    }

    private String URL, Nome, Tipo;
    private int Capitulos, Volumes;
    private float Nota;
}
