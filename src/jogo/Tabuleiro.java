package jogo;

import java.util.Random;
import jogo.personagem.Personagem;
public class Tabuleiro {
    private final int TAMANHO = 10;
    private Personagem[][] grid = new Personagem[TAMANHO][TAMANHO];

    public void posicionarAleatoriamente(Personagem p) {
        Random random = new Random();
        int linha, coluna;
        
        do { 
            linha = random.nextInt(TAMANHO);
            coluna = random.nextInt(TAMANHO);
        } while (grid[linha][coluna] != null);// Posiciona o personagem na grade

        grid[linha][coluna] = p;
        p.setLinha(linha);
        p.setColuna(coluna);
    }

    public void moverPersonagem(Personagem p, int novaLinha, int novaColuna) {
        if (estaDentroDosLimites(novaLinha, novaColuna) && grid[novaLinha][novaColuna] == null) {
            grid[p.getLinha()][p.getColuna()] = null; // Remove da posição antiga
            grid[novaLinha][novaColuna] = p; // Adiciona na nova posição
            p.setLinha(novaLinha);
            p.setColuna(novaColuna);
        } 
    }

    public boolean estaDentroDosLimites(int linha, int coluna) {
        return linha >= 0 && linha < TAMANHO && coluna >= 0 && coluna < TAMANHO;
    }

    public int calcularDistancia(Personagem p1, Personagem p2) {
        int dx = Math.abs(p1.getLinha() - p2.getLinha());
        int dy = Math.abs(p1.getColuna() - p2.getColuna());
        return Math.max(dx, dy);
    }

    public void imprimirTabuleiro() {
        System.out.println("\nTabuleiro:");

        System.out.print("    ");
        for (int col = 0; col < TAMANHO; col++) {
            System.out.printf(" %2d  ", col);
        }
        System.out.println();
        
        String horizontalLine = "   +" + "----+".repeat(TAMANHO);

        for (int i = 0; i < TAMANHO; i++) {
            System.out.println(horizontalLine);
            System.out.printf("%2d |", i);

            for (int j = 0; j < TAMANHO; j++) {
                if (grid[i][j] == null) {
                    System.out.print("    |");
                } else {
                    String iniciais = grid[i][j].getNome().substring(0, 1).toUpperCase();
                    System.out.printf(" %-2s |", iniciais);
                }
            }
            System.out.println();
        }
        System.out.println(horizontalLine);
    }

    public boolean posicaoOcupada(int linha, int coluna) {
        return grid[linha][coluna] != null;
    }
}
