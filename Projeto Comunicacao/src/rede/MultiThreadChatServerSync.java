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
	public static void main(String args[]) {

		// The default port number.
		int portNumber = 2222;
		if (args.length < 1) {
			System.out.println("Usage: java MultiThreadChatServerSync <portNumber>\n"
					+ "Now using port number=" + portNumber);
		} else {
			portNumber = Integer.valueOf(args[0]).intValue();
		}

		/*
		 * Open a server socket on the portNumber (default 2222). Note that we can
		 * not choose a port less than 1023 if we are not privileged users (root).
		 */
		try {
			serverSocket = new ServerSocket(portNumber);
		} catch (IOException e) {
			System.out.println(e);
		}

		/*
		 * Create a client socket for each connection and pass it to a new client
		 * thread.
		 */
		jogadores[0] = new Jogador(0);
		jogadores[1] = new Jogador(1);
		jogadores[2] = new Jogador(2);
		jogadores[3] = new Jogador(3);
		try {
			jogo = new Jogo(jogadores);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		boolean full = false;
		while (true) {
			try {
				
				clientSocket = serverSocket.accept();
				if(numClientes < maxClientsCount){
					clientes[numClientes] = new ThreadSocket(clientSocket);
					++numClientes;
					if(numClientes == maxClientsCount) full = true;
				}
				if(full){
					System.out.println("Comecar o jogo");
					threads = new clientThread(clientes, jogo);
					threads.start();
				}
				/*if (numClientes == maxClientsCount) {
					PrintStream os = new PrintStream(clientSocket.getOutputStream());
					os.println("Server too busy. Try later.");
					os.close();
					clientSocket.close();
				}*/
			} catch (IOException e) {
				System.out.println(e);
			}
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
	boolean comecou;
	Jogo jogo;
	Jogador[] jogadores;
	public clientThread(ThreadSocket[] clientes, Jogo jogo) {
		this.clientes = clientes;
		this.jogo = jogo;
		this.comecou = false;
	}
	/*
	 * Le toda rodada as pecas dele e as da mesa
	 * Pegar o IP dos jogadores que entrarem
	 * Trocar o nome da String pra concatenar os IPs
	 */
	public void run() {
		int maxClientsCount = this.maxClientsCount;
		
		try {	

			int vez = jogo.getVez();
			
			//String mesa = jogo.getMesa().print_test();// impressao de test
			//synchronized (this) {
			System.out.println(vez);
			for(int i = 0; i < 4; ++i){
				clientes[i].os.writeInt(i);
				//clientes[i].os.flush();
			}				
			System.out.println(vez);
			while(jogo.verify() != 2){
				jogo.save();
				vez = jogo.getVez();
				for (int i = 0; i < 4; ++i) {
					clientes[i].os.writeObject(jogo);
				}
				jogo = (Jogo) clientes[vez].is.readObject();
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