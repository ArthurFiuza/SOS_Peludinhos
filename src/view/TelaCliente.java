package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import java.awt.event.ActionEvent;

import controller.ClienteController;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import javax.swing.ImageIcon;

public class TelaCliente {

	private JFrame frmPrincipal;
	private JTextField txtNomePet;
	private JTextField txtcpf;
	private JTextField txtcontato;

	private ClienteController controller = new ClienteController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCliente window = new TelaCliente();
					window.frmPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the application.
	 */
	public TelaCliente() {
		initialize();
		frmPrincipal.setVisible(true);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrincipal = new JFrame();
		frmPrincipal.setResizable(false);
		frmPrincipal.getContentPane().setBackground(new Color(244, 209, 204));
		frmPrincipal.setTitle("Cadastro Cliente");
		frmPrincipal.setBounds(100, 100, 400, 400);
		frmPrincipal.setLocationRelativeTo(null);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrincipal.getContentPane().setLayout(null);

		JButton btnGravar = new JButton("Gravar");
		btnGravar.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnGravar.setFocusPainted(false);
		btnGravar.setContentAreaFilled(false);
		btnGravar.setOpaque(true);
		btnGravar.setBackground(new Color(98, 68, 47));
		btnGravar.setForeground(Color.WHITE);
		btnGravar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String nome = txtNomePet.getText();
				String cpf = txtcpf.getText();
				String contato = txtcontato.getText();

				if (!Funcoes.validarCpf(cpf)) {
					JOptionPane.showMessageDialog(frmPrincipal, "CPF inválido!");
					return;
				} else if (!Funcoes.validarNumero(contato)) {
					JOptionPane.showMessageDialog(frmPrincipal, "Numero inválido!");
					return;
				}
				naoEVazioESalvar(nome, cpf, contato, btnGravar);

			}
		});

		btnGravar.setBounds(256, 201, 120, 23);
		frmPrincipal.getContentPane().add(btnGravar);

		JButton btnLimparCampos = new JButton("Limpar campos");
		btnLimparCampos.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnLimparCampos.setFocusPainted(false);
		btnLimparCampos.setContentAreaFilled(false);
		btnLimparCampos.setOpaque(true);
		btnLimparCampos.setBackground(new Color(98, 68, 47));
		btnLimparCampos.setForeground(Color.WHITE);

		btnLimparCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar(frmPrincipal);

				txtcontato.setText("(xx)x xxxx-xxxx");
				txtcontato.setForeground(Color.GRAY);

				txtcpf.setText("xxx.xxx.xxx-xx");
				txtcpf.setForeground(Color.GRAY);
			}
		});
		btnLimparCampos.setBounds(10, 201, 120, 23);
		frmPrincipal.getContentPane().add(btnLimparCampos);

		JButton btnListarCliente = new JButton("Listar Clientes");
		btnListarCliente.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnListarCliente.setFocusPainted(false);
		btnListarCliente.setContentAreaFilled(false);
		btnListarCliente.setOpaque(true);
		btnListarCliente.setBackground(new Color(98, 68, 47));
		btnListarCliente.setForeground(Color.WHITE);
		btnListarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RelatorioCliente relatorio = new RelatorioCliente();
		        relatorio.abrir();
		        frmPrincipal.dispose();
		        
			}
		});
		btnListarCliente.setBounds(256, 329, 120, 23);
		frmPrincipal.getContentPane().add(btnListarCliente);

		JLabel lblCadastrandoCliente = new JLabel("Cadastrando Cliente");
		lblCadastrandoCliente.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblCadastrandoCliente.setBounds(85, 1, 241, 44);
		frmPrincipal.getContentPane().add(lblCadastrandoCliente);

		JButton btnVoltarAoMenu = new JButton("Voltar ao menu");
		btnVoltarAoMenu.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnVoltarAoMenu.setFocusPainted(false);
		btnVoltarAoMenu.setContentAreaFilled(false);
		btnVoltarAoMenu.setOpaque(true);
		btnVoltarAoMenu.setBackground(new Color(98, 68, 47));
		btnVoltarAoMenu.setForeground(Color.WHITE);
		btnVoltarAoMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaMenu();
				frmPrincipal.dispose();
			}
		});
		btnVoltarAoMenu.setBounds(10, 329, 125, 23);
		frmPrincipal.getContentPane().add(btnVoltarAoMenu);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 55, 59, 32);
		frmPrincipal.getContentPane().add(lblNome);
		lblNome.setFont(new Font("Segoe UI", Font.BOLD, 14));

		txtNomePet = new JTextField();
		txtNomePet.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtNomePet.setBounds(85, 56, 257, 32);
		frmPrincipal.getContentPane().add(txtNomePet);
		txtNomePet.setColumns(10);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(10, 98, 59, 32);
		frmPrincipal.getContentPane().add(lblCpf);
		lblCpf.setFont(new Font("Segoe UI", Font.BOLD, 14));

		JLabel lblContato = new JLabel("Contato:");
		lblContato.setBounds(10, 155, 74, 12);
		frmPrincipal.getContentPane().add(lblContato);
		lblContato.setFont(new Font("Segoe UI", Font.BOLD, 14));

		txtcontato = new JTextField();
		txtcontato.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtcontato.setBounds(85, 142, 257, 32);
		frmPrincipal.getContentPane().add(txtcontato);
		txtcontato.setColumns(10);
		txtcontato.setText("(xx)x xxxx-xxxx");
		txtcontato.setForeground(Color.GRAY);

		txtcpf = new JTextField();
		txtcpf.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtcpf.setBounds(85, 99, 257, 32);
		frmPrincipal.getContentPane().add(txtcpf);
		txtcpf.setColumns(10);
		txtcpf.setText("xxx.xxx.xxx-xx");
		txtcpf.setForeground(Color.GRAY);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("D:\\AAI POO tudo\\AAI-POO 16_06_2025\\AAI-POO-11_06_2025-20250616T131620Z-1-001\\AAI-POO-11_06_2025\\AAI\\TRABALHO\\src\\imagem\\SOS gatinho.png"));
		lblNewLabel.setBounds(10, 4, 50, 50);
		frmPrincipal.getContentPane().add(lblNewLabel);

		txtcpf.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if (txtcpf.getText().equals("xxx.xxx.xxx-xx")) {
					txtcpf.setText("");
					txtcpf.setForeground(Color.BLACK); // Altera a cor do texto para preto quando o campo é clicado
				}
			}

			public void focusLost(FocusEvent e) {
				if (txtcpf.getText().isEmpty()) {
					txtcpf.setText("xxx.xxx.xxx-xx");
					txtcpf.setForeground(Color.GRAY); // Altera a cor do texto de volta para o cinza do exemplo
				}
			}
		});

		txtcontato.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if (txtcontato.getText().equals("(xx)x xxxx-xxxx")) {
					txtcontato.setText("");
					txtcontato.setForeground(Color.BLACK); // Altera a cor do texto para preto quando o campo é clicado
				}
			}

			public void focusLost(FocusEvent e) {
				if (txtcontato.getText().isEmpty()) {
					txtcontato.setText("(xx)x xxxx-xxxx");
					txtcontato.setForeground(Color.GRAY); // Altera a cor do texto de volta para o cinza do exemplo
				}
			}
		});

	}

	void limpar(Container container) {
		for (Component component : container.getComponents()) {
			if (component instanceof JTextField) {
				((JTextField) component).setText("");
			} else if (component instanceof Container) {
				limpar((Container) component);
			}
		}
	}

	void naoEVazioESalvar(String string1, String string2, String string3, JButton btnGravar) {
		if (string1.trim().isEmpty() && string2.trim().isEmpty() && string3.trim().isEmpty()) {
			JOptionPane.showMessageDialog(frmPrincipal, "Todos os campos são obrigatórios!", "Atenção",
					JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				txtcpf.setText(Funcoes.FormatarCpf(txtcpf.getText()));
				txtcontato.setText(Funcoes.FormatarContato(txtcontato.getText()));
				salvar();
				JOptionPane.showMessageDialog(btnGravar, "Dados enviados com sucesso!");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(frmPrincipal, "Erro ao salvar: " + ex.getMessage(), "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		return;
	}

	private void salvar() {
		controller.salvar(txtNomePet.getText(), txtcpf.getText(), txtcontato.getText());
	}

	
}