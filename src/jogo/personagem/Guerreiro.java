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
        this.alcanceDeAtaque = 30;
        System.out.println(nome + " usou o poder especial e aumentou seu alcance de ataque para " + alcanceDeAtaque + ".");
    }
}
