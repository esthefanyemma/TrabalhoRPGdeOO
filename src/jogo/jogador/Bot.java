package jogo.jogador;

import java.util.Random;
import jogo.Tabuleiro;
import jogo.personagem.Personagem;

public class Bot extends Jogador {
    private Random random = new Random();

    public Bot(Personagem personagem) {
        this.personagem = personagem;
    }
    
    @Override
    public void realizarAcao(Tabuleiro tabuleiro, Jogador jogadorInimigo) {
        System.out.println("\n=== Ação do Bot: " + personagem.getNome() + " ===");
        System.out.println("O bot está pensando em sua próxima ação...");

        try {
            Thread.sleep(1500); // Espera entre 1 e 3 segundos
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Escolhe uma ação aleatória
        int acao = random.nextInt(4) + 1; // Gera um número entre 1 e 4
        String[] direcoes = {"Cima", "Baixo", "Esquerda", "Direita"};

        switch (acao) {
            case 1 -> {
                String direcao = direcoes[random.nextInt(direcoes.length)];
                System.out.println("Bot escolheu MOVER para: " + direcao);
                personagem.mover(direcao, tabuleiro);
            }
            case 2 -> {
                personagem.atacar(jogadorInimigo.getPersonagem(), tabuleiro);
                System.out.println("Bot escolheu ATACAR");
            }
            case 3 -> {
                personagem.defender();
                System.out.println("Bot escolheu DEFENDER");
            }
            case 4 -> {
                personagem.usarPoderEspecial(jogadorInimigo.getPersonagem(), tabuleiro);
                System.out.println("Bot escolheu USAR PODER ESPECIAL");
            }
            default -> System.out.println("Ação inválida. Perdeu a vez.");
        }

        System.out.println("Status do Bot pós ação: ");
        System.out.println("Vida: " + personagem.getPontosDeVida());
        System.out.println("Posição: " + personagem.getLinha() + ", " + personagem.getColuna());
    }
}
