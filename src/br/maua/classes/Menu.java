package br.maua.classes;


import java.util.Scanner;

public class Menu {

    private Scanner scanner;

    public Menu(){
        scanner = new Scanner(System.in);
    }

    public void run(){
        boolean alive = true;
        do{
            menuvisual();
            int num = Integer.parseInt(this.scanner.nextLine());
            switch(num){
            case 0:
                alive = false;
                break;
                case 1:
                System.out.println("Você escolheu animes:");
                break;
            case 2:
                System.out.println("Voce escolheu mangas:");
                break;
            default:
                System.out.println("Não é um numero valido, escolha uma opção que está no menu");
            }
        }while (alive);
    }


    private static void menuvisual(){
        System.out.println("Escolha o que você quer checar:");
        System.out.println("1-Anime");
        System.out.println("2-Manga");
        System.out.println("0-Sair");
    }
}
