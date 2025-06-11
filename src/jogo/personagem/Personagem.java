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

    public int getAlcanceDeAtaque() {
        return alcanceDeAtaque;
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

        if (tabuleiro.estaDentroDosLimites(novaLinha, novaColuna)) {
            if (!tabuleiro.posicaoOcupada(novaLinha, novaColuna)) {

                // Limpa a posição atual
                tabuleiro.getGrid()[linha][coluna] = null;

                tabuleiro.getGrid()[novaLinha][novaColuna] = this; // Move o personagem para a nova posição
                this.linha = novaLinha;
                this.coluna = novaColuna;

                System.out.println(nome + " se moveu para (" + novaLinha + ", " + novaColuna + ")");
            } else {
                System.out.println("Posição ocupada! Você perdeu a vez");
            }
        } else {
            System.out.println("Movimento fora dos limites do tabuleiro! Você perdeu a vez");
        }
    }

    public void atacar(Personagem inimigo, Tabuleiro tabuleiro) {
        System.out.println(nome + " está atacando " + inimigo.getNome() + "!");

        if (tabuleiro.calcularDistancia(this, inimigo) > alcanceDeAtaque) {
            System.out.println(inimigo.getNome() + " está fora do alcance de ataque!");
            return;
        }

        int dano = Math.max(0, forcaDeAtaque - inimigo.forcaDeDefesa);
        System.out.println(nome + " causa " + dano + " de dano a " + inimigo.getNome() + ".");
        inimigo.receberDano(dano);
    }

    public void defender() {
        System.out.println(nome + " está se defendendo!");
        
        if (this instanceof Mago) {
            this.forcaDeDefesa = 7; // Defesa do Mago
        } else if (this instanceof Arqueiro) {
            this.forcaDeDefesa = 5; // Defesa do Arqueiro
        } else if (this instanceof Guerreiro) {
            this.forcaDeDefesa = 10; // Defesa do Guerreiro
        }
        System.out.println(nome + " agora tem " + forcaDeDefesa + " de defesa.");
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

    protected boolean poderEspecialUsado = false;

    public boolean temPoderEspecial() {
        return !poderEspecialUsado;
    }

    public abstract void usarPoderEspecial(Personagem inimigo, Tabuleiro tabuleiro);

    public boolean poderEspecialUsado() {
        return poderEspecialUsado;
    }

    public void resetarPoderEspecial() {
        poderEspecialUsado = false;
    }
}

