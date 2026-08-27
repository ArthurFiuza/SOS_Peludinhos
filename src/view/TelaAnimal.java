package view;

import java.awt.Color;

import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import controller.AnimalController;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;

public class TelaAnimal {
	
	public int codraca;
	private JFrame frmPrincipal;
	private JTextField txtNomePet;
	private JTextField textDtnasc;
	JComboBox<String> comboRaca = new JComboBox<String>();
	JComboBox<String> comboEspecie = new JComboBox<String>();

	private AnimalController controller = new AnimalController();
	private JTextField txtcpf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAnimal window = new TelaAnimal();
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
	public TelaAnimal() {
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
		frmPrincipal.setTitle("Cadastro Animal");
		frmPrincipal.setBounds(100, 100, 571, 526);
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
						
						String cpf = txtcpf.getText();
						String nomeAnimal = txtNomePet.getText();
						String dtNascimentoAnimal = textDtnasc.getText();
						
						if (!Funcoes.validarCpf(cpf)) {
							JOptionPane.showMessageDialog(frmPrincipal, "CPF inválido!");
							return;
						}

						naoEVazioESalvar(cpf, nomeAnimal, dtNascimentoAnimal, comboRaca,btnGravar);

					
			}
		});
		btnGravar.setBounds(374, 338, 110, 23);
		frmPrincipal.getContentPane().add(btnGravar);

		JButton btnLimparCampos = new JButton("Limpar campos");
		btnLimparCampos.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnLimparCampos.setFocusPainted(false);
		btnLimparCampos.setContentAreaFilled(false);
		btnLimparCampos.setOpaque(true);
		btnLimparCampos.setBackground(new Color(98, 68, 47));
		btnLimparCampos.setForeground(Color.WHITE);
		btnLimparCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar(frmPrincipal);

			}

		});
		btnLimparCampos.setBounds(203, 338, 115, 23);
		frmPrincipal.getContentPane().add(btnLimparCampos);

		JButton btnAtualizar = new JButton("Listar Animais");
		btnAtualizar.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnAtualizar.setFocusPainted(false);
		btnAtualizar.setContentAreaFilled(false);
		btnAtualizar.setOpaque(true);
		btnAtualizar.setBackground(new Color(98, 68, 47));
		btnAtualizar.setForeground(Color.WHITE);
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RelatorioAnimal relatorioanimal = new RelatorioAnimal();
				relatorioanimal.abrir();
				frmPrincipal.dispose();

			}
		});
		btnAtualizar.setBounds(45, 338, 110, 23);
		frmPrincipal.getContentPane().add(btnAtualizar);

		JLabel lblCadastrandoCliente = new JLabel("Cadastrando Animal");
		lblCadastrandoCliente.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblCadastrandoCliente.setBounds(101, 11, 241, 44);
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
		btnVoltarAoMenu.setBounds(193, 409, 125, 23);
		frmPrincipal.getContentPane().add(btnVoltarAoMenu);

		JLabel lblNomePet = new JLabel("Nome do Pet:");
		lblNomePet.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNomePet.setBounds(67, 151, 110, 15);
		frmPrincipal.getContentPane().add(lblNomePet);

		JLabel lblDataNasc = new JLabel("Data De Nascimento:");
		lblDataNasc.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblDataNasc.setBounds(22, 186, 163, 14);
		frmPrincipal.getContentPane().add(lblDataNasc);

		JLabel lblEspecie = new JLabel("Selecione a Espécie:");
		lblEspecie.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblEspecie.setBounds(37, 230, 144, 15);
		frmPrincipal.getContentPane().add(lblEspecie);

		JLabel lblRaca = new JLabel("Selecione a Raça:");
		lblRaca.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblRaca.setBounds(37, 263, 125, 14);
		frmPrincipal.getContentPane().add(lblRaca);

		textDtnasc = new JTextField();
		textDtnasc.setFont(new Font("Tahoma", Font.BOLD, 14));
		textDtnasc.setBounds(179, 179, 257, 32);
		frmPrincipal.getContentPane().add(textDtnasc);
		textDtnasc.setColumns(10);

		txtNomePet = new JTextField();
		txtNomePet.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtNomePet.setBounds(178, 143, 257, 32);
		frmPrincipal.getContentPane().add(txtNomePet);
		txtNomePet.setColumns(10);

		comboEspecie = new JComboBox<>();
		comboEspecie.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboEspecie.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// Habilita o comboRaca só se a seleção for diferente da primeira opção (ex:
				// índice 0)
				if (comboEspecie.getSelectedIndex() > 0) {
					comboRaca.setEnabled(true);
					controller.SelectRaca(comboRaca, comboEspecie.getSelectedIndex());
				} else {
					comboRaca.setEnabled(false);
					comboRaca.setSelectedIndex(-1); // limpa seleção
				}
			}
		});

		comboEspecie.setToolTipText("");
		comboEspecie.setBackground(new Color(255, 255, 255));
		controller.SelectEspecie(comboEspecie);
		comboEspecie.setBounds(179, 228, 150, 22);
		frmPrincipal.getContentPane().add(comboEspecie);

		comboRaca = new JComboBox<>();
		comboRaca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			        String nomeRacaSelecionada = (String) comboRaca.getSelectedItem();
			        codraca = controller.PegarCodigoRacaPeloNome(comboRaca, nomeRacaSelecionada);
			}
		});
		comboRaca.setToolTipText("");
		comboRaca.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboRaca.setBackground(new Color(255, 255, 255));
		comboRaca.setBounds(179, 261, 150, 23);
		frmPrincipal.getContentPane().add(comboRaca);
		comboRaca.setEnabled(false);
		controller.SelectRaca(comboRaca, comboRaca.getSelectedIndex());

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("D:\\AAI POO tudo\\AAI-POO 16_06_2025\\AAI-POO-11_06_2025-20250616T131620Z-1-001\\AAI-POO-11_06_2025\\AAI\\TRABALHO\\src\\imagem\\SOS gatinho.png"));
		lblNewLabel.setBounds(27, 14, 50, 50);
		frmPrincipal.getContentPane().add(lblNewLabel);
		
		JLabel lblCpfDoCliente = new JLabel("CPF do Cliente:");
		lblCpfDoCliente.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblCpfDoCliente.setBounds(66, 110, 110, 15);
		frmPrincipal.getContentPane().add(lblCpfDoCliente);
		
		txtcpf = new JTextField();
		txtcpf.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtcpf.setBounds(177, 102, 257, 32);
		frmPrincipal.getContentPane().add(txtcpf);
		txtcpf.setColumns(10);
		txtcpf.setText("xxx.xxx.xxx-xx");
		txtcpf.setForeground(Color.GRAY);
		
		
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

	void naoEVazioESalvar(String string, String string1, String string2, JComboBox<String> combo, JButton btnGravar) {
		if (string.trim().isEmpty() && string1.trim().isEmpty() && string2.trim().isEmpty() && combo.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(frmPrincipal, "Todos os campos são obrigatórios!", "Atenção",
					JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				txtcpf.setText(Funcoes.FormatarCpf(txtcpf.getText()));
				salvar();
				salvarClientexAnimal();
				JOptionPane.showMessageDialog(btnGravar, "Dados enviados com sucesso!");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(frmPrincipal, "Erro ao salvar: " + ex.getMessage(), "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		return;
	}

	private void salvar() {
		 controller.salvar(txtNomePet.getText(), textDtnasc.getText(), codraca);
	}
	
	private void salvarClientexAnimal() {
		controller.salvarClientexAnimal(txtcpf.getText());
	}


}
