package UDP;

import rede.*;
import jogo.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import jogo.Jogador;
import jogo.Jogo;

public class ServidorUDP {

	private static final int serverPort = 2222;
	DatagramSocket serverSocket;
	InetAddress ip;
	WriteUDP w;
	ReadUDP r;
	static Jogador[] jogadores = new Jogador[4];
	ClientThread[] clientes;
	static Jogo jogo;

	public ServidorUDP() throws IOException {
		this.serverSocket = new DatagramSocket(serverPort);
		this.r = new ReadUDP(serverSocket);
		clientes = new ClientThread[4];
	}
	
	private boolean novoJogador(ClientThread cliente) throws IOException {
		 if (cliente.time == 1) {
			 if (jogadores[0] == null) {
				 jogadores[0] = new Jogador(0, cliente.nome);
				 cliente.w.enviarDatagrama(true);
				 cliente.w.enviarDatagrama(0);
				 return true;
			 }
			 else if (jogadores[2] == null) {
				 jogadores[2] = new Jogador(2, cliente.nome);
				 cliente.w.enviarDatagrama(true);
				 cliente.w.enviarDatagrama(2);
				 return true;
			 }
			 else {
				 return false;
			 }
		 }
		 else if (cliente.time == 2) {
			 if (jogadores[1] == null) {
				 jogadores[1] = new Jogador(1, cliente.nome);
				 cliente.w.enviarDatagrama(true);
				 cliente.w.enviarDatagrama(1);
				 return true;
			 }
			 else if (jogadores[3] == null) {
				 jogadores[3] = new Jogador(3, cliente.nome);
				 cliente.w.enviarDatagrama(true);
				 cliente.w.enviarDatagrama(3);
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

	private void esperarConexao() throws ClassNotFoundException, IOException {
		boolean full = false;
		int numeroConexoes = 0;
		boolean jogadorValido;
		while (full != false) {			
			jogadorValido = false;
			while (!jogadorValido) {
				DadosCliente cliente = (DadosCliente) r.lerDatagrama();				
				clientes[numeroConexoes] = new ClientThread(cliente.socket, cliente.ip, cliente.port, cliente.nome, cliente.time);		
				jogadorValido = novoJogador(clientes[numeroConexoes]);
			}
			++numeroConexoes;		
			if (numeroConexoes == 4) {
				full = true;
			}
		}
	}

	private void run() {
		try {
			esperarConexao();
			int vez;
			jogo = new Jogo(jogadores);
			
			while(jogo.verify() != 2) {
				jogo.save();
				vez = jogo.getVez();
				for (int i = 0; i < 4; ++i) {
					clientes[i].w.enviarDatagrama(jogo);
				}
				jogo = (Jogo) r.lerDatagrama();				
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

class ClientThread {
	WriteUDP w;
	String nome;
	int time;

	public ClientThread(DatagramSocket socket, InetAddress ip, int port, String nome, int time) throws IOException {
		this.w = new WriteUDP(socket, ip, port);
		this.nome = nome;
		this.time = time;
	}
}





















