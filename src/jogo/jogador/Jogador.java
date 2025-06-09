package jogo.jogador;

import jogo.Tabuleiro;
import jogo.personagem.Personagem;

public abstract class Jogador {
    protected Personagem personagem;
    public abstract void realizarAcao(Tabuleiro tabuleiro, Jogador jogadorInimigo);

    public Personagem getPersonagem() {
        return personagem;
    }
}
