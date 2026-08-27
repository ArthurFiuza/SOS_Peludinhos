package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;

public class TelaMenu {

	private JFrame Menu;
	private JButton btnCadastroCliente;
	private JButton btnCadastroPet;

	;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMenu window = new TelaMenu();
					window.Menu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaMenu() {
		initialize();
		Menu.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Menu = new JFrame();
		Menu.setResizable(false);
		Menu.getContentPane().setBackground(new Color(244, 209, 204));
		Menu.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(355, 0, 300, 500);
		Menu.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel IMGpeludinhos = new JLabel("");
		IMGpeludinhos.setBounds(0, 0, 300, 500);
		panel.add(IMGpeludinhos);
		IMGpeludinhos.setIcon(new ImageIcon(getClass().getResource("/imagem/SOS peludinhos 2.png")));

		// BOTAO CADASTRO CLIENTE
		btnCadastroCliente = new JButton("Cadastro Cliente");
		btnCadastroCliente.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnCadastroCliente.setFocusPainted(false);
		btnCadastroCliente.setContentAreaFilled(false);
		btnCadastroCliente.setOpaque(true);
		btnCadastroCliente.setBorderPainted(false);
		btnCadastroCliente.setBackground(new Color(98, 68, 47));
		btnCadastroCliente.setForeground(Color.WHITE);
		btnCadastroCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaCliente();
				Menu.dispose();

			}
		});

		// BOTAO CADASTRO PET
		btnCadastroCliente.setBounds(20, 37, 210, 61);
		Menu.getContentPane().add(btnCadastroCliente);
		btnCadastroPet = new JButton("Cadastro Pet");
		btnCadastroPet.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnCadastroPet.setBounds(20, 121, 210, 61);
		btnCadastroPet.setFocusPainted(false);
		btnCadastroPet.setContentAreaFilled(false);
		btnCadastroPet.setOpaque(true);
		btnCadastroPet.setBorderPainted(false);
		btnCadastroPet.setBackground(new Color(98, 68, 47));
		btnCadastroPet.setForeground(Color.WHITE);
		btnCadastroPet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaAnimal();
				Menu.dispose();
			}
		});

		Menu.getContentPane().add(btnCadastroPet);
		
		// BOTAO CADASTRO ATENDIMENTO
		JButton btnCadastroAtendimento = new JButton("Atendimento");
		btnCadastroAtendimento.setOpaque(true);
		btnCadastroAtendimento.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnCadastroAtendimento.setFocusPainted(false);
		btnCadastroAtendimento.setBorderPainted(false);
		btnCadastroAtendimento.setBackground(new Color(98, 68, 47));
		btnCadastroAtendimento.setForeground(Color.WHITE);
		btnCadastroAtendimento.setBounds(20, 204, 210, 61);
		btnCadastroAtendimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaAtendimento();
				Menu.dispose();
			}
		});
		
		
		Menu.getContentPane().add(btnCadastroAtendimento);
		
		Menu.setTitle("Menu");
		Menu.setBounds(100, 100, 668, 535);
		Menu.setLocationRelativeTo(null);
		Menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
