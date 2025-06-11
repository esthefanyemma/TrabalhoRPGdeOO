package jogo.personagem;

import jogo.Tabuleiro;

public class Guerreiro extends Personagem {
    public Guerreiro(String nome) {
        this.nome = nome;
        this.forcaDeAtaque = 15;
        this.forcaDeDefesa = 10;
        this.alcanceDeAtaque = 1;
    }

    @Override
    public void usarPoderEspecial(Personagem inimigo, Tabuleiro tabuleiro) {
        if (poderEspecialUsado) {
            System.out.println(nome + " já usou o poder especial e não pode usá-lo novamente.");
            return;
        }
        
        // O poder especial do Guerreiro é aumentar sua força de ataque
        int alcanceOriginal = this.alcanceDeAtaque;
        this.alcanceDeAtaque = 3; // Aumenta o alcance de ataque para 3 temporariamente

        System.out.println(nome + " usa o poder especial!");
        System.out.println(nome + " agora tem um alcance de ataque de " + this.alcanceDeAtaque + ".");

        this.atacar(inimigo, tabuleiro);
        this.alcanceDeAtaque = alcanceOriginal;
        poderEspecialUsado = true; // Marca que o poder especial foi usado
    }
}
