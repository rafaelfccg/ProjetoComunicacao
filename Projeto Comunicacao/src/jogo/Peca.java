package jogo;

public class Peca {
	int num1;
	int num2;
	int encaixe;// 1 se estiver em mesa com o num1 aberto, 2se estiver em mesa com num2 aberto
	int identificador;// 0-Na mão de um jogador, 1- primeira peca ,2- Jogada na parte superior , 3 - jogada na parte inferior, 4 - fora do jogo
	
	Peca(int num1, int num2){
		this.num1 = num1;
		this.num2 = num2;
		this.encaixe = -1;
	}

}
