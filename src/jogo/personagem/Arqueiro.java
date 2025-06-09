package jogo.personagem;

import jogo.Tabuleiro;

public class Arqueiro extends Personagem {
    public Arqueiro(String nome) {
        this.nome = nome;
        this.forcaDeAtaque = 8;
        this.forcaDeDefesa = 5;
        this.alcanceDeAtaque = 5;
    }

    @Override
    public void usarPoderEspecial(Personagem inimigo, Tabuleiro tabuleiro) {
        this.alcanceDeAtaque++;
        System.out.println(nome + " usou o poder especial e aumentou seu alcance de ataque para " + alcanceDeAtaque + ".");
    }
}
