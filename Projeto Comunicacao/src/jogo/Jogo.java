package jogo;

import java.util.Vector;

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
	
	
	
	
}
