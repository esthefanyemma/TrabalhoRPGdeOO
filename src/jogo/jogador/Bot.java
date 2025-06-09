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
        
        int distancia = tabuleiro.calcularDistancia(personagem, jogadorInimigo.getPersonagem());
        boolean vidaBaixa = personagem.getPontosDeVida() < 30;
        Random random = new Random();

        if (distancia > personagem.getAlcanceDeAtaque()) {
            // Se o bot está longe, ele tenta se aproximar
            System.out.println("Bot está se aproximando do inimigo.");
            moverEmDirecaoAoInimigo(tabuleiro, jogadorInimigo.getPersonagem());
        } else if (vidaBaixa && random.nextDouble() < 0.5) {
            // 70% de chance de defender se a vida estiver baixa
            System.out.println("Bot está com vida baixa, optando por defender.");
            personagem.defender();
        } else if (personagem.temPoderEspecial() && random.nextDouble() < 0.4) {
            // 40% de chance de usar poder especial
            System.out.println("Bot está usando poder especial.");
            personagem.usarPoderEspecial(jogadorInimigo.getPersonagem(), tabuleiro);
        } else if (distancia <= personagem.getAlcanceDeAtaque()) {
            System.out.println("Bot está atacando o inimigo.");
            personagem.atacar(jogadorInimigo.getPersonagem(), tabuleiro);
        } else {
            // Movimento aleatório
            String[] direcoes = {"Cima", "Baixo", "Esquerda", "Direita"};
            String direcao;
            do { 
                direcao = direcoes[random.nextInt(direcoes.length)];
            } while (!movimentoSeguro(direcao, tabuleiro));

            System.out.println("Bot está se movendo para: " + direcao);
            personagem.mover(direcao, tabuleiro);
        }

        //PRIMEIRA IMPLEMETAÇÃO PARA O BOT
        // switch (acao) {
        //     case 1 -> {
        //         String direcao = direcoes[random.nextInt(direcoes.length)];
        //         System.out.println("Bot escolheu MOVER para: " + direcao);
        //         personagem.mover(direcao, tabuleiro);
        //     }
        //     case 2 -> {
        //         personagem.atacar(jogadorInimigo.getPersonagem(), tabuleiro);
        //         System.out.println("Bot escolheu ATACAR");
        //     }
        //     case 3 -> {
        //         personagem.defender();
        //         System.out.println("Bot escolheu DEFENDER");
        //     }
        //     case 4 -> {
        //         personagem.usarPoderEspecial(jogadorInimigo.getPersonagem(), tabuleiro);
        //         System.out.println("Bot escolheu USAR PODER ESPECIAL");
        //     }
        //     default -> System.out.println("Ação inválida. Perdeu a vez.");
        // }

        System.out.println("Status do Bot pós ação: ");
        System.out.println("Vida: " + personagem.getPontosDeVida());
        System.out.println("Posição: " + personagem.getLinha() + ", " + personagem.getColuna());
    }

    private void moverEmDirecaoAoInimigo(Tabuleiro tabuleiro, Personagem inimigo) {
        int linhaInimigo = inimigo.getLinha();
        int colunaInimigo = inimigo.getColuna();
        int linhaAtual = personagem.getLinha();
        int colunaAtual = personagem.getColuna();

        if (linhaInimigo < linhaAtual) {
            personagem.mover("Cima", tabuleiro);
        } else if (linhaInimigo > linhaAtual) {
            personagem.mover("Baixo", tabuleiro);
        } else if (colunaInimigo < colunaAtual) {
            personagem.mover("Esquerda", tabuleiro);
        } else if (colunaInimigo > colunaAtual) {
            personagem.mover("Direita", tabuleiro);
        }
    }

    private boolean movimentoSeguro(String direcao, Tabuleiro tabuleiro) {
        int novaLinha = personagem.getLinha();
        int novaColuna = personagem.getColuna();

        switch (direcao.toLowerCase()) {
            case "cima" -> novaLinha--;
            case "baixo" -> novaLinha++;
            case "esquerda" -> novaColuna--;
            case "direita" -> novaColuna++;
        }

        return tabuleiro.estaDentroDosLimites(novaLinha, novaColuna);
    }
}
