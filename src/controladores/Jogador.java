package controladores;

import java.util.ArrayList;
import java.util.Date;

public class Jogador {
	private String nome;
	private int acertos;
	private int pulos;
	private Date d;

	private static ArrayList<Jogador> j = new ArrayList<>();
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getAcertos() {
		return acertos;
	}
	public void setAcertos(int acertos) {
		this.acertos = acertos;
	}
	public int getPulos() {
		return pulos;
	}
	public Date getD() {
		return d;
	}
	public void setD(Date d) {
		this.d = d;
	}
	public void setPulos(int pulos) {
		this.pulos = pulos;
	}
	public static ArrayList<Jogador> getJ() {
		return j;
	}
	public static void setJ(ArrayList<Jogador> j) {
		Jogador.j = j;
	}
	
}
