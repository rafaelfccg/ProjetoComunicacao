package rede;
//Example 26 (updated)

import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

import jogo.Jogador;
import jogo.Jogo;

/*
 * A chat server that delivers public and private messages.
 */
public class MultiThreadChatServerSync {

	// The server socket.
	private static ServerSocket serverSocket = null;
	// The client socket.
	private static Socket clientSocket = null;

	// This chat server can accept up to maxClientsCount clients' connections.
	private static final int maxClientsCount = 10;
	private static final clientThread[] threads = new clientThread[maxClientsCount];
	private static int numClientes = 0;
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
		while (true) {
			try {
				clientSocket = serverSocket.accept();
				int i = 0;
				for (i = 0; i < maxClientsCount; i++) {
					if (threads[i] == null) {
						(threads[i] = new clientThread(clientSocket, threads)).start();
						numClientes++;
						break;
					}
				}
				if (i == maxClientsCount) {
					PrintStream os = new PrintStream(clientSocket.getOutputStream());
					os.println("Server too busy. Try later.");
					os.close();
					clientSocket.close();
				}
			} catch (IOException e) {
				System.out.println(e);
			}
		}
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

	private String clientName = null;
	private ObjectInputStream is = null;
	private ObjectOutputStream os = null;
	private Socket clientSocket = null;
	private final clientThread[] threads;
	private int maxClientsCount;
	Jogo jogo;
	Jogador[] jogadores = new Jogador[4];
	boolean comecou = false;
	public clientThread(Socket clientSocket, clientThread[] threads) {
		this.clientSocket = clientSocket;
		this.threads = threads;
		maxClientsCount = threads.length;
	}
	/*
	 * Le toda rodada as pecas dele e as da mesa
	 * Pegar o IP dos jogadores que entrarem
	 * Trocar o nome da String pra concatenar os IPs
	 */
	public boolean isFull(){
		for(int i = 0;i<4;++i){
			if(jogadores[i]==null)return false;
		}
		return true;
	}
	public void run() {
		int maxClientsCount = this.maxClientsCount;
		clientThread[] threads = this.threads;

		try {
			/*
			 * Create input and output streams for this client.
			 */
			is = new ObjectInputStream(clientSocket.getInputStream());
			os = new ObjectOutputStream(clientSocket.getOutputStream());
			String name = "oi";
			/*while (true) {
				os.writeObject("Enter your name.");
				name = (String) is.readObject();
				if (name.indexOf('@') == -1) {
					break;
				} else {
					os.writeObject("The name should not contain '@' character.");
				}
			}*/

			/* Welcome the new the client. */
			/*os.println("Welcome " + name
					+ " to our chat room.\nTo leave enter /quit in a new line.");*/
			/*synchronized (this) {
				for (int i = 0; i < maxClientsCount; i++) {
					if (threads[i] != null && threads[i] == this) {
						clientName = "@" + name;
						break;
					}
				}
				for (int i = 0; i < maxClientsCount; i++) {
					if (threads[i] != null && threads[i] != this) {
						threads[i].os.println("*** A new user " + name
								+ " entered the chat room !!! ***");
					}
				}
			}*/
			/* Start the conversation. */
			while (true) {
				Object o = is.readObject();
				if(o instanceof Jogo){
					this.jogo = (Jogo) o;
				}
				else if(o instanceof Jogador){
					Object ob = is.readObject();
					this.jogadores[(Integer)ob] = (Jogador) o;
				}
				else if(o instanceof String){
					if(o.equals("sair"))break;
				}
				if(isFull() && !comecou){
					comecou = true;
					jogo = new Jogo(jogadores);
				}
				/*if (line.startsWith("/quit")) {
					break;
				}*/
				//System.out.println(name+" mandou:" + line);
				/* If the message is private sent it to the given client. */
				/*if (line.startsWith("@")) {
					String[] words = line.split("\\s", 2);
					if (words.length > 1 && words[1] != null) {
						words[1] = words[1].trim();
						if (!words[1].isEmpty()) {
							synchronized (this) {
								for (int i = 0; i < maxClientsCount; i++) {
									if (threads[i] != null && threads[i] != this
											&& threads[i].clientName != null
											&& threads[i].clientName.equals(words[0])) {
										threads[i].os.println("<" + name + "> " + words[1]);*/
										/*
										 * Echo this message to let the client know the private
										 * message was sent.
										 */
										/*this.os.println(">" + name + "> " + words[1]);
										break;
									}
								}
							}
						}
					}
				} else {*/
					/* The message is public, broadcast it to all other clients. */
					synchronized (this) {
						for (int i = 0; i < maxClientsCount && isFull(); i++) {
							if (threads[i] != null && threads[i].clientName != null) {
								threads[i].os.writeObject(this.jogo);
							}
						}
					}
				//}
			}
			/*synchronized (this) {
				for (int i = 0; i < maxClientsCount; i++) {
					if (threads[i] != null && threads[i] != this
							&& threads[i].clientName != null) {
						threads[i].os.println("*** The user " + name
								+ " is leaving the chat room !!! ***");
					}
				}
			}
			os.println("*** Bye " + name + " ***");*/

			/*
			 * Clean up. Set the current thread variable to null so that a new client
			 * could be accepted by the server.
			 */
			synchronized (this) {
				for (int i = 0; i < maxClientsCount; i++) {
					if (threads[i] == this) {
						threads[i] = null;
					}
				}
			}
			/*
			 * Close the output stream, close the input stream, close the socket.
			 */
			is.close();
			os.close();
			clientSocket.close();
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}