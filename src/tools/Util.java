/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.*;
import java.util.InputMismatchException;

import static java.lang.Math.random;

/**
 * Esta classe deve conter somente métodos estáticos que são úteis no geral,
 * como um método para transpor uma matriz, buscar um elemento em um vetor ou
 * limpar a tela do console.
 *
 * @author Professor Gabriel de Carvalho
 */
public class Util {
    public static void clearScreen() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    public static int[][] createCards() {
        int[][] matriz = new int[4][4];

        populeCard(matriz);

        return matriz;
    }

    public static void populeCard(int[][] matriz) {
        int count = 0;
        int[] possibilities = {1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8};
        possibilities = shuffle(possibilities);
        possibilities = shuffle(possibilities);
        possibilities = shuffle(possibilities);

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = possibilities[count];
                count++;
            }
        }

    }

    public static int[][] populeMatriz(int length) {
        int[][] matriz = new int[length][length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = 0;
            }
        }

        return matriz;
    }

    public static boolean saveGame(int[][] cards, int[][] successCards, int success, int[] firstCard, int[] secondCard, int error) {
        try {
            FileWriter file = new FileWriter(System.getProperty("user.dir") + "/save.txt");

            file.append(matrizToString(cards)).append("\n");
            file.append(matrizToString(successCards)).append("\n");
            file.append(arrayToString(firstCard)).append("\n");
            file.append(arrayToString(secondCard)).append("\n");
            file.append(String.valueOf(success)).append("\n");
            file.append(String.valueOf(error));
            file.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String loadSave(int n) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/save.txt"));
            for (int i = 0; i < n; i++){
                reader.readLine();
            }
            String line = reader.readLine();
            reader.close();
            return line;
        } catch (IOException ignored) {}
        return null;
    }

    public static boolean checkFile() {
        File file = new File(System.getProperty("user.dir") + "/save.txt");
        if(file.exists()){
            return true;
        }
        return false;
    }

    public static String matrizToString(int[][] matriz) {
        final StringBuilder str = new StringBuilder();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                str.append(matriz[i][j]);
                if (j != matriz[i].length - 1) {
                    str.append(",");
                }
            }
            if (i != matriz.length - 1) {
                str.append(",");
            }
        }
        return str.toString();
    }

    public static String arrayToString(int[] array) {
        final StringBuilder str = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            str.append(array[i]);
            if (i != array.length - 1) {
                str.append(",");
            }
        }
        return str.toString();
    }

    private static boolean valid(int[][] matriz, int value, int[] pos) {
        for (int j = 0; j < matriz[0].length; j++)
            if (matriz[pos[0]][j] == value && pos[1] != j) return false;

        for (int i = 0; i < matriz.length; i++)
            if (matriz[i][pos[1]] == value && pos[0] != i) return false;

        int box_x = pos[1] / 3;
        int box_y = pos[0] / 3;

        for (int i = box_y * 3; i < box_y * 3 + 3; i++) {
            for (int j = box_x * 3; j < box_x * 3 + 3; j++) {
                if (matriz[i][j] == value && pos[0] != i && pos[1] != j) return false;
            }
        }
        return true;
    }

    /**
     * Este método retorna um inteiro 'i', tal que 'rangeB' >= 'i' >= 'rangeA'.
     *
     * @param rangeA primeiro inteiro possível de ser gerado
     * @param rangeB segundo inteiro possível de ser gerado
     * @return um número aleatório entre 'rangeA' e 'rangeB'
     */
    public static int randInt(int rangeA, int rangeB) {
        if (rangeA < 0) throw new InputMismatchException("rangeA must bu non negative (" + rangeA + ")");
        if (rangeA > rangeB)
            throw new InputMismatchException("rangeB cannot be smaller than rangeA(" + rangeB + " < " + rangeA + ")");
        if (rangeA == rangeB) return rangeA;

        return (int) (random() * 100 * (rangeB - rangeA)) % (1 + rangeB - rangeA) + rangeA;

    }

    /**
     * Este método recebe um vetor de inteiros 'vet' e retorna um novo vetor,
     * composto por todos os elementos de 'vet' porém em posições aletórias
     * (embaralhado).
     *
     * @param vet vetor cujos elementos serão embaralhados.
     * @return vetor de inteiros com os elementos de 'vet' embaralhados.
     */
    public static int[] shuffle(int[] vet) {
        boolean[] posicoesInvalidas = new boolean[vet.length];
        int[] result = new int[vet.length];
        int rangeB = vet.length - 1;
        int posAtual;
        for (int i = 0; i < vet.length; i++) {
            posAtual = -1;

            while (posAtual == -1) {
                int temp = randInt(0, rangeB);
                if (!posicoesInvalidas[temp]) {
                    posicoesInvalidas[temp] = true;
                    posAtual = temp;
                }
            }
            result[posAtual] = vet[i];
        }
        return result;
    }
}
