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
        int temp = this.pontosDeVida;
        this.pontosDeVida = inimigo.getPontosDeVida();
        inimigo.setPontosDeVida(temp);

        poderEspecialUsado = true; // Marca que o poder especial foi usado
        System.out.println(nome + " usou o poder especial e trocou de vida com " + inimigo.getNome() + ".");
    }
}
