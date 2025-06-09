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
        int temp = this.pontosDeVida;
        this.pontosDeVida = inimigo.getPontosDeVida();
        inimigo.setPontosDeVida(temp);
        System.out.println(nome + " usou o poder especial e trocou de vida com " + inimigo.getNome() + ".");
    }
}
