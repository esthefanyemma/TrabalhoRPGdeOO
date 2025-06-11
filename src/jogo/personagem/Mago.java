package jogo.personagem;

import jogo.Tabuleiro;

public class Mago extends Personagem {
    public Mago(String nome) {
        this.nome = nome;
        this.forcaDeAtaque = 10;
        this.forcaDeDefesa = 7;
        this.alcanceDeAtaque = 3;
    }

    @Override
    public void usarPoderEspecial(Personagem inimigo, Tabuleiro tabuleiro) {
        if (poderEspecialUsado) {
            System.out.println(nome + " já usou o poder especial e não pode usá-lo novamente.");
            return;
        }

        int vidaAtual = this.pontosDeVida;
        int vidaInimigo = inimigo.getPontosDeVida();

        System.out.println(nome + " usa o poder especial!");

        this.pontosDeVida = vidaInimigo;
        inimigo.setPontosDeVida(vidaAtual);

        System.out.println("Vida de " + nome + " agora é " + this.pontosDeVida);
        System.out.println("Vida de " + inimigo.getNome() + " agora é " + inimigo.getPontosDeVida());

        poderEspecialUsado = true; // Marca que o poder especial foi usado
    }
}
