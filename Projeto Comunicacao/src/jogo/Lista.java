package jogo;

public class Lista {
	Peca head;
	Peca tail;
	Peca inicio;
	int size;
	
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
			++this.size;
		}
		else if (peca.identificador == 3) {//inserir atrás (parte inferior)
			peca.next = this.tail;
			tail.prev = peca;
			this.tail = peca;
			++this.size;
		}
	}	
}