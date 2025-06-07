package jogo.personagem;

public class Guerreiro extends Personagem {
    public Guerreiro(String nome) {
        this.nome = nome;
        this.forcaDeAtaque = 15;
        this.forcaDeDefesa = 10;
        this.alcanceDeAtaque = 1;
    }

    @Override
    public void usarPoderEspecial() {
        System.out.println(nome + " usou o poder especial: Carga Brutal!");
    }
}
