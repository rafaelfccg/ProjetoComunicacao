package jogo;

import java.io.Serializable;

public class Lista implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5021536319985479358L;
	Peca head;
	Peca tail;
	Peca inicio;
	int size;
	int index_inicio; // qnt de pecas em cima
	
	public int getIndex_inicio(){
		return index_inicio;
	}
	public Lista() {
		this.head = null;
		this.tail = null;
		this.inicio = null;
	}
	
	public void inserir(Peca peca) {
		if (this.inicio == null) { //lista vazia
			this.head = peca;
			this.tail = peca;
			this.inicio = peca;
			++this.size;
		}
		else if (peca.identificador == 2) { //inserir na frente (parte superior)
			peca.prev = head;
			this.head.next = peca;
			this.head = peca;
			++index_inicio;
			++this.size;
		}
		else if (peca.identificador == 3) {//inserir atr�s (parte inferior)
			peca.next = this.tail;
			tail.prev = peca;
			this.tail = peca;
			++this.size;
		}
	}
	public String print_test(){
		Peca aux = tail;
		String retorno= "";
		for(int i = 0; i<size; i++){
			retorno = retorno +(aux.num1+"/"+aux.num2);
			aux = aux.next;
		}
		return retorno;
	}
}