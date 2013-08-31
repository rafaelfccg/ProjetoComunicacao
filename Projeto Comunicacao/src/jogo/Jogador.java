package jogo;

import java.util.Vector;

public class Jogador {
	Vector<Peca> emMao;
	private int num;
	Jogo jogo;
	
	Jogador(int num){
		this.num = num;
	}
	boolean joga(int index, boolean cima){
		Peca peca = emMao.get(index);
		boolean retorno = jogo.jogada(num, peca, cima);
		if(retorno) emMao.remove(index);
		return retorno;
	}
	
}
