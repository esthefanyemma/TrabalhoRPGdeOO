package jogo.jogador;

import jogo.personagem.Personagem;

public class Bot extends Jogador {
    public Bot(Personagem personagem) {
        this.personagem = personagem;
    }
    
    @Override
    public void realizarAcao() {
        // Implementar ações automáticas do bot
    }
}
