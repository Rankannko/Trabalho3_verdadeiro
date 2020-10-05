package br.maua.classes;

/**Classe Anime, utilizada como base do banco de dados.
 * @Author João Pedro de Pauda Santoro Azevedo RA: 18.02277-4 e-mail: azevedomasterjp27@hotmail.com
 * @since 04/10
 * @version 1.0
 */
public class Anime {
    public Anime(String URL, String nome, String sinopse, int episodios, float nota) {
        this.URL = URL;
        Nome = nome;
        Sinopse = sinopse;
        Episodios = episodios;
        Nota = nota;
    }

    /**Maneira de exibir a classe anime de uma maneira visual.
     * @return
     */
    public String toString() {
        return "Anime{" +
                "URL='" + URL + '\'' +
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
    public Anime (){};

    private String URL, Nome, Sinopse;
    private int Episodios;
    private float Nota;
}
