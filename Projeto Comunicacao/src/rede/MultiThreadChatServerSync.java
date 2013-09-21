package rede;
//Example 26 (updated)

import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.net.Socket;
import java.net.ServerSocket;

import jogo.Jogador;
import jogo.Jogo;

/*
 * A chat server that delivers public and private messages.
 */
public class MultiThreadChatServerSync {

	private static ServerSocket serverSocket = null;
	private static Socket clientSocket = null;

	// This chat server can accept up to maxClientsCount clients' connections.
	private static final int maxClientsCount = 4;
	private static clientThread threads;
	private static int numClientes = 0;
	private static ThreadSocket[] clientes = new ThreadSocket[maxClientsCount];
	
	static Jogo jogo;
	static Jogador[] jogadores = new Jogador[4];
	
	public MultiThreadChatServerSync() {
		int portNumber = 2222;
		
		try {
			serverSocket = new ServerSocket(portNumber);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	static void esperarConexao() {
		boolean full = false;
		boolean conexao_aceita = false;
		while (true) {
			try {				
				clientSocket = serverSocket.accept();
				if(numClientes>=maxClientsCount){
					clientSocket.close();
				}else if(numClientes < maxClientsCount){					
					clientes[numClientes] = new ThreadSocket(clientSocket);
					conexao_aceita = novoJogador(clientes[numClientes]);
					
					//cria jogador, espera restante conectar, gui = tela de espera
					if(conexao_aceita){
						++numClientes;
						
					}
					if(numClientes == maxClientsCount) full = true;
				}
				if(full){
					System.out.println("Comecar o jogo");
					threads = new clientThread(clientes, jogo);
					//gui = mesa
					//threads.start();
					//FAZER START NA GUI
					break;
				}
				
			} catch (IOException e) {
				System.out.println(e);
			} catch (ClassNotFoundException e) {
				System.out.println(e);
			}
			conexao_aceita = false;
		}
	}
	
	static boolean novoJogador(ThreadSocket cliente) throws ClassNotFoundException, IOException {
		String nome = (String) cliente.is.readObject();
		int time = cliente.is.readInt();
		if(time == 1) {
			if (jogadores[0] == null) {
				jogadores[0] = new Jogador(0, nome);
				cliente.os.writeBoolean(true);
				cliente.os.writeInt(0);
				return true;
			}
			else if (jogadores[2] == null){
				jogadores[2] = new Jogador(2, nome);
				cliente.os.writeBoolean(true);
				cliente.os.writeInt(2);
				return true;
			}
			else {//time cheio
				cliente.os.writeBoolean(false);
				return false;
			}
		}
		else if (time == 2){
			if (jogadores[1] == null) {
				jogadores[1] = new Jogador(1, nome);
				cliente.os.writeBoolean(true);
				cliente.os.writeInt(1);
				return true;
			}
			else if (jogadores[3] == null) {
				jogadores[3] = new Jogador(3, nome);
				cliente.os.writeBoolean(true);
				cliente.os.writeInt(3);
				return true;
			}
			else {//time cheio
				return false;
			}
		}
		else {
			return false;
		}		
	}	
	
}

class ThreadSocket extends Thread {
	private Socket sock;
	public ObjectInputStream is;
	public ObjectOutputStream os;
	
	public ThreadSocket(Socket sock) throws IOException {
		this.sock = sock;
		is = new ObjectInputStream(sock.getInputStream());
		os = new ObjectOutputStream(sock.getOutputStream());
	}
}

/*
 * The chat client thread. This client thread opens the input and the output
 * streams for a particular client, ask the client's name, informs all the
 * clients connected to the server about the fact that a new client has joined
 * the chat room, and as long as it receive data, echos that data back to all
 * other clients. The thread broadcast the incoming messages to all clients and
 * routes the private message to the particular client. When a client leaves the
 * chat room this thread informs also all the clients about that and terminates.
 */
class clientThread extends Thread {

	private Socket clientSocket;
	private final ThreadSocket[] clientes;
	private final int maxClientsCount = 10;
	static boolean online;
	boolean comecou;
	Jogo jogo;
	Pacote pck;
	Jogador[] jogadores;
	public clientThread(ThreadSocket[] clientes, Jogo jogo) {
		this.clientes = clientes;
		this.jogo = jogo;
		this.comecou = false;
		online = true;
	}
	/*
	 * Le toda rodada as pecas dele e as da mesa
	 * Pegar o IP dos jogadores que entrarem
	 * Trocar o nome da String pra concatenar os IPs
	 */
	public void run() {
		int maxClientsCount = this.maxClientsCount;
		pck = new Pacote(jogo,online);
		try {	
			int vez = jogo.getVez();
			
			//String mesa = jogo.getMesa().print_test();// impressao de test
			//synchronized (this) {
			
			while(jogo.verify() != 2){
				jogo.save();
				vez = jogo.getVez();
				for (int i = 0; i < 4; ++i) {
					clientes[i].os.writeObject(pck);
				}
				pck = (Pacote) clientes[vez].is.readObject();
				jogo =  pck.jogo;
				System.out.println("Li o jogo");
				System.out.println("Vez de: "+jogo.getVez());
			}			
		
			clientSocket.close();
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}