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

	private static Socket clientSocket = null;
	private static ObjectOutputStream os = null;
	private static ObjectInputStream is = null;

	//private static BufferedReader inputLine = null;
	private static boolean closed = false;

	static int numJogador;
	static Scanner in = new Scanner(System.in);
	static Jogo jogo;
	/*
	 * Le toda rodada as pecas dele e as da mesa
	 * inteiro pra vez (mod 4)
	 */
	public static void main(String[] args) {

		// The default port.
		int portNumber = 2222;
		// The default host.
		String host = "192.168.0.176";
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
			//os.flush();
			is = new ObjectInputStream(clientSocket.getInputStream());
			//is.reset();
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
		try {
			System.out.println("available: "+is.available());
			numJogador = is.readInt();
			System.out.println(numJogador);
			jogo = (Jogo) is.readObject();
			System.out.println("Recebi a porra do jogo");
			boolean b = true;
			while(jogo.verify() != 2 ){
				System.out.println(jogo.getMesa().print_test());
				System.out.println("vez: "+jogo.getVez() + " numJogador: " + numJogador);
				if(jogo.getVez() == numJogador){
					System.out.println(jogo.getJogador(jogo.getVez()).print_hand());
					// faz a jogada		
					do{
						System.out.println("Digite o 0 para jogar em baixo, 1 para jogar em cima e 2 para tocar");
						int lado = in.nextInt();// escolhe lado da mesa ou toca
						int index;
						if(lado==2)index = 0;
						else{
							System.out.println("Digite o index da peca que você deseja jogar");
							index = in.nextInt();
						}
						b = jogo.getJogador(numJogador).joga(index, lado);
						if(!b) System.out.println("Jogada inváida");
					}while(!b);
					System.out.println("joguei");
					//jogo.jogar(player, peca, jogada);
					os.writeObject(jogo);
				}				
				jogo = (Jogo)is.readObject();				
			}
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch blocks
			e.printStackTrace();
		}
	}
}
















