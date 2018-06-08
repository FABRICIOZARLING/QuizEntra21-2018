package formularios;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controladores.Funcoes;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class FormularioInicial extends JFrame {

	private JPanel contentPane;
	private JTextField tfNome;

	/**
	 * Create the frame.
	 */
	public FormularioInicial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize( 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Funcoes f = new Funcoes();
		
		JLabel lblDigiteSeuNome = new JLabel("Digite seu Nome");
		lblDigiteSeuNome.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDigiteSeuNome.setBounds(65, 69, 139, 39);
		contentPane.add(lblDigiteSeuNome);
		
		tfNome = new JTextField();
		tfNome.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfNome.setBounds(227, 78, 280, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"M\u00FAsicas", "Oscar", "Diversas"}));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox.setBounds(302, 161, 205, 20);
		contentPane.add(comboBox);
		
		JLabel lblSelecioneACategoria = new JLabel("Selecione a Categoria");
		lblSelecioneACategoria.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSelecioneACategoria.setBounds(65, 152, 177, 39);
		contentPane.add(lblSelecioneACategoria);
		
		JButton btnRanking = new JButton("Ranking");
		btnRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FormularioRanking fr = new FormularioRanking();
				fr.setVisible(true);
			}
		});
		btnRanking.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnRanking.setBounds(65, 295, 195, 23);
		contentPane.add(btnRanking);
		
		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!tfNome.getText().equals("")) {
					if(f.verificarNome(tfNome.getText())) {
						FormularioPrincipal fi = new FormularioPrincipal(comboBox.getSelectedIndex());
						fi.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "O Jogador digitado já participou do Desafio");
					}
					tfNome.setText("");
				}else {
					JOptionPane.showMessageDialog(null, "Digite um Nome");
				}

			}
		});
		btnIniciar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnIniciar.setBounds(312, 295, 195, 23);
		contentPane.add(btnIniciar);
		setLocationRelativeTo(null);
	}
}
