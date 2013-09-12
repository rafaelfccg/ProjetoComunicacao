import java.io.IOException;
import java.util.Scanner;

import jogo.Jogador;
import jogo.Jogo;

// Servidor vai Gerenciar todo o jogo 
// Cliente só vai receber informações do que ele tem e da mesa e enviar sua jogada
public class Main_test {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Jogador[] jogs = new Jogador[4]; // servidor vai ter o array dos jogadores
		jogs[0] = new Jogador(0);
		jogs[1] = new Jogador(1);
		jogs[2] = new Jogador(2);
		jogs[3] = new Jogador(3);
		
		try {
			Jogo jogo = new Jogo(jogs);// cria o jogo (Servidor cria o jogo )
			int vez = jogo.getVez();
			jogo.getMesa().print_test();// impressao de test
			while(jogo.getPoints_a()<7 && jogo.getPoints_b() <7){// enquanto ninguem ganhar o jogo
				vez = jogo.getVez();
				System.out.println("Sua mao:"); // Servidor envia mao e mesa para os clientes
				jogs[vez].print_hand();
				boolean b;
				do{// repete enquanto a jogada for invalida // Jogador manda a peça que ele deseja jogar e o lugar para o servidor
					System.out.println(jogo.getVez());
					System.out.println("Digite o 0 para jogar em cima, 1 para jogar em baixo e 2 para tocar");
					int lado = in.nextInt();// escolhe lado da mesa ou toca
					if(lado ==2){ 
						b = jogs[vez].joga(0, lado);
					}else{
						System.out.println("digite o inidice da peca que deseja jogar:");// escolhe a peca
						int index = in.nextInt();
						b  = jogs[vez].joga(index, lado);
						if(!b)System.out.println( "Jogada invalida "+jogs[vez].getEmMao().get(index).getNum1() +"/ "+jogs[vez].getEmMao().get(index).getNum2() );
					}
				}while(!b);
				
				jogo.getMesa().print_test();// imprime a mesa
				jogo.verify();// verifica fim de jogo servidor verifica fim do jogo (ESSA É A Única função que falta testar)
			}
			// Obs: Caso um dos jogadores caia é necessario usar a função jogo.save() para salvar o estado do jogo.
			// e quando ele reconectar dar load save. 
			// Obs: Assim que tiver os IPs de cada jogador alterar o string arquivo para indentificar unicamente cada quarteto de jogadores.
			// recomento concatenar os 4 IPs para indentificar o arquivo.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
