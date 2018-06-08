package formularios;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import controladores.Funcoes;

public class FormularioRanking extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public FormularioRanking() {		
		
		setSize( 600, 362);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		Funcoes f = new Funcoes();
		
	
		JTable tabela = new JTable(f.geraRanking());
		
		// JScrollPane - Barra de rolagem englobando o JTable
		JScrollPane barraRolagem = new JScrollPane(tabela);
		barraRolagem.setBounds(0, 0, 592, 284);
		contentPane.add(barraRolagem);

	}

}
