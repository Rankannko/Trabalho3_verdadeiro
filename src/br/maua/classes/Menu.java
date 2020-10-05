package br.maua.classes;

import br.maua.classes.DAO.AnimeDAO;
import br.maua.classes.Anime;
import br.maua.classes.DAO.MangaDAO;
import br.maua.classes.Manga;
import br.maua.classes.parser.AnimeParser;
import br.maua.classes.parser.MangaParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLOutput;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**Inicio da classe menu, onde o programa é rodado. Nesta classe, trabalhamos com um menu para escolhas do
 * usuario, e a partir deste ponto, o usuário escolhe se quer fazer uma busca no banco de dados ou se planeja
 * acessar o banco de dados inteiro
 * @Author João Pedro de Pauda Santoro Azevedo RA: 18.02277-4 e-mail: azevedomasterjp27@hotmail.com
 * @since 04/10
 * @version 1.0
 */
public class Menu {

    private Scanner scanner;
    private List<Anime> animes;
    private AnimeDAO animeDAO;
    private List <Manga> mangas;
    private MangaDAO mangaDAO;
    private int opcao;

    /**
     * Inicio da classe menu, inicializando as variaveis basicas que serão utilizadas no programa
     */
    public Menu(){
        animes= new ArrayList<>();
        animeDAO = new AnimeDAO();
        mangas= new ArrayList<>();
        mangaDAO = new MangaDAO();
        scanner = new Scanner(System.in);
    }

    /** Função que inicializa o menu, permitindo uma interação com o usuário. As escolhas que o usuário tem
     * são selecionadas aqui.
     * @throws Exception
     */
    public void run() throws Exception {
        boolean alive = true;
        do{
            menuvisual1();
            while(true) {
                try {
                    opcao = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Por favor inserir um numero valido: ");

                }
            }
            switch(opcao){
            case 0:
                alive = false;
                break;
                case 1:
                System.out.println("Você escolheu animes:");
                    boolean alive2= true;
                do{
                    menuvisual2();
                    int num2 = Integer.parseInt(this.scanner.nextLine());
                    switch (num2){
                        case 0:
                            alive2=false;
                            break;
                        case 1:
                            System.out.println("Diga o nome do anime:");
                            String nome = scanner.nextLine();
                            checarAnime(nome);
                            break;
                        case 2:
                            exibirAnimes();
                            break;
                        case 3:
                            cadastrarNovoAnime();
                            break;
                        default:
                            System.out.println("Não é um numero valido, escolha uma opção que está no menu");
                    }
                } while (alive2);
                break;
            case 2:
                System.out.println("Voce escolheu mangas:");
                boolean alive3 = true;
                do{
                    menuvisual3();
                    int num2 = Integer.parseInt(this.scanner.nextLine());
                    switch (num2){
                        case 0:
                            alive3=false;
                            break;
                        case 1:
                            System.out.println("Digite o manga desejado:");
                            String nome = scanner.nextLine();
                            checarManga(nome);
                            break;
                        case 2:
                            exibirMangas();
                            break;
                        case 3:
                            cadastrarNovoManga();
                            break;
                        default:
                            System.out.println("Não é um numero valido, escolha uma opção que está no menu");
                    }
                } while (alive3);
                break;
                case 3:
                    System.out.println("Diga o nome do anime:");
                    String nome = scanner.nextLine();
                    System.out.println(nome);
                    Anime json_retorno = requestAnimeAPI(nome);
                    System.out.println(json_retorno);
                    break;
            default:
                System.out.println("Não é um numero valido, escolha uma opção que está no menu");
            }
        }while (alive);
    }

    /**
     * Primeiro dos menus utilizados, para vizualizar as opções disponiveis para o usuario.
     */
    private static void menuvisual1(){
        System.out.println("Escolha o que você quer checar:");
        System.out.println("1-Anime");
        System.out.println("2-Manga");
        System.out.println("0-Sair");
    }

    /**
     * Segundo dos menus utilizados, para vizualizar as opções disponiveis para o usuario.
     */
    private static void menuvisual2(){
        System.out.println("Escolha o que deseja:");
        System.out.println("1-Procurar por série específica");
        System.out.println("2-Exibir os dados registrados no banco de dados");
        System.out.println("0-Voltar para o menu anterior");
    }
    /**
     * Terceiro dos menus utilizados, para vizualizar as opções disponiveis para o usuario.
     */
    private static void menuvisual3(){
        System.out.println("Escolha o que deseja:");
        System.out.println("1-Procurar por série específica");
        System.out.println("2-Exibir os dados registrados no banco de dados");
        System.out.println("0-Voltar para o menu anterior");
    }

    /**
     * Função para mostrar todos os animes que tem na lista.
     */
    public void exibirAnimes(){
        animes = animeDAO.getAll();
        System.out.println("Lista de Animes:");
        animes.forEach(anime -> System.out.println(anime));
    }

    /**
     * Função pra mostrar todos os mangas que tem no banco de dados.
     */
    public void exibirMangas(){
        mangas= mangaDAO.getAll();
        System.out.println("Lista de Mangas:");
        mangas.forEach(manga-> System.out.println(manga));
    }

    /**Função para mostrar se tem um anime que esta no banco de dados, e, caso não, fazer o pull do API dos
     * Dados do anime
     * @param nome
     * @throws IOException
     * @throws InterruptedException
     */
    public void checarAnime(String nome) throws IOException, InterruptedException {
        Anime anime;
        anime=animeDAO.get(nome);
        if (anime == null){
            Anime json_retorno = requestAnimeAPI(nome);
            System.out.println(json_retorno);
            }
        else{
            System.out.println(anime);
        }
    }
    /**Função para mostrar se tem um anime que esta no banco de dados, e, caso não, fazer o pull do API dos
     * Dados do manga
     * @param nome
     * @throws IOException
     * @throws InterruptedException
     */
    public void checarManga(String nome) throws IOException, InterruptedException {
        Manga manga;
        manga=mangaDAO.get(nome);
        if (manga == null){
            Manga json_retorno = requestMangaAPI(nome);
            System.out.println(json_retorno);
        }
        else{
            System.out.println(manga);
        }
    }

    /**
     * Função pra criar novo anime
     */
    private void cadastrarNovoAnime() {
        String URL, nome,sinopse;
        int episodios;
        float nota;
        System.out.println("Informe os dados:");
        URL = scanner.nextLine();
        nome = scanner.nextLine();
        sinopse = scanner.nextLine();
        episodios = Integer.parseInt(scanner.nextLine());
        nota = Float.parseFloat(scanner.nextLine());
        animeDAO.create(new Anime(
                URL, nome, sinopse, episodios,nota
        ));
    }

    /**
     * Criação de novo manga
     */
    private void cadastrarNovoManga() {
        String URL,nome,tipo,sinopse;
        int capitulos, volumes;
        float notas;
        System.out.println("Informe os dados:");
        URL = scanner.nextLine();
        nome = scanner.nextLine();
        tipo= scanner.nextLine();
        sinopse = scanner.nextLine();
        capitulos = Integer.parseInt(scanner.nextLine());
        volumes = Integer.parseInt(scanner.nextLine());
        notas = Float.parseFloat(scanner.nextLine());
        mangaDAO.create(new Manga(
                URL, nome,tipo, sinopse,capitulos,volumes,notas
        ));
    }


    /**Função para requesitar animes da API, utilizando os recurços de Anime Parser para se transformar em um
     * item Anime
     * @param name
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    private Anime requestAnimeAPI(String name) throws IOException, InterruptedException {
        System.out.println("Fazendo request");
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .GET().uri(URI.create(("https://api.jikan.moe/v3/search/anime?q=")+name.replace(" ","%20"))).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        AnimeParser animeParser = new AnimeParser();
        Anime anime = animeParser.parseJson(response.body());
        animeDAO.create(anime);
        return anime;
    }

    /**Função para requesitar animes da API, utilizando os recurços de Anime Parser para se transformar em um
     * item Manga
     * @param name
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    private Manga requestMangaAPI(String name) throws IOException, InterruptedException {
        System.out.println("Fazendo request");
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .GET().uri(URI.create(("https://api.jikan.moe/v3/search/manga?q=")+name.replace(" ","%20"))).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        MangaParser mangaParser = new MangaParser();
        Manga manga = mangaParser.parseJson(response.body());
        mangaDAO.create(manga);
        return manga;
    }

}


