package jogo.personagem;

public class Arqueiro extends Personagem {
    public Arqueiro(String nome) {
        this.nome = nome;
        this.forcaDeAtaque = 8;
        this.forcaDeDefesa = 5;
        this.alcanceDeAtaque = 5;
    }

    @Override
    public void usarPoderEspecial() {
        System.out.println(nome + " usou o poder especial: Flecha Precisa!");
    }
}
