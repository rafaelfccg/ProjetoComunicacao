package jogo;
import java.util.Vector;
import java.util.Collections;

public class Jogo {
	Vector<Peca> pecas;
	Jogador[] jogador;
	Mesa mesa;
	int vez;
	
	Jogo(Jogador[] jogadores){
		jogador =jogadores;
		int maiorCarroca =0;
		pecas = new Vector<Peca>();
		for(int i = 0; i<7;i++){
			for(int j = i;j<7;j++){
				Peca peca =new Peca(i,j);
				pecas.add(peca);
			}
		}
		
		Collections.shuffle(pecas);
		int start=0;
		
		for(int joga=0; joga<4;joga++){
			start = joga*6;
			Vector<Peca> mao =new Vector<Peca>();
			for(int i = 0;i<6;i++){
				mao.add(pecas.get(i+start));
				if(pecas.get(i+start).num1 >= maiorCarroca && pecas.get(i+start).num1 == pecas.get(i+start).num2){
					vez = joga;
					maiorCarroca =pecas.get(i+start).num1; 
				}
			}
			jogador[joga].emMao = mao;
		}		
	}
	
	boolean jogada(int player, Peca peca, boolean cima){
		if(player!=vez) return false;
		else{
			if(cima)mesa.jogar_cima(peca);
			else mesa.jogar_baixo(peca);
		}
		vez++;
		vez%=4;
		return true;
	}	
}
