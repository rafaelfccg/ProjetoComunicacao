package jogo;

import java.io.Serializable;
import java.util.Vector;

public class Jogador implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3965415346455078693L;
	Vector<Peca> emMao;
	private int num;
	Jogo jogo;
	
	Jogador(int num){
		this.num = num;
	}
	boolean joga(int index, int jogada){ //cima: 0 = cima, 1 = baixo, 2 == toque
		Peca peca = emMao.get(index);
		boolean retorno = jogo.jogar(num, peca, jogada);
		if (retorno) {
			emMao.remove(index);
		}
		return retorno;
	}
	
}
