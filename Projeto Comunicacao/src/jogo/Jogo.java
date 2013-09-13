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
	int points_a;
	int points_b;
	Peca last_played;
	int cont_tocs;
	
	public int getPoints_a() {
		return points_a;
	}

	public void setPoints_a(int points_a) {
		this.points_a = points_a;
	}

	public int getPoints_b() {
		return points_b;
	}

	public void setPoints_b(int points_b) {
		this.points_b = points_b;
	}

	public Vector<Peca> getPecas() {
		return pecas;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public void setPecas(Vector<Peca> pecas) {
		this.pecas = pecas;
	}

	public int getVez() {
		return vez;
	}

	public void setVez(int vez) {
		this.vez = vez;
	}

	public Jogo(Jogador[] jogadores) throws IOException{
		cont_tocs = 0;
		path = new File(caminho);
		path.mkdirs();
		file = new File(path, "Jogo.txt");
		file.createNewFile();
		jogador = jogadores;
		int maiorCarroca = 0;
		pecas = new Vector<Peca>();
		mesa = new Mesa();
		for(int i = 0; i < 7; ++i){
			for(int j = i; j < 7; ++j){
				Peca peca = new Peca(i, j);
				pecas.add(peca);
			}
		}
		print_test();
		Collections.shuffle(pecas);
		print_test();
		int start = 0;
		int play=0;
		for(int joga = 0; joga < 4; ++joga){
			start = joga * 6;
			jogadores[joga].jogo = this;
			Vector<Peca> mao = new Vector<Peca>();
			for(int i = 0; i < 6; ++i){
				mao.add(pecas.get(i + start));
				if(pecas.get(i + start).num1 >= maiorCarroca && pecas.get(i + start).num1 == pecas.get(i + start).num2){
					vez = joga;
					maiorCarroca = pecas.get(i + start).num1; 
					play = i;
				}
			}
			jogador[joga].emMao = mao;
		}
		jogador[vez].joga(play, 0);//jogar(vez,jogador[vez].emMao.get(play),0);
	}
	void print_test(){
		for(int i = 0 ; i <pecas.size();i++){
			Peca aux = pecas.get(i);
			System.out.println(aux.num1+"/"+aux.num2);
		}
	}
	boolean jogar(int player, Peca peca, int jogada){
		//jogada: 0 == cima, 1 == baixo, 2 == toque
		boolean aux = true ;
		if(player != vez) {
			/*test*/System.out.println("nao é a sua vez");
			return false;
		}
		else{
			if(jogada == 0) {
				//System.out.println(peca.num1);
				aux = mesa.jogar_cima(peca);
				cont_tocs = 0;
			}
			else if (jogada == 1) {
				aux = mesa.jogar_baixo(peca);
				cont_tocs  = 0;
			}else if(jogada == 2){
				if(aux) cont_tocs++;
			}
		}
		if(aux){
			if(jogada!=2){
				last_played = peca;
				cont_tocs = 0;
			}
			
			++vez;
			vez%=4;
		}
		return aux;
	}	
	public void verify(){
		if(cont_tocs == 4){
			reset();
			return;
		}
		int aux = -1;
		for(int i = 0; i<4;i++){
			if(jogador[i].emMao.size() == 0){
				aux = i;
				int points = 1;
				int encaixe = last_played.encaixe;
				if(last_played.num1 == last_played.num2) points++;
				if(last_played.identificador == 2){
					if(encaixe ==1 && mesa.mesa.tail.encaixe==1 && last_played.num1==mesa.mesa.tail.num1) points+=2;
					else if(encaixe ==1 && mesa.mesa.tail.encaixe==2 && last_played.num1==mesa.mesa.tail.num2) points+=2;
					else if(encaixe ==2 && mesa.mesa.tail.encaixe==1 && last_played.num2==mesa.mesa.tail.num1) points+=2;
					else if(encaixe ==2 && mesa.mesa.tail.encaixe==2 && last_played.num2==mesa.mesa.tail.num2) points+=2;
				}else if(last_played.identificador == 3){
					if(encaixe ==1 && mesa.mesa.head.encaixe==1 && last_played.num1==mesa.mesa.head.num1) points+=2;
					else if(encaixe ==1 && mesa.mesa.head.encaixe==2 && last_played.num1==mesa.mesa.head.num2) points+=2;
					else if(encaixe ==2 && mesa.mesa.head.encaixe==1 && last_played.num2==mesa.mesa.head.num1) points+=2;
					else if(encaixe ==2 && mesa.mesa.head.encaixe==2 && last_played.num2==mesa.mesa.head.num2) points+=2;
				}
				if(i%2==0)points_a+=points;
				else points_b+=points;
				break;
			}
		}
		if(points_a<7 && points_b<7 && aux !=-1){
			reset();
			System.out.println("Pontos_a: " +points_a);
			System.out.println("Pontos_b: " +points_b);
		}else{
			if(points_a >=7)System.out.println("Time A ganhou");
			if(points_b >=7)System.out.println("Time B ganhou");
		}
	}
	void reset(){
		System.out.println("JOGO TRANCADO - Reinicia jogo");
		int maiorCarroca = 0;
		cont_tocs = 0;
		pecas = new Vector<Peca>();
		mesa = new Mesa();
		for(int i = 0; i < 7; ++i){
			for(int j = i; j < 7; ++j){
				Peca peca = new Peca(i, j);
				pecas.add(peca);
			}
		}
		
		Collections.shuffle(pecas);
		int start = 0;
		int play=0;
		for(int joga = 0; joga < 4; ++joga){
			start = joga * 6;
			jogador[joga].jogo = this;
			Vector<Peca> mao = new Vector<Peca>();
			for(int i = 0; i < 6; ++i){
				mao.add(pecas.get(i + start));
				if(pecas.get(i + start).num1 >= maiorCarroca && pecas.get(i + start).num1 == pecas.get(i + start).num2){
					vez = joga;
					maiorCarroca = pecas.get(i + start).num1; 
					play = i;
				}
			}
			jogador[joga].emMao = mao;
		}
		jogar(vez,jogador[vez].emMao.get(play),0);
	}
	
	public void save() throws IOException {
		Save save = new Save(this.jogador, this.mesa, this.vez, this.pecas);
		FileOutputStream fos = new FileOutputStream(arquivo);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(save);
		oos.close();
		fos.close();
	}
	
	public void load() throws ClassNotFoundException, IOException {
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
