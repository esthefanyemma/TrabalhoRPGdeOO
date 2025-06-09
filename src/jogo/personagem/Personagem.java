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

    public String getResumo() {
        return String.format("%s (%s) (Vida: %d | Ataque: %d | Defesa: %d | Alcance: %d)",
                nome, this.getClass().getSimpleName(), pontosDeVida, forcaDeAtaque, forcaDeDefesa, alcanceDeAtaque);
    }

    public int getLinha() {
        return linha;
    }

    public  int getColuna() {
        return coluna;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public String getNome() {
        return nome;
    }
}
