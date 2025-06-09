package jogo.jogador;

import java.util.Scanner;
import jogo.Tabuleiro;
import jogo.personagem.Personagem;

public class JogadorHumano extends Jogador {
    private Scanner scanner;

    public JogadorHumano(Personagem personagem, Scanner scanner) {
        this.personagem = personagem;
        this.scanner = scanner;
    }

    @Override
    public void realizarAcao(Tabuleiro tabuleiro, Jogador jogadorInimigo) {
        System.out.println("\n=== Ação do Jogador: " + personagem.getNome() + " ===");
        System.out.println("Escolha uma ação:");
        System.out.println("1 - Mover (Cima, Baixo, Esquerda, Direita)");
        System.out.println("2 - Atacar");
        System.out.println("3 - Defender");
        System.out.println("4 - Usar Poder Especial");

        int acao = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        switch (acao) {
            case 1 -> {
                System.out.println("Digite a direção (Cima, Baixo, Esquerda, Direita): ");
                String direcao = scanner.nextLine().toLowerCase();
                personagem.mover(direcao, tabuleiro);
            }
            case 2 -> personagem.atacar(jogadorInimigo.getPersonagem(), tabuleiro);
            case 3 -> personagem.defender();
            case 4 -> personagem.usarPoderEspecial(jogadorInimigo.getPersonagem(), tabuleiro);
            default -> System.out.println("Ação inválida. Perdeu a vez.");
        }
    }
}
