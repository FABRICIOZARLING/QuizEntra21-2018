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
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormularioPrincipal extends JFrame {

	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public FormularioPrincipal(int categoria) {
		setSize( 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Funcoes f = new Funcoes();
		f.definePerguntas(categoria);
		int jogador = f.getJ().size()-1;
		int questao = 1;
	
		
		JLabel lblquestaoN = new JLabel();
		lblquestaoN.setText("Quest\u00E3o "+questao);
		lblquestaoN.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblquestaoN.setBounds(10, 11, 113, 22);
		contentPane.add(lblquestaoN);
		
		JLabel lblPergunta = new JLabel(f.retornaPergunta());
		lblPergunta.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblPergunta.setBounds(10, 44, 864, 274);
		contentPane.add(lblPergunta);
		
		ButtonGroup alternativas = new ButtonGroup();
		JRadioButton rdbtnAlternativa1 = new JRadioButton(f.retornaAlternativa(01));
		rdbtnAlternativa1.setMnemonic(1);
		rdbtnAlternativa1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnAlternativa1.setBounds(10, 337, 864, 23);
		alternativas.add(rdbtnAlternativa1);
		contentPane.add(rdbtnAlternativa1);
		
		JRadioButton rdbtnAlternativa2 = new JRadioButton(f.retornaAlternativa(02));
		rdbtnAlternativa2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnAlternativa2.setMnemonic(2);
		rdbtnAlternativa2.setBounds(10, 372, 864, 23);
		alternativas.add(rdbtnAlternativa2);
		contentPane.add(rdbtnAlternativa2);
		
		JRadioButton rdbtnAlternativa3 = new JRadioButton(f.retornaAlternativa(03));
		rdbtnAlternativa3.setMnemonic(3);
		rdbtnAlternativa3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnAlternativa3.setBounds(10, 411, 864, 23);
		alternativas.add(rdbtnAlternativa3);
		contentPane.add(rdbtnAlternativa3);
		
		JRadioButton rdbtnAlternativa4 = new JRadioButton(f.retornaAlternativa(04));
		rdbtnAlternativa4.setMnemonic(4);
		rdbtnAlternativa4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnAlternativa4.setBounds(10, 449, 864, 23);
		alternativas.add(rdbtnAlternativa4);
		contentPane.add(rdbtnAlternativa4);
		

		

		
		JButton btnDesistir = new JButton("Desistir");
		btnDesistir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnDesistir.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDesistir.setBounds(10, 502, 215, 23);
		contentPane.add(btnDesistir);
		
		JButton btnPróxima = new JButton("Pr\u00F3xima");
		btnPróxima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(f.verificaRespostas(alternativas.getSelection().getMnemonic(), jogador)) {
						lblPergunta.setText(f.retornaPergunta());
						rdbtnAlternativa1.setText(f.retornaAlternativa(1));
						rdbtnAlternativa2.setText(f.retornaAlternativa(2));
						rdbtnAlternativa3.setText(f.retornaAlternativa(3));
						rdbtnAlternativa4.setText(f.retornaAlternativa(4));
						lblquestaoN.setText("Questão "+(f.getContadorPerguntas()-f.getJ().get(jogador).getPulos()+1));
						alternativas.clearSelection();
					}else {
						dispose();
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Selecione uma resposta");
				}
				

			}
		});
		btnPróxima.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnPróxima.setBounds(658, 502, 215, 23);
		contentPane.add(btnPróxima);
		
		JButton btnPular = new JButton("Pular");
		btnPular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPular.setEnabled(f.contabilizaPulos(jogador));
				lblPergunta.setText(f.retornaPergunta()+1);
				rdbtnAlternativa1.setText(f.retornaAlternativa(1));
				rdbtnAlternativa2.setText(f.retornaAlternativa(2));
				rdbtnAlternativa3.setText(f.retornaAlternativa(3));
				rdbtnAlternativa4.setText(f.retornaAlternativa(4));
				alternativas.clearSelection();

			}
		});
		btnPular.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnPular.setBounds(226, 502, 215, 23);
		contentPane.add(btnPular);
		
		JButton btnAjuda = new JButton("Ajuda");
		btnAjuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAjuda.setEnabled(f.retornaAjuda(rdbtnAlternativa1, rdbtnAlternativa2, rdbtnAlternativa3, rdbtnAlternativa4));
			}
		});
		btnAjuda.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAjuda.setBounds(442, 502, 215, 23);
		contentPane.add(btnAjuda);
		setLocationRelativeTo(null);
	}
}
