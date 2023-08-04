package jogo21;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Baralho {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Carta> maoJogador = new ArrayList<>();
        List<Carta> maoDealer = new ArrayList<>();
        Random random = new Random();

        // Inicializa��o do baralho
        List<Carta> baralho = criarBaralho();
        Collections.shuffle(baralho);

        // Distribui��o inicial das cartas
        maoJogador.add(pedirCarta(baralho));
        maoJogador.add(pedirCarta(baralho));
        maoDealer.add(pedirCarta(baralho));

        // L�gica do jogo
        boolean continuar = true;
        while (continuar) {
            System.out.println("Sua m�o: " + maoJogador);
            int pontuacaoJogador = calcularPontuacao(maoJogador);
            System.out.println("Pontua��o atual: " + pontuacaoJogador);

            if (pontuacaoJogador == 21) {
                System.out.println("Voc� tem 21! Voc� vence!");
                continuar = false;
            } else if (pontuacaoJogador > 21) {
                System.out.println("Voc� estourou! Pontua��o: " + pontuacaoJogador);
                continuar = false;
            } else {
                System.out.print("Deseja mais uma carta? (s/n): ");
                String escolha = scanner.nextLine();
                if (escolha.equalsIgnoreCase("s")) {
                    maoJogador.add(pedirCarta(baralho));
                } else {
                    continuar = false;
                }
            }
        }

        while (calcularPontuacao(maoDealer) < 17) {
            maoDealer.add(pedirCarta(baralho));
        }

        // Resultado
        int pontuacaoJogador = calcularPontuacao(maoJogador);
        int pontuacaoDealer = calcularPontuacao(maoDealer);

        System.out.println("Sua m�o final: " + maoJogador);
        System.out.println("M�o do dealer: " + maoDealer);

        if (pontuacaoJogador > 21) {
            System.out.println("Voc� estourou! Dealer vence.");
        } else if (pontuacaoDealer > 21 || pontuacaoJogador > pontuacaoDealer) {
            System.out.println("Voc� venceu!");
        } else if (pontuacaoJogador == pontuacaoDealer) {
            System.out.println("Empate!");
        } else {
            System.out.println("Dealer vence.");
        }
    }

    public static List<Carta> criarBaralho() {
        List<Carta> baralho = new ArrayList<>();
        String[] naipes = {"Ouros", "Espadas", "Paus", "Copas"};
        String[] valores = {"�s", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Dama", "Valete", "Rei"};

        for (String naipe : naipes) {
            for (String valor : valores) {
                baralho.add(new Carta(valor, naipe));
            }
        }

        return baralho;
    }

    public static Carta pedirCarta(List<Carta> baralho) {
        if (!baralho.isEmpty()) {
            return baralho.remove(baralho.size() - 1);
        } else {
            return null; // Baralho vazio
        }
    }

    public static int calcularPontuacao(List<Carta> mao) {
        int pontuacao = 0;
        int asCount = 0;

        for (Carta carta : mao) {
            int valor = carta.getValorNumerico();
            pontuacao += valor;

            if (valor == 1) {
                asCount++;
            }
        }

        while (pontuacao > 21 && asCount > 0) {
            pontuacao -= 10;
            asCount--;
        }

        return pontuacao;
    }
}

class Carta {
    private String valor;
    private String naipe;

    public Carta(String valor, String naipe) {
        this.valor = valor;
        this.naipe = naipe;
    }

    public int getValorNumerico() {
        if (valor.equals("�s")) {
            return 11; // �s pode valer 1 ou 11
        } else if (valor.equals("Valete") || valor.equals("Dama") || valor.equals("Rei")) {
            return 10;
        } else {
            return Integer.parseInt(valor);
        }
    }

    @Override
    public String toString() {
        return valor + " de " + naipe;
    }
}
