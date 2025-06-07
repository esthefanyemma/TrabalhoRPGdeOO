package jogo.personagem;

public class Mago extends Personagem {
    public Mago(String nome) {
        this.nome = nome;
        this.forcaDeAtaque = 10;
        this.forcaDeDefesa = 7;
        this.alcanceDeAtaque = 3;
    }

    @Override
    public void usarPoderEspecial() {
        System.out.println(nome + " usou o poder especial: Trocar vida!");
    }
}
