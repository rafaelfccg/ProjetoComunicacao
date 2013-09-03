package jogo;

import java.util.Vector;
import java.util.Collections;

class Mesa{
	Vector<Peca> em_mesa;
	Mesa(){
		em_mesa = new Vector<Peca>();
	}
	boolean jogar_cima(Peca p){
		for(int i = em_mesa.size()-1; i>=0; i--){
			if(em_mesa.get(i).identificador == 2){
				int aux = em_mesa.get(i).encaixe;
				if(aux == 1 && em_mesa.get(i).num1 == p.num1){
					p.encaixe = 2;
					p.identificador = 2;
					em_mesa.add(p);
					return true;
				}else if(aux == 1 && em_mesa.get(i).num1 == p.num2){
					p.encaixe = 1;
					p.identificador = 2;
					em_mesa.add(p);
					return true;
				}else if(aux == 2 && em_mesa.get(i).num2 == p.num1){
					p.encaixe = 2;
					p.identificador = 2;
					em_mesa.add(p);
					return true;
				}else if(aux == 2 && em_mesa.get(i).num2 == p.num2){
					p.encaixe = 1;
					p.identificador = 2;
					em_mesa.add(p);
					return true;
				}
				
			}
		}
		return false;
	}
	boolean jogar_baixo(Peca p){
		for(int i = em_mesa.size()-1; i>=0; i--){
			if(em_mesa.get(i).identificador == 3){
				int aux = em_mesa.get(i).encaixe;
				if(aux == 1 && em_mesa.get(i).num1 == p.num1){
					p.encaixe = 2;
					p.identificador = 3;
					em_mesa.add(p);
					return true;
				}else if(aux == 1 && em_mesa.get(i).num1 == p.num2){
					p.encaixe = 1;
					p.identificador = 3;
					em_mesa.add(p);
					return true;
				}else if(aux == 2 && em_mesa.get(i).num2 == p.num1){
					p.encaixe = 2;
					p.identificador = 3;
					em_mesa.add(p);
					return true;
				}else if(aux == 2 && em_mesa.get(i).num2 == p.num2){
					p.encaixe = 1;
					p.identificador = 3;
					em_mesa.add(p);
					return true;
				}
				
			}
		}
		return false;
	}
}
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
