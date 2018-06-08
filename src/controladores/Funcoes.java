package controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;

import formularios.FormularioInicial;
import formularios.FormularioPrincipal;

public class Funcoes extends Jogador {
	private static String[][] perguntas;
	private static int contador_perguntas;
	
	public int getContadorPerguntas() {
		return contador_perguntas;
	}
	
	private int[] geraPerguntas() {
		int[] perguntas = new int[13];
		Random r = new Random();
		for(int x = 0 ; x<13; x++) {
			int n = r.nextInt(20);
			if(x!=0) {
				for(int y=0;y<x;y++) {
					if(perguntas[y]==n) {
						x--;
						break;
					}else if(y==x-1) {
						perguntas[x]=n;
					}
				}
			}else {
				perguntas[x]=n;
			}
		}
		return perguntas;
	}
	public void definePerguntas(int categoria) {
		String[][] perguntas = new String[13][6];
		String[][] perguntas_lista = new String[20][6];
		if(categoria==0) {
			Perguntas_musicas pm = new Perguntas_musicas();
			perguntas_lista = pm.retornaPerguntas();
		}else if(categoria==1) {
			Perguntas_oscar po = new Perguntas_oscar();
			perguntas_lista = po.retornaPerguntas();
		}else {
			Perguntas_diversas pd = new Perguntas_diversas();
			perguntas_lista = pd.retornaPerguntas();
		}
		int[] aleatorias=geraPerguntas();
		for(int x=0; x<13;x++) {
			for(int y = 0; y<6; y++) {
				perguntas[x][y]=perguntas_lista[aleatorias[x]][y];
			}
		}
		contador_perguntas=0;
		
		this.perguntas=perguntas;
	
	}
	public boolean verificaRespostas(int resposta, int index) {
		if(perguntas[contador_perguntas][5].equalsIgnoreCase(perguntas[contador_perguntas][resposta])) {
			ArrayList<Jogador> j = Funcoes.getJ();
			j.get(index).setAcertos(j.get(index).getAcertos()+1);
			Funcoes.setJ(j);

		}
		boolean valida = atualizaPergunta();
		
		return valida;
	}
	
	public String retornaPergunta() {
		String pergunta = perguntas[contador_perguntas][0];
		String texto = "<html><div style=\"width:664px;\"><p style=\"text-align: center;\">";
		int y = 0;
		for(int x=pergunta.length()/3; x<pergunta.length();x++) {
			if(pergunta.substring(x, x+1).equals(" ")) {
				texto+=pergunta.substring(y,x);
				y=x;
				texto+="<br>";
				if(x<(pergunta.length()/3)*2) {
					x=(pergunta.length()/3)*2;
				}else {
					x=pergunta.length();
					texto+=pergunta.substring(y, x);
				}
			}
		}
		texto+="</p></div></html>";
		
		pergunta = texto;
		return pergunta;
		
	}
	public String retornaAlternativa(int coluna) {
		String alternativa = perguntas[contador_perguntas][coluna];
		return alternativa;		
	}
	
	public boolean contabilizaPulos(int index) {
		boolean pode_pular_ainda = true;
		ArrayList<Jogador> j = Funcoes.getJ();
		j.get(index).setPulos(j.get(index).getPulos()+1);
		Funcoes.setJ(j);
		if(j.get(index).getPulos()>2) {
			pode_pular_ainda=false;
		}
		atualizaPergunta();
		return pode_pular_ainda;
	}
	public boolean atualizaPergunta() {
		boolean valida=true;
		if(contador_perguntas-Funcoes.getJ().get(Funcoes.getJ().size()-1).getPulos()>8) {
			valida=false;
			JOptionPane.showMessageDialog(null, "Você acertou "+Funcoes.getJ().get(Funcoes.getJ().size()-1).getAcertos()+" Respostas");
		}else{
			contador_perguntas++;
		}
		return valida;
	}
	public boolean verificarNome(String nome) {
		boolean valida=true;
		for(int x = 0; x<Funcoes.getJ().size(); x++) {
			if(nome.equalsIgnoreCase(Funcoes.getJ().get(x).getNome())) {
				valida=false;
			}
		}
		if(valida==true) {
			ArrayList<Jogador> j = Funcoes.getJ();
			Jogador jo = new Jogador();;
			Date d = new Date();
			jo.setD(d);
			jo.setAcertos(0);
			jo.setNome(nome);
			jo.setPulos(0);
			System.out.println(d);
			j.add(jo);
			jo.setJ(j);
		}
		
		return valida;
		
	}
	public boolean retornaAjuda(JRadioButton r1, JRadioButton r2, JRadioButton r3, JRadioButton r4) {
		
		Random r = new Random();
		int n = r.nextInt(3);
		JOptionPane.showMessageDialog(null, "Você conseguiu "+(n+1)+" Alternativas erradas apagadas");
		while(n>=0) {
			if(!perguntas[contador_perguntas][1].equals(perguntas[contador_perguntas][5])&&!r1.getText().equals("Alternativa excluída")) {
				r1.setText("Alternativa excluída");
			}else if(!perguntas[contador_perguntas][2].equals(perguntas[contador_perguntas][5])&&!r2.getText().equals("Alternativa excluída")) {
				r2.setText("Alternativa excluída");
			}else if(!perguntas[contador_perguntas][3].equals(perguntas[contador_perguntas][5])&&!r3.getText().equals("Alternativa excluída")) {
				r3.setText("Alternativa excluída");
			}else {
				r4.setText("Alternativa excluída");
			}
			n--;
		}
		return false;
		
	}
	public DefaultTableModel geraRanking() {
		DefaultTableModel exibirDados = new DefaultTableModel();
		
		//Criar colunas
		exibirDados.addColumn("Data");
		exibirDados.addColumn("Nome");
		exibirDados.addColumn("Acertos");
		String[] colunas = new String[3];
		ArrayList<Jogador> j = Funcoes.getJ();
		for(int x = j.size()-1; x>=0; x--) {
			if(x>0) {
				for(int y = x-1;y>=0;y--) {
					if(j.get(x).getAcertos()<j.get(y).getAcertos()) {
						int acertos = j.get(x).getAcertos();
						String nome = j.get(x).getNome();
						Date d = j.get(x).getD();
						j.get(x).setAcertos(j.get(y).getAcertos());
						j.get(x).setD(j.get(y).getD());
						j.get(x).setNome(j.get(y).getNome());
						j.get(y).setAcertos(acertos);
						j.get(y).setD(d);
						j.get(y).setNome(nome);
						
					}
				}
			}

			colunas[0]= (j.get(x).getD()).toString();
			colunas[1]= j.get(x).getNome();
			colunas[2]= ""+j.get(x).getAcertos();
			exibirDados.addRow(colunas);

		}

		
		
		return exibirDados;
	}

}


