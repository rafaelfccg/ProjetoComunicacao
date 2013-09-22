package UDP;

import jogo.*;
import rede.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import jogo.Jogo;

public class ClienteUDP {
	
	InetAddress clientIP;
	InetAddress serverIP;
	DatagramSocket clientSocket;
	WriteUDP w;
	ReadUDP r;
	static int numJogador;
	static Jogo jogo;
	static final int clientPort = 2222;
	DadosCliente dados;
	
	
	public ClienteUDP(String serverIP, String clientIP, String nome, int time) throws IOException, ClassNotFoundException, TimeCheioException {
		this.clientSocket = new DatagramSocket(clientPort);
		this.serverIP = InetAddress.getByName(serverIP);
		this.clientIP = InetAddress.getByName(clientIP);
		WriteUDP w = new WriteUDP(clientSocket, this.serverIP, clientPort);
		ReadUDP r = new ReadUDP(clientSocket);
		this.dados = new DadosCliente(clientPort, clientSocket, this.clientIP, nome, time);		
		w.enviarDatagrama(this.dados);
		boolean conectado = (boolean) r.lerDatagrama();
		if (conectado) {
			numJogador = (Integer) r.lerDatagrama();
		}
		else {
			throw new TimeCheioException();
		}
	}
	
	void fecha() {
		clientSocket.close();
	}
	
	public boolean jogar(int index, int jogada) throws IOException {
		boolean b = false;
		if (jogo.getVez() == numJogador) {
			b = jogo.getJogador(numJogador).joga(index, jogada);
		}
		if (b) {
			w.enviarDatagrama(jogo);
		}		
		return b;
	}
	

	private void run() {
		while(jogo.verify() != 2) {
			try {
				jogo = (Jogo) r.lerDatagrama();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	}
	
}














