package rede;

import java.io.IOException;

import jogo.*;

public class Sala extends Thread{
	Jogador[] jogadores;
	Jogo jogo;
	int id;
	int numJogadores;
	
	
	public Sala(int id) throws IOException{
		this.id = id;
		jogadores = new Jogador[4];
		this.numJogadores = 0;
	}
	
	public boolean salaCheia() {
		if (numJogadores > 3) {
			return false;
		}
		return true;
	}
	
	public boolean inserirJogador(int time, String nome) {
			if(time == 1) {
				if (jogadores[0] == null) {
					jogadores[0] = new Jogador(0,nome);
					++numJogadores;
					return true;
				}
				else if (jogadores[2] == null){
					jogadores[2] = new Jogador(2,nome);
					++numJogadores;
					return true;
				}
				else {
					return false;
				}
			}
			else if (time == 2){
				if (jogadores[1] == null) {
					jogadores[1] = new Jogador(1,nome);
					++numJogadores;
					return true;
				}
				else if (jogadores[3] == null) {
					jogadores[3] = new Jogador(3,nome);
					++numJogadores;
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
	}
	
	
}
