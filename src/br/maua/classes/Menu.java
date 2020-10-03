package br.maua.classes;

import br.maua.classes.DAO.AnimeDAO;
import br.maua.classes.Anime;
import br.maua.classes.DAO.MangaDAO;
import br.maua.classes.Manga;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private Scanner scanner;
    private List<Anime> animes;
    private AnimeDAO animeDAO;
    private List <Manga> mangas;
    private MangaDAO mangaDAO;

    public Menu(){
        animes= new ArrayList<>();
        animeDAO = new AnimeDAO();
        mangas= new ArrayList<>();
        mangaDAO = new MangaDAO();
        scanner = new Scanner(System.in);
    }

    public void run(){
        boolean alive = true;
        do{
            menuvisual1();
            int num = Integer.parseInt(this.scanner.nextLine());
            switch(num){
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
                            checarAnime();
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
                            checarManga();
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
            default:
                System.out.println("Não é um numero valido, escolha uma opção que está no menu");
            }
        }while (alive);
    }


    private static void menuvisual1(){
        System.out.println("Escolha o que você quer checar:");
        System.out.println("1-Anime");
        System.out.println("2-Manga");
        System.out.println("0-Sair");
    }

    private static void menuvisual2(){
        System.out.println("Escolha o que deseja:");
        System.out.println("1-Procurar por série específica");
        System.out.println("2-Exibir os dados registrados no banco de dados");
        System.out.println("0-Voltar para o menu anterior");
    }

    private static void menuvisual3(){
        System.out.println("Escolha o que deseja:");
        System.out.println("1-Procurar por série específica");
        System.out.println("2-Exibir os dados registrados no banco de dados");
        System.out.println("0-Voltar para o menu anterior");
    }

    public void exibirAnimes(){
        animes = animeDAO.getAll();
        System.out.println("Lista de Animes:");
        animes.forEach(anime -> System.out.println(anime));
    }

    public void exibirMangas(){
        mangas= mangaDAO.getAll();
        System.out.println("Lista de Mangas:");
        mangas.forEach(manga-> System.out.println(manga));
    }

    public void checarAnime(){
        System.out.println("Digite o nome do anime");
        String nome = scanner.nextLine();
        animes=animeDAO.get(nome);
        animes.forEach(anime->System.out.println(anime));
    }

    public void checarManga(){
        System.out.println("O nome do Manga é MHA");
        String nome = scanner.nextLine();
        mangas=mangaDAO.get(nome);
        mangas.forEach(manga->System.out.println(manga));
    }

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
}


