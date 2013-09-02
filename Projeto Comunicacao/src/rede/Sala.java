package rede;

import jogo.*;

public class Sala {
	String[] clientesIP;
	//Servidor servior;
	Jogo jogo;
	int aux;
	
	Sala(){
		clientesIP = new String[4];
		aux = 0;
	}
	
	void getCliente(String ip){
		clientesIP[aux] = ip;
		aux++;
	}
	void kickCliente(int index){
		clientesIP[index] = null;
		for(int i = index ; i < 3;i++){
			clientesIP[i] = clientesIP[i+1];
			
		}
	}
	
	
}
