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
	
	public Jogador(int num){
		this.num = num;
	}
	public Vector<Peca> getEmMao() {
		return emMao;
	}
	public void setEmMao(Vector<Peca> emMao) {
		this.emMao = emMao;
	}
	public boolean joga(int index, int jogada){ //cima: 0 = cima, 1 = baixo, 2 == toque
		Peca peca = emMao.get(index);
		boolean retorno = jogo.jogar(num, peca, jogada);
		if (retorno) {
			emMao.remove(index);
		}
		return retorno;
	}
	public void print_hand(){
		for(int i = 0; i<emMao.size();i++){
			Peca aux = emMao.get(i);
			System.out.print(aux.num1+"/"+aux.num2+" ; ");
		}
		System.out.println();
	}
	
}
