package rede;
//Example 25

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import jogo.*;
/*
 * 
 * */
public class MultiThreadChatClient implements Runnable {

	// The client socket
	private static Socket clientSocket = null;
	// The output stream
	private static ObjectOutputStream os = null;
	// The input stream
	private static ObjectInputStream is = null;

	//private static BufferedReader inputLine = null;
	private static boolean closed = false;
	
	static Jogador jogador;
	static Jogo jogo;
	static int vez;
	static boolean primeiro = true;
	static boolean sair = false;
	
	/*
	 * Le toda rodada as pecas dele e as da mesa
	 * inteiro pra vez (mod 4)
	 */
	public static void main(String[] args) {

		// The default port.
		int portNumber = 2222;
		// The default host.
		String host = "localhost";
		Scanner in = new Scanner(System.in);
		System.out.println("Digite o seu id:");
		vez = in.nextInt();
		jogador = new Jogador(vez);
		if (args.length < 2) {
			System.out
			.println("Usage: java MultiThreadChatClient <host> <portNumber>\n"
					+ "Now using host=" + host + ", portNumber=" + portNumber);
		} else {
			host = args[0];
			portNumber = Integer.valueOf(args[1]).intValue();
		}

		/*
		 * Open a socket on a given host and port. Open input and output streams.
		 */
		try {
			clientSocket = new Socket(host, portNumber);
			//inputLine = new BufferedReader(new InputStreamReader(System.in));
			os = new ObjectOutputStream(clientSocket.getOutputStream());
			is = new ObjectInputStream(clientSocket.getInputStream());
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + host);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to the host "
					+ host);
		}

		/*
		 * If everything has been initialized then we want to write some data to the
		 * socket we have opened a connection to on the port portNumber.
		 */
		if (clientSocket != null && os != null && is != null) {
			try {

				/* Create a thread to read from the server. */
				new Thread(new MultiThreadChatClient()).start();
				//String toServer = " ";
				while (!closed) {
					//le do teclado pra mandar pro servidor (inputline)
					//toServer = inputLine.readLine().trim();
					//os.println(toServer);
					//ESCREVER OBJETO
					if(sair){
						os.writeObject("sair");
					}
					else if(primeiro){
						os.writeObject(jogador);
						primeiro = false;
						os.writeObject(new Integer(vez));
					}
					else os.writeObject(jogo);
				}
				/*
				 * Close the output stream, close the input stream, close the socket.
				 */
				os.close();
				is.close();
				clientSocket.close();
			} catch (IOException e) {
				System.err.println("IOException:  " + e);
			}
		}
	}

	/*
	 * Create a thread to read from the server. (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		/*
		 * Keep on reading from the socket till we receive "Bye" from the
		 * server. Once we received that then we want to break.
		 */
		Object responseObject;
		String responseLine = "";
		try {
			while ((responseObject = is.readObject()) != null) {
				if(responseObject instanceof Jogo){
					jogo = (Jogo) responseObject;
					
				}
				else if(responseObject instanceof String)
					responseLine = (String) responseObject;
				
				//System.out.println();
				/*if (responseLine.indexOf("*** Bye") != -1)
					break;*/
			}
			closed = true;
		} catch (IOException e) {
			System.err.println("IOException:  " + e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}