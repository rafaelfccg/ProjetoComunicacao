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
		Peca aux, aux2;
		int encaixe1 = -1;
		int encaixe2 = -1;
		boolean retorno =true;
		if(jogada == 2){
			aux = jogo.mesa.mesa.head;
			aux2 = jogo.mesa.mesa.tail;
			encaixe1 = aux.encaixe;
			encaixe2 = aux2.encaixe;
			if(encaixe1 == 1){
				encaixe1 = aux.num1;
			}else{
				encaixe1 = aux.num2;
			}
			if(encaixe2 == 1){
				encaixe2 = aux2.num1;
			}else{
				encaixe2 = aux2.num2;
			}//System.out.println("Encixes :" +encaixe1+ " e "+encaixe2);
			for(int i = 0; i<emMao.size(); i++){
				//System.out.println(emMao.get(i).num1 +" - "+ emMao.get(i).num2);
				retorno = retorno && !(emMao.get(i).num1 == encaixe1 || emMao.get(i).num1 == encaixe2 || emMao.get(i).num2 == encaixe2 || emMao.get(i).num2 == encaixe1);
				//System.out.println((emMao.get(i).num1 == encaixe1 || emMao.get(i).num1 == encaixe2 || emMao.get(i).num2 == encaixe2 || emMao.get(i).num2 == encaixe1));
			}
		}
		if(!retorno) return retorno;
		retorno = jogo.jogar(num, peca, jogada);
		//System.out.println(retorno);
		if (retorno && jogada != 2) {
			emMao.remove(index);
		}
		return retorno;
	}
	public String print_hand(){
		String retorno = "";
		for(int i = 0; i<emMao.size();i++){
			Peca aux = emMao.get(i);
			retorno += aux.num1+"/"+aux.num2+" ; ";
		}
		retorno +="\n";
		return retorno;
	}
	
}
