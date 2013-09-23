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
	public String print_test(){
		return mesa.print_test();
	}
	public int getInicio_index(){
		return mesa.getIndex_inicio();
	}
	boolean jogar_cima(Peca p){
		if(mesa.inicio == null){
			p.identificador =1;
			p.encaixe=3;
			mesa.inserir(p);
			return true;
		}
		//System.out.println("chegou aqui");
			//if(mesa.head.identificador == 2 || mesa.head.identificador == 1){
				int aux = mesa.head.encaixe;
				//System.out.println(aux);
				//System.out.println(mesa.head.num1+"/"+mesa.head.num2);
				//System.out.println(p.num1+"/"+p.num2);
				if ((aux == 1||aux == 3) && mesa.head.num1 == p.num1) {
					p.encaixe = 2;
					p.identificador = 2;
					mesa.inserir(p);
					mesa.head.encaixe = 2;
					return true;
				}
				else if ((aux == 1||aux == 3) && mesa.head.num1 == p.num2) {
					p.encaixe = 1;
					p.identificador = 2;
					mesa.inserir(p);
					mesa.head.encaixe = 1;
					return true;
				}
				else if ((aux == 2||aux == 3) && mesa.head.num2 == p.num1) {
					p.encaixe = 2;
					p.identificador = 2;
					mesa.inserir(p);
					mesa.head.encaixe = 2;
					return true;
				}
				else if ((aux == 2 ||aux == 3)&& mesa.head.num2 == p.num2) {
					p.encaixe = 1;
					p.identificador = 2;
					mesa.inserir(p);
					mesa.head.encaixe = 1;
					return true;
				}				
		//	}
		return false;
	}
	
	boolean jogar_baixo(Peca p){
		if(mesa.inicio == null){
			p.identificador =1;
			mesa.inserir(p);
			p.encaixe = 3;
			return true;
		}
	//		if(mesa.tail.identificador == 3 || mesa.tail.identificador == 1){
				
				int aux = mesa.tail.encaixe;
				
				if ((aux == 1 ||aux == 3) && mesa.tail.num1 == p.num1) {
					p.encaixe = 2;
					p.identificador = 3;
					mesa.inserir(p);
					mesa.tail.encaixe = 2;
					return true;
				}
				else if((aux == 1 ||aux == 3) && mesa.tail.num1 == p.num2) {
					p.encaixe = 1;
					p.identificador = 3;
					mesa.inserir(p);
					mesa.tail.encaixe = 1;
					return true;
				}
				else if((aux == 2 ||aux == 3) && mesa.tail.num2 == p.num1) {
					p.encaixe = 2;
					p.identificador = 3;
					mesa.inserir(p);
					mesa.tail.encaixe = 2;
					return true;
				}
				else if((aux == 2 ||aux == 3)&& mesa.tail.num2 == p.num2) {
					p.encaixe = 1;
					p.identificador = 3;
					mesa.inserir(p);
					mesa.tail.encaixe = 1;
					return true;
				}				
		//	}
		return false;
	}
}