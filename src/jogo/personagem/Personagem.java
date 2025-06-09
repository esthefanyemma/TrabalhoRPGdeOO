package jogo.personagem;

import jogo.Tabuleiro;

public abstract class Personagem {
    protected String nome;
    protected int pontosDeVida = 100;
    protected int forcaDeAtaque;
    protected int forcaDeDefesa;
    protected int alcanceDeAtaque;
    protected int linha;
    protected int coluna;

    public abstract void usarPoderEspecial(Personagem inimigo, Tabuleiro tabuleiro);

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

    public int getPontosDeVida() {
        return pontosDeVida;
    }

    public void setPontosDeVida(int pontosDeVida) {
        this.pontosDeVida = pontosDeVida;
    }

    public void mover(String direcao, Tabuleiro tabuleiro) {
        System.out.println(nome + " está se movendo para " + direcao);
        int novaLinha = linha;
        int novaColuna = coluna;

        switch (direcao.toLowerCase()) {
            case "cima" -> novaLinha--;
            case "baixo" -> novaLinha++;
            case "esquerda" -> novaColuna--;
            case "direita" -> novaColuna++;
            default -> {
                System.out.println("Direção inválida. Use 'Cima', 'Baixo', 'Esquerda' ou 'Direita'.");
                return;
            }
        }

        System.out.println("Tentando mover para (" + novaLinha + ", " + novaColuna + ")");

        if (tabuleiro.estaDentroDosLimites(novaLinha, novaColuna)
            && !tabuleiro.posicaoOcupada(novaLinha, novaColuna)) {
        tabuleiro.moverPersonagem(this, novaLinha, novaColuna);
        System.out.println("Movimentado para (" + novaLinha + ", " + novaColuna + ") com sucesso!");
        } else {
        System.out.println("Movimento inválido.");
        }
    }

    public void atacar(Personagem inimigo, Tabuleiro tabuleiro) {
        int distancia = tabuleiro.calcularDistancia(this, inimigo);

        if (distancia > alcanceDeAtaque) {
            System.out.println("Inimigo fora de alcance! Você perdeu a vez");
            return;
        }

        int dano = Math.max(0, forcaDeAtaque - inimigo.forcaDeDefesa);
        inimigo.receberDano(dano);
        System.out.println(nome + " atacou " + inimigo.nome + " causando " + dano + " de dano!");
    }

    public void defender() {
        System.out.println(nome + " está se defendendo! Defesa restaurada.");
        restaurarDefesa();
    }

    public void restaurarDefesa() {
        // Reseta para o valor original da subclasse
        if (this instanceof Mago) this.forcaDeDefesa = 7;
        else if (this instanceof Arqueiro) this.forcaDeDefesa = 5;
        else if (this instanceof Guerreiro) this.forcaDeDefesa = 10;
    }

    public void receberDano(int dano) {
        int danoFinal = Math.max(dano, 0);
        this.pontosDeVida -= danoFinal;
        System.out.println(nome + " recebeu " + danoFinal + " de dano! Vida restante: " + pontosDeVida);
    }
}

