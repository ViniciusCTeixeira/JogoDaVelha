package game;

import tools.Util;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Jdv extends Game {
    private int[][] cards;
    private int error;
    private int success;
    private int[][] successCards;
    private int[] firstCard;
    private int[] secondCard;

    @Override
    public void criaNovo() {
        this.cards = Util.createCards();
        this.error = 0;
        this.success = 0;
        this.successCards = Util.populeMatriz(4);
        this.firstCard = new int[2];
        this.secondCard = new int[2];
    }

    @Override
    public void executa() {
        try {
            Util.clearScreen();

            System.out.println("--------------- JOGO DA VELHA ---------------");
            System.out.println();
            System.out.println("Regas:");
            System.out.println(" - Será criada uma matriz 4x4 contendo os pares.");
            System.out.println(" - Voce tera 10 segundos para memorizar os pares.");
            System.out.println(" - Voce so pode errar 6 vezes.");
            System.out.println(" - Para salvar digite '0' a qualquer momento.");
            System.out.println();

            System.out.println("Pares:");
            //-Mostro os cardes.
            for (int i = 0; i < this.cards.length; i++) {
                for (int j = 0; j < this.cards[i].length; j++) {
                    System.out.print("   " + this.cards[i][j] + "   ");
                }
                System.out.println();
            }

            Thread.sleep(10000);
            Util.clearScreen();

            Scanner teclado = new Scanner(System.in);
            int linha;
            int linha2;
            int coluna;
            int coluna2;

            while (this.error <= 6 || this.success == 8) {
                System.out.println("--------------- JOGO DA VELHA ---------------");
                System.out.println();
                System.out.println("Regas:");
                System.out.println(" - Será criada uma matriz 4x4 contendo os pares.");
                System.out.println(" - Voce tera 10 segundos para memorizar os pares.");
                System.out.println(" - Voce so pode errar 6 vezes.");
                System.out.println(" - Para salvar digite '0' a qualquer momento.");
                System.out.println();

                System.out.println("Erros: " + this.error);
                System.out.println("Acertos: " + this.success);

                for (int i = 0; i < this.cards.length; i++) {
                    for (int j = 0; j < this.cards[i].length; j++) {
                        if (this.successCards[i][j] == 1) {
                            System.out.print("   " + this.cards[i][j] + "   ");
                        } else {
                            System.out.print("   \uD83C\uDFB4   ");
                        }
                    }
                    System.out.println();
                }

                System.out.println();
                System.out.println("Escolha a primeira carta.");

                System.out.print("Linha: ");
                linha = teclado.nextInt();

                if (linha == 0) {
                    boolean save = Util.saveGame(this.cards, this.successCards, this.success, this.firstCard, this.secondCard, this.error);
                    if (save) {
                        System.out.println("Jogo Salvo!");
                        Thread.sleep(2000);
                        this.sai();
                    } else {
                        System.out.println("Não foi possivel salvar!");
                        Thread.sleep(2000);
                    }
                }

                while (linha < 1 || linha > 4) {
                    System.out.print("Linha: ");
                    linha = teclado.nextInt();
                }

                System.out.print("Coluna: ");
                coluna = teclado.nextInt();

                if (coluna == 0) {
                    boolean save = Util.saveGame(this.cards, this.successCards, this.success, this.firstCard, this.secondCard, this.error);
                    if (save) {
                        System.out.println("Jogo Salvo!");
                        Thread.sleep(2000);
                        this.sai();
                    } else {
                        System.out.println("Não foi possivel salvar!");
                        Thread.sleep(2000);
                    }
                }

                while (coluna < 1 || coluna > 4) {
                    System.out.print("Coluna: ");
                    coluna = teclado.nextInt();
                }

                Util.clearScreen();

                System.out.println("--------------- JOGO DA VELHA ---------------");
                System.out.println();
                System.out.println("Regas:");
                System.out.println(" - Será criada uma matriz 4x4 contendo os pares.");
                System.out.println(" - Voce tera 10 segundos para memorizar os pares.");
                System.out.println(" - Voce so pode errar 6 vezes.");
                System.out.println(" - Para salvar digite '0' a qualquer momento.");
                System.out.println();

                this.firstCard[0] = linha - 1;
                this.firstCard[1] = coluna - 1;

                for (int i = 0; i < this.cards.length; i++) {
                    for (int j = 0; j < this.cards[i].length; j++) {
                        if (this.successCards[i][j] == 1 || (i == this.firstCard[0] & j == this.firstCard[1])) {
                            System.out.print("   " + this.cards[i][j] + "   ");
                        } else {
                            System.out.print("   \uD83C\uDFB4   ");
                        }
                    }
                    System.out.println();
                }

                do {
                    System.out.println();
                    System.out.println("Escolha a segunda carta.");

                    System.out.print("Linha: ");
                    linha2 = teclado.nextInt();

                    if (linha2 == 0) {
                        boolean save = Util.saveGame(this.cards, this.successCards, this.success, this.firstCard, this.secondCard, this.error);
                        if (save) {
                            System.out.println("Jogo Salvo!");
                            Thread.sleep(2000);
                            this.sai();
                        } else {
                            System.out.println("Não foi possivel salvar!");
                            Thread.sleep(2000);
                        }
                    }

                    while (linha2 < 1 || linha2 > 4) {
                        System.out.print("Linha: ");
                        linha2 = teclado.nextInt();
                    }

                    System.out.print("Coluna: ");
                    coluna2 = teclado.nextInt();

                    if (coluna2 == 0) {
                        boolean save = Util.saveGame(this.cards, this.successCards, this.success, this.firstCard, this.secondCard, this.error);
                        if (save) {
                            System.out.println("Jogo Salvo!");
                            Thread.sleep(2000);
                            this.sai();
                        } else {
                            System.out.println("Não foi possivel salvar!");
                            Thread.sleep(2000);
                        }
                    }

                    while (coluna2 < 1 || coluna2 > 4) {
                        System.out.print("Coluna: ");
                        coluna2 = teclado.nextInt();
                    }

                } while (coluna2 == coluna & linha2 == linha);

                Util.clearScreen();

                System.out.println("--------------- JOGO DA VELHA ---------------");
                System.out.println();
                System.out.println("Regas:");
                System.out.println(" - Será criada uma matriz 4x4 contendo os pares.");
                System.out.println(" - Voce tera 10 segundos para memorizar os pares.");
                System.out.println(" - Voce so pode errar 6 vezes.");
                System.out.println(" - Para salvar digite '0' a qualquer momento.");
                System.out.println();

                this.secondCard[0] = linha2 - 1;
                this.secondCard[1] = coluna2 - 1;

                for (int i = 0; i < this.cards.length; i++) {
                    for (int j = 0; j < this.cards[i].length; j++) {
                        if (this.successCards[i][j] == 1 || (i == this.firstCard[0] & j == this.firstCard[1]) || (i == this.secondCard[0] & j == this.secondCard[1])) {
                            System.out.print("   " + this.cards[i][j] + "   ");
                        } else {
                            System.out.print("   \uD83C\uDFB4   ");
                        }
                    }
                    System.out.println();
                }

                if (this.cards[firstCard[0]][firstCard[1]] == this.cards[secondCard[0]][secondCard[1]]) {
                    System.out.println("Voce acertou!");
                    this.successCards[firstCard[0]][firstCard[1]] = 1;
                    this.successCards[secondCard[0]][secondCard[1]] = 1;
                    this.success++;
                    Thread.sleep(5000);
                } else {
                    System.out.println("Voce errou!");
                    this.error++;
                    Thread.sleep(5000);
                }

                Util.clearScreen();

            }
            System.out.println("--------------- JOGO DA VELHA ---------------");
            System.out.println();
            if(this.error > 6 & this.success < 8){
                System.out.println("Voce perdeu o jogo");
            }else{
                System.out.println("Voce ganhou o jogo");
            }
            System.out.println("Erros: " + this.error);
            System.out.println("Acertos: " + this.success);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void carrega() {
        int count,i,j;
        this.cards = new int[4][4];
        if(!Util.checkFile()){
            System.out.println("Não foi possivel carregar o save.");
            System.out.println("Verifique se o arquivo se encontra na pasta do executavel.");
            this.sai();
        }
        String[] cards = Objects.requireNonNull(Util.loadSave(0)).split(",");
        count = 0;
        for (i = 0; i < this.cards.length; i++) {
            for (j = 0; j < this.cards[i].length; j++) {
                this.cards[i][j] = Integer.parseInt(cards[count]);
                count++;
            }
        }

        this.successCards = new int[4][4];
        String[] successCards = Objects.requireNonNull(Util.loadSave(1)).split(",");
        count = 0;
        for (i = 0; i < this.successCards.length; i++) {
            for (j = 0; j < this.successCards[i].length; j++) {
                if(this.successCards[i][j] != Integer.parseInt(successCards[count])){
                    this.successCards[i][j] = Integer.parseInt(successCards[count]);
                }
                count++;
            }
        }

        this.firstCard = new int[2];
        String[] firstCard = Objects.requireNonNull(Util.loadSave(2)).split(",");
        for (i = 0; i < this.firstCard.length; i++) {
            this.firstCard[i] = Integer.parseInt(firstCard[i]);
        }

        this.secondCard = new int[2];
        String[] secondCard = Objects.requireNonNull(Util.loadSave(3)).split(",");
        for (i = 0; i < this.secondCard.length; i++) {
            this.secondCard[i] = Integer.parseInt(secondCard[i]);
        }

        this.success = Integer.parseInt(Objects.requireNonNull(Util.loadSave(4)));

        this.error = Integer.parseInt(Objects.requireNonNull(Util.loadSave(5)));
    }

}
