package jogo.personagem;

public abstract class Personagem {
    protected String nome;
    protected int pontosDeVida = 100;
    protected int forcaDeAtaque;
    protected int forcaDeDefesa;
    protected int alcanceDeAtaque;
    protected int linha;
    protected int coluna;

    public abstract void usarPoderEspecial();

    public void receberDano(int dano) {
        this.pontosDeVida -= Math.max(dano, 0);
    }

    // Getters e setters podem ser adicionados aqui
}
