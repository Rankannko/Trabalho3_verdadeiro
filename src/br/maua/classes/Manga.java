package br.maua.classes;

public class Manga {
    public Manga(String URL, String nome, String tipo, String sinopse, int capitulos, int volumes, float nota) {
        this.URL = URL;
        Nome = nome;
        Tipo = tipo;
        Sinopse = sinopse;
        Capitulos = capitulos;
        Volumes = volumes;
        Nota = nota;
    }
    @Override
    public String toString() {
        return "Manga{" +
                "URL='" + URL + '\'' +
                ", Nome='" + Nome + '\'' +
                ", Tipo=" + Tipo + '\'' +
                ", Sinopse=" + Sinopse + '\'' +
                ", Capitulos=" + Capitulos + '\'' +
                ", Volumes=" + Volumes+ '\'' +
                ", Nota=" + Nota +
                '}';
    }
    public Manga(String nome) {this(null, nome,null,null,0,0,0);
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


    public String getSinopse() {
        return Sinopse;
    }

    private String URL, Nome, Tipo, Sinopse;
    private int Capitulos, Volumes;
    private float Nota;
}
