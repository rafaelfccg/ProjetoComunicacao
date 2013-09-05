package jogo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;
import java.util.Collections;

public class Jogo implements Serializable{

	private static final long serialVersionUID = -1436222319841650730L;
	private File file;
	private File path;
	private final String caminho = "Save";
	private final String arquivo = "Save/Jogo.txt";
	Vector<Peca> pecas;
	Jogador[] jogador;
	Mesa mesa;
	int vez;
	
	public Jogo(Jogador[] jogadores) throws IOException{
		path = new File(caminho);
		path.mkdirs();
		file = new File(path, "Jogo.txt");
		file.createNewFile();
		jogador = jogadores;
		int maiorCarroca = 0;
		pecas = new Vector<Peca>();
		for(int i = 0; i < 7; ++i){
			for(int j = i; j < 7; ++j){
				Peca peca = new Peca(i, j);
				pecas.add(peca);
			}
		}
		
		Collections.shuffle(pecas);
		int start = 0;
		
		for(int joga = 0; joga < 4; ++joga){
			start = joga * 6;
			Vector<Peca> mao = new Vector<Peca>();
			for(int i = 0; i < 6; ++i){
				mao.add(pecas.get(i + start));
				if(pecas.get(i + start).num1 >= maiorCarroca && pecas.get(i + start).num1 == pecas.get(i + start).num2){
					vez = joga;
					maiorCarroca = pecas.get(i + start).num1; 
				}
			}
			jogador[joga].emMao = mao;
		}		
	}
	
	boolean jogar(int player, Peca peca, int jogada){
		//jogada: 0 == cima, 1 == baixo, 2 == toque
		if(player != vez) {
			return false;
		}
		else{
			if(jogada == 0) {
				mesa.jogar_cima(peca);
			}
			else if (jogada == 1) {
				mesa.jogar_baixo(peca);
			}
		}
		++vez;
		vez%=4;
		return true;
	}	
	
	void save() throws IOException {
		Save save = new Save(this.jogador, this.mesa, this.vez, this.pecas);
		FileOutputStream fos = new FileOutputStream(arquivo);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(save);
		oos.close();
		fos.close();
	}
	
	void load() throws ClassNotFoundException, IOException {
		FileInputStream fis = new FileInputStream(arquivo);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Save save = (Save) ois.readObject();
		this.pecas = save.pecas;
		this.vez = save.vez;
		this.mesa = save.mesa;
		this.jogador = save.jogador;
		ois.close();
		fis.close();
	}
}
