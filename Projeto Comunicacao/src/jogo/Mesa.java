package jogo;

import java.io.Serializable;

public class Mesa implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8590931278202855378L;
	Lista mesa;
	
	public Mesa(){
		mesa = new Lista();
	}
	
	boolean jogar_cima(Peca p){
			if(mesa.head.identificador == 2){
				int aux = mesa.head.encaixe;
				if (aux == 1 && mesa.head.num1 == p.num1) {
					p.encaixe = 2;
					p.identificador = 2;
					mesa.inserir(p);
					return true;
				}
				else if (aux == 1 && mesa.head.num1 == p.num2) {
					p.encaixe = 1;
					p.identificador = 2;
					mesa.inserir(p);
					return true;
				}
				else if (aux == 2 && mesa.head.num2 == p.num1) {
					p.encaixe = 2;
					p.identificador = 2;
					mesa.inserir(p);
					return true;
				}
				else if (aux == 2 && mesa.head.num2 == p.num2) {
					p.encaixe = 1;
					p.identificador = 2;
					mesa.inserir(p);
				}				
			}
		return false;
	}
	
	boolean jogar_baixo(Peca p){
		
			if(mesa.tail.identificador == 3){
				
				int aux = mesa.tail.encaixe;
				
				if (aux == 1 && mesa.tail.num1 == p.num1) {
					p.encaixe = 2;
					p.identificador = 3;
					mesa.inserir(p);
					return true;
				}
				else if(aux == 1 && mesa.tail.num1 == p.num2) {
					p.encaixe = 1;
					p.identificador = 3;
					mesa.inserir(p);
					return true;
				}
				else if(aux == 2 && mesa.tail.num2 == p.num1) {
					p.encaixe = 2;
					p.identificador = 3;
					mesa.inserir(p);
					return true;
				}
				else if(aux == 2 && mesa.tail.num2 == p.num2) {
					p.encaixe = 1;
					p.identificador = 3;
					mesa.inserir(p);
					return true;
				}				
			}
		return false;
	}
}