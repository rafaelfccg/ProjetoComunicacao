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
	static int portNumber;
	static String host;
	static Pacote pck;
	static boolean online;
	/*
	 * Le toda rodada as pecas dele e as da mesa
	 * inteiro pra vez (mod 4)
	 */
	
	public MultiThreadChatClient(String ip, String nome, int time) throws TimeCheioException{
		this.portNumber = 2222;
		this.host = ip;	
		try {
			clientSocket = new Socket(host, portNumber);
			os = new ObjectOutputStream(clientSocket.getOutputStream());
			is = new ObjectInputStream(clientSocket.getInputStream());
			boolean aux = false;
			os.writeObject(nome);
			os.writeInt(time);
			aux = is.readBoolean();
			online = true;
			if(aux){
				numJogador = is.readInt();
			}else{
				throw new TimeCheioException();
			}
			
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + host);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to the host "
					+ host);
		}			
	}
	
	public static void main(String[] args) {		

		if (clientSocket != null && os != null && is != null) {
			try {
				//NA GUI -> new Thread(new MultiThreadChatClient()).start();
				while (!closed) {
					//nada
				}
				os.close();
				is.close();
				clientSocket.close();
			} catch (IOException e) {
				System.err.println("IOException:  " + e);
			}
		}
	}
	
	void fecha(){
		try {
			os.close();
			is.close();
			clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Fazer um funçao só para leitura que nao tem while nela e sim na GUI. Assim gui vai saber quando atualizar.
	 * Fazer uma funçao que  vai fazer a jogada, essa funçao vai receber parametros  o index da peça e onde jogar e retorna se a jogada foi valida
	 * */
	
	//fica lendo o jogo
	public void run(){
		while(jogo.verify()!=2){
			try {
				pck = (Pacote) is.readObject();
				jogo = pck.jogo;
				online = pck.status;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	public boolean jogar(int index , int jogada){
		boolean b = false;	
		if(jogo.getVez() == numJogador){
			b = jogo.getJogador(numJogador).joga(index, jogada);
		}
		if(b){
			try {
				os.writeObject(pck);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return b;
		
	}
	/*public void run() {
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
	}*/
}
















