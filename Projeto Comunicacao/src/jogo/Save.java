package jogo;

import java.io.Serializable;
import java.util.Vector;

public class Save implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 360829878223554083L;
	Jogador[] jogador;
	Mesa mesa;
	int vez;
	Vector<Peca> pecas;
	
	public Save(Jogador[] jogador, Mesa mesa, int vez, Vector<Peca> pecas) {
		this.jogador = jogador;
		this.mesa = mesa;
		this.vez = vez;
		this.pecas = pecas;
	}
	
	
}
