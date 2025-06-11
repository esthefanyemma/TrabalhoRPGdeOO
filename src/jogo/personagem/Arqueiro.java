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
        if (poderEspecialUsado) {
            System.out.println(nome + " já usou o poder especial e não pode usá-lo novamente.");
            return;
        }

        // Flecha precisa (ignora defesa)
        int defesaOriginal = inimigo.forcaDeDefesa;
        inimigo.forcaDeDefesa = 0;

        System.out.println(nome + " usa o poder especial!");
        System.out.println(nome + " ataca " + inimigo.getNome() + " com uma flecha precisa!");

        this.atacar(inimigo, tabuleiro);
        inimigo.forcaDeDefesa = defesaOriginal; // Restaura a defesa original do inimigo
        poderEspecialUsado = true; // Marca que o poder especial foi usado
    }
}
