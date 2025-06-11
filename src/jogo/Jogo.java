package jogo;

import java.util.Scanner;
import jogo.jogador.Bot;
import jogo.jogador.Jogador;
import jogo.jogador.JogadorHumano;
import jogo.personagem.Arqueiro;
import jogo.personagem.Guerreiro;
import jogo.personagem.Mago;
import jogo.personagem.Personagem;
public class Jogo {
    private Scanner scanner = new Scanner(System.in);
    private Jogador jogador1;
    private Jogador jogador2;
    Tabuleiro tabuleiro = new Tabuleiro();

    public void iniciar() {
        // Aqui vai a lógica de configuração inicial e controle do jogo
        System.out.println("\n=== DUELO DE PERSONAGENS ===");

        System.out.print("\nEscolha o modo de jogo: ");
        System.out.println("\n1 - Jogador vs Jogador");
        System.out.println("2 - Jogador vs Computador");
        
        int modo = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        //Jogador 1 (sempre humano)
        System.out.print("\nDigite o nome do Jogador 1: ");
        String nomeJogador1 = scanner.nextLine();
        Personagem p1 = escolherPersonagem(nomeJogador1);

        jogador1 = new JogadorHumano(p1, scanner);

        //Jogador 2 (pode ser humano ou computador)
        if (modo == 1) {
            System.out.print("Digite o nome do Jogador 2: ");
            String nomeJogador2 = scanner.nextLine();
            Personagem p2 = escolherPersonagem(nomeJogador2);
            jogador2 = new JogadorHumano(p2, scanner);
        } else {
            Personagem p2 = new Guerreiro("Botão");
            jogador2 = new Bot(p2); // Jogador 2 é um bot, não precisa de Scanner
            System.out.println("\nBot escolheu: Guerreiro Botão");
        }
        System.out.println("Jogadores prontos para o duelo!");
        System.out.println("Iniciando o duelo entre: \n" + jogador1.getPersonagem().getResumo());
        System.out.println("e " + jogador2.getPersonagem().getResumo());

        //Tabuleiro tabuleiro = new Tabuleiro();

        tabuleiro.posicionarAleatoriamente(jogador1.getPersonagem());
        tabuleiro.posicionarAleatoriamente(jogador2.getPersonagem());

        Jogador jogadorAtual = jogador1;
        Jogador jogadorInimigo = jogador2;

        while (true) { 
            tabuleiro.imprimirTabuleiro();

            System.out.println("\nVez de: " + jogadorAtual.getPersonagem().getNome());
            System.out.println("Pontos de Vida: " + jogadorAtual.getPersonagem().getPontosDeVida());
            System.out.println("Resumo do inimigo: " + jogadorInimigo.getPersonagem().getResumo());
            System.out.println("Meu resumo: " + jogadorAtual.getPersonagem().getResumo());

            jogadorAtual.realizarAcao(tabuleiro, jogadorInimigo);

            if (jogadorInimigo.getPersonagem().getPontosDeVida() <= 0) {
                System.out.println("\n" + jogadorAtual.getPersonagem().getNome() + " venceu o duelo!");
                break;
            }

            Jogador temp = jogadorAtual;
            jogadorAtual = jogadorInimigo;
            jogadorInimigo = temp;
        }
    }

    private Personagem escolherPersonagem(String nome) {
        System.out.println("\nEscolha seu tipo personagem:");
        System.out.println("\n1 - Mago");
        System.out.println("2 - Arqueiro");
        System.out.println("3 - Guerreiro");

        int escolha = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        return switch (escolha) {
            case 1 -> new Mago(nome);
            case 2 -> new Arqueiro(nome);
            case 3 -> new Guerreiro(nome);
            default -> {
                System.out.println("Escolha inválida, usando Guerreiro por padrão.");
                yield  new Guerreiro(nome);
            }
        };
    }
}
