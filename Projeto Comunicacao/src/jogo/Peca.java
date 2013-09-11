package jogo;

import java.io.Serializable;

public class Peca implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1247565935740083866L;
	Peca prev;
	Peca next;
	int num1;
	int num2;
	int encaixe;// 1 se estiver em mesa com o num1 aberto, 2se estiver em mesa com num2 aberto, 3 se for a peca inicial e tiver os dois abertos
	int identificador;// 0-Na mão de um jogador, 1- primeira peca ,2- Jogada na parte superior , 3 - jogada na parte inferior, 4 - fora do jogo
	
	Peca(int num1, int num2){
		this.num1 = num1;
		this.num2 = num2;
		this.encaixe = -1;
	}

	public int getNum1() {
		return num1;
	}

	public void setNum1(int num1) {
		this.num1 = num1;
	}

	public int getNum2() {
		return num2;
	}

	public void setNum2(int num2) {
		this.num2 = num2;
	}
	
}
