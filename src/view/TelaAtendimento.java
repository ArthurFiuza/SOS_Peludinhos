package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.time.Year;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.text.NumberFormatter;


import controller.AtendimentoController;
import model.Atendimento;

import java.text.NumberFormat;
import java.util.Locale;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;



public class TelaAtendimento {

	private JFrame frmPrincipal;
	String[] meses = { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro",
			"Outubro", "Novembro", "Dezembro" };
	private JFormattedTextField CustoTotal;
	JComboBox<String> comboPet = new JComboBox<String>();
	JComboBox<String> comboVet = new JComboBox<String>();
	private JFormattedTextField ftxtcpfTutor;
	private int codVet;
	private float custoTotal;
	private String relatorio;
	private String cpf;
	private String horaAtual;
	private int codPet;        
    private String data;  
	private boolean pago;
	private boolean retorno;
	Atendimento atendimento = new Atendimento();
	
	private AtendimentoController controller = new AtendimentoController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAtendimento window = new TelaAtendimento();
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
	public TelaAtendimento() {
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
		frmPrincipal.getContentPane().setLayout(null);
		
		

		JLabel lbData = new JLabel("Data :");
		lbData.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lbData.setBounds(78, 295, 46, 14);
		frmPrincipal.getContentPane().add(lbData);

		JLabel lbHora = new JLabel("Hora :");
		lbHora.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lbHora.setBounds(517, 331, 46, 14);
		frmPrincipal.getContentPane().add(lbHora);

		JLabel lbCustoTotal = new JLabel("CustoTotal  :");
		lbCustoTotal.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lbCustoTotal.setBounds(31, 208, 91, 14);
		frmPrincipal.getContentPane().add(lbCustoTotal);

		JLabel lbPago = new JLabel("Pago :");
		lbPago.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lbPago.setBounds(71, 239, 46, 19);
		frmPrincipal.getContentPane().add(lbPago);

		JLabel lbRetorno = new JLabel("Retorno :");
		lbRetorno.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lbRetorno.setBounds(54, 269, 69, 14);
		frmPrincipal.getContentPane().add(lbRetorno);

		JLabel lbRelatório = new JLabel("Relatório :");
		lbRelatório.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lbRelatório.setBounds(22, 320, 80, 14);
		frmPrincipal.getContentPane().add(lbRelatório);

		JTextArea txtRelatorio = new JTextArea();
		txtRelatorio.setFont(new Font("Segoe UI", Font.BOLD, 14));
		txtRelatorio.setBounds(12, 352, 603, 124);
		frmPrincipal.getContentPane().add(txtRelatorio);
		
		

		JComboBox<Integer> Dia = new JComboBox<>();
		Dia.setBounds(134, 293, 70, 22);
		frmPrincipal.getContentPane().add(Dia);

		JComboBox<String> Mes = new JComboBox<>(meses);
		Mes.setBounds(214, 293, 70, 22);
		frmPrincipal.getContentPane().add(Mes);

		int anoAtual = Year.now().getValue();
		Integer[] ano = new Integer[anoAtual - 1999];
		for (int i = 0; i <= anoAtual - 2000; i++) {
			ano[i] = anoAtual - i;
		}

		JComboBox<Integer> Ano = new JComboBox<>(ano);
		Ano.setBounds(294, 293, 70, 22);
		frmPrincipal.getContentPane().add(Ano);
		frmPrincipal.setBounds(100, 100, 656, 581);
		frmPrincipal.setLocationRelativeTo(null);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ActionListener atualizarDias = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int mes = Mes.getSelectedIndex() + 1;
				int ano = (int) Ano.getSelectedItem();

				int diasNoMes;
				switch (mes) {
				case 2: //
					diasNoMes = (isAnoBissexto(ano)) ? 29 : 28;
					break;
				case 4:
				case 6:
				case 9:
				case 11:
					diasNoMes = 30;
					break;
				default:
					diasNoMes = 31;
				}

				Dia.removeAllItems();
				for (int i = 1; i <= diasNoMes; i++) {
					Dia.addItem(i);
				}
			}

			private boolean isAnoBissexto(int ano) {
				return (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);
			}
		};

		Mes.addActionListener(atualizarDias);
		Ano.addActionListener(atualizarDias);

		Mes.setSelectedIndex(0);
		Ano.setSelectedIndex(0);
		
		//CheckBoxes
		JCheckBox PagoSim = new JCheckBox("Sim");
		PagoSim.setFont(new Font("Segoe UI", Font.BOLD, 14));
		PagoSim.setBackground(new Color(244, 209, 204));
		PagoSim.setBounds(128, 237, 60, 23);
		frmPrincipal.getContentPane().add(PagoSim);
		

		JCheckBox PagoNao = new JCheckBox("Não");
		PagoNao.setFont(new Font("Segoe UI", Font.BOLD, 14));
		PagoNao.setBackground(new Color(244, 209, 204));
		PagoNao.setBounds(205, 237, 60, 23);
		frmPrincipal.getContentPane().add(PagoNao);
		

		JCheckBox RetornoSim = new JCheckBox("Sim");
		RetornoSim.setFont(new Font("Segoe UI", Font.BOLD, 14));
		RetornoSim.setBackground(new Color(244, 209, 204));
		RetornoSim.setBounds(129, 265, 60, 23);
		frmPrincipal.getContentPane().add(RetornoSim);

		JCheckBox RetornoNao = new JCheckBox("Não");
		RetornoNao.setFont(new Font("Segoe UI", Font.BOLD, 14));
		RetornoNao.setBackground(new Color(244, 209, 204));
		RetornoNao.setBounds(206, 265, 60, 23);
		frmPrincipal.getContentPane().add(RetornoNao);
		
		RetornoSim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (RetornoSim.isSelected()) {
			        RetornoNao.setSelected(false);  
			        retorno = true;
			    }
				
			}
		});
		
		RetornoNao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (RetornoNao.isSelected()) {
			        RetornoSim.setSelected(false);  
			        retorno = false;
			    }
				
			}
		});
		
		PagoSim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (PagoSim.isSelected()) {
			        PagoNao.setSelected(false); 
			        pago = true;
			    }
				
			}
		});
		
		PagoNao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (PagoNao.isSelected()) {
			        PagoSim.setSelected(false);  
			        pago = false;
			    }
				
			}
		});
		//Fim das CheckBoxes
		
		

		
		@SuppressWarnings("deprecation")
		NumberFormat formatoNumero = NumberFormat.getNumberInstance(new Locale("US"));
		formatoNumero.setMinimumFractionDigits(2);
		formatoNumero.setMaximumFractionDigits(2);
		formatoNumero.setGroupingUsed(true);

		NumberFormatter formatter = new NumberFormatter(formatoNumero);
		formatter.setAllowsInvalid(false);
		formatter.setMinimum(0.0);

		CustoTotal = new JFormattedTextField(formatter);
		CustoTotal.setFont(new Font("Segoe UI", Font.BOLD, 14));
		CustoTotal.setBounds(123, 198, 238, 32);
		frmPrincipal.getContentPane().add(CustoTotal);
		
		
		
		DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
		horaAtual = LocalTime.now().format(formatoHora);

		JLabel lbHoraValor = new JLabel(horaAtual);
		lbHoraValor.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lbHoraValor.setBounds(563, 327, 52, 23); 
		frmPrincipal.getContentPane().add(lbHoraValor);
		atualizarDias.actionPerformed(null);
		
		JLabel lblNewLabel = new JLabel("Atendimento");
        lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 23));
        lblNewLabel.setBounds(21, 11, 292, 32);
        frmPrincipal.getContentPane().add(lblNewLabel);
        
        JLabel IMGgato = new JLabel("");
        IMGgato.setIcon(new ImageIcon("D:\\AAI POO tudo\\AAI-POO 16_06_2025\\AAI-POO-11_06_2025-20250616T131620Z-1-001\\AAI-POO-11_06_2025\\AAI\\TRABALHO\\src\\imagem\\Gato (2).png"));
        IMGgato.setBounds(200, 0, 114, 93);
        frmPrincipal.getContentPane().add(IMGgato);
        
        JButton btnGravar = new JButton("Gravar");
        btnGravar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    cpf = ftxtcpfTutor.getText().replaceAll("[^\\d]", "");
                    if (!Funcoes.validarCpf(cpf)) {
                        JOptionPane.showMessageDialog(frmPrincipal, "CPF inválido!");
                        return;
                    }
                    
                    
                    atendimento.setCpf(cpf);
                    
                    // Chama buscaPorCpf para setar codPet dentro do atendimento
                    controller.buscaPorCpf(atendimento);
                    
                    controller.buscaNomePet(comboPet, cpf); // Atualiza comboPet com base no CPF

                    relatorio = txtRelatorio.getText();
                    custoTotal = Float.parseFloat(CustoTotal.getText());

                    String petSelecionado = (String) comboPet.getSelectedItem();
                    codPet = controller.pegarCodPet(comboPet, petSelecionado);

                    String vetSelecionado = (String) comboVet.getSelectedItem();
                    codVet = controller.pegarCodVet(comboVet, vetSelecionado);
                    
                    
                    int dia = (int) Dia.getSelectedItem();
                    int mes = Mes.getSelectedIndex() + 1;
                    int ano = (int) Ano.getSelectedItem();
                    data = String.format("%04d-%02d-%02d", ano, mes, dia);

                    horaAtual = lbHoraValor.getText();
                    System.out.println("codPet (codanimal) antes do insert: " + atendimento.getCodPet());
                    salvar();

                    JOptionPane.showMessageDialog(frmPrincipal, "Atendimento registrado com sucesso!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmPrincipal, "Erro ao gravar atendimento: " + ex.getMessage());
                }
            }
        });
        btnGravar.setBounds(492, 502, 123, 23);
        frmPrincipal.getContentPane().add(btnGravar);
        btnGravar.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnGravar.setFocusPainted(false);
		btnGravar.setContentAreaFilled(false);
		btnGravar.setOpaque(true);
		btnGravar.setBackground(new Color(98, 68, 47));
		btnGravar.setForeground(Color.WHITE);
		
		JButton btnVoltarAoMenu = new JButton("Voltar ao Menu");
		btnVoltarAoMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaMenu();
				frmPrincipal.dispose();
			}
		});
		btnVoltarAoMenu.setBounds(178, 502, 123, 23);
		frmPrincipal.getContentPane().add(btnVoltarAoMenu);
		btnVoltarAoMenu.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnVoltarAoMenu.setFocusPainted(false);
		btnVoltarAoMenu.setContentAreaFilled(false);
		btnVoltarAoMenu.setOpaque(true);
		btnVoltarAoMenu.setBackground(new Color(98, 68, 47));
		btnVoltarAoMenu.setForeground(Color.WHITE);
		
		JButton btnLimparCampos = new JButton("Limpar Campos");
		btnLimparCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar(frmPrincipal);
			}
		});
		btnLimparCampos.setBounds(333, 502, 123, 23);
		btnLimparCampos.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnLimparCampos.setFocusPainted(false);
		btnLimparCampos.setContentAreaFilled(false);
		btnLimparCampos.setOpaque(true);
		btnLimparCampos.setBackground(new Color(98, 68, 47));
		btnLimparCampos.setForeground(Color.WHITE);
		frmPrincipal.getContentPane().add(btnLimparCampos);
		
		JLabel lbNomeDoPet = new JLabel("Nome do pet  :");
		lbNomeDoPet.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lbNomeDoPet.setBounds(10, 134, 114, 14);
		frmPrincipal.getContentPane().add(lbNomeDoPet);
		
		JLabel lbCpfCliente = new JLabel("CPF do Tutor :");
		lbCpfCliente.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lbCpfCliente.setBounds(15, 102, 114, 14);
		frmPrincipal.getContentPane().add(lbCpfCliente);
		
		
		ftxtcpfTutor = new JFormattedTextField();
		ftxtcpfTutor.addFocusListener(new java.awt.event.FocusAdapter() {
		    public void focusLost(java.awt.event.FocusEvent evt) {
		        String cpfDigitado = ftxtcpfTutor.getText().replaceAll("[^\\d]", "");
		        if (Funcoes.validarCpf(cpfDigitado)) {
		            cpf = cpfDigitado;
		            controller.buscaNomePet(comboPet, cpf);
		        } else {
		            comboPet.removeAllItems();
		        }
		    }
		});
		ftxtcpfTutor.setBounds(123, 101, 203, 20);
		frmPrincipal.getContentPane().add(ftxtcpfTutor);
		
		
		ftxtcpfTutor.setText(Funcoes.FormatarCpf(ftxtcpfTutor.getText()));
		
		
		JButton btnListarAtendimentos = new JButton("Listar Atendimentos");
		btnListarAtendimentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RelatorioAtendimento relatorioatendimento = new RelatorioAtendimento();
				relatorioatendimento.abrir();
				frmPrincipal.dispose();

			}
		});
		btnListarAtendimentos.setOpaque(true);
		btnListarAtendimentos.setForeground(Color.WHITE);
		btnListarAtendimentos.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnListarAtendimentos.setFocusPainted(false);
		btnListarAtendimentos.setBackground(new Color(98, 68, 47));
		btnListarAtendimentos.setBounds(10, 502, 137, 23);
		frmPrincipal.getContentPane().add(btnListarAtendimentos);
		
		
		comboPet.setBounds(123, 132, 221, 22);
		frmPrincipal.getContentPane().add(comboPet);
		controller.buscaNomePet(comboPet, cpf);
		String PetSelecionado = (String) comboPet.getSelectedItem();
		codVet = controller.pegarCodPet(comboPet, PetSelecionado);
		
		JLabel lblVeterinario = new JLabel("Veterinario :");
		lblVeterinario.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblVeterinario.setBounds(30, 167, 94, 14);
		frmPrincipal.getContentPane().add(lblVeterinario);
		
		
		comboVet.setBounds(123, 165, 221, 22);
		frmPrincipal.getContentPane().add(comboVet);
		controller.buscaPorVet(comboVet);
		String vetSelecionado = (String) comboVet.getSelectedItem();
		codVet = controller.pegarCodVet(comboVet, vetSelecionado);

		
		
		
		
		
		
		
		

	}
	
	
	void naoEVazioESalvar(String string, String string1, String string2, JComboBox<String> combo, JButton btnGravar) {
		if (string.trim().isEmpty() && string1.trim().isEmpty() && string2.trim().isEmpty() && combo.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(frmPrincipal, "Todos os campos são obrigatórios!", "Atenção",
					JOptionPane.WARNING_MESSAGE);
		} else {
			try {
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
		controller.salvar(codVet, atendimento.getCodPet(), data, horaAtual, custoTotal, pago, retorno, relatorio);
	}
	
	
	void limpar(Container container) {
	    for (Component component : container.getComponents()) {
	        if (component instanceof JCheckBox) {
	            ((JCheckBox) component).setSelected(false);
	        } else if (component instanceof JComboBox<?>) {
	            ((JComboBox<?>) component).setSelectedIndex(0);
	        }else if (component instanceof JFormattedTextField) {
	                JFormattedTextField field = (JFormattedTextField) component;
	                field.setValue(null);
	                field.setText(""); // <- Garante que a máscara desapareça visualmente
	        } else if (component instanceof JTextField) {
	            ((JTextField) component).setText("");
	        } else if (component instanceof JTextArea) {
	            ((JTextArea) component).setText("");
	        } else if (component instanceof Container) {
	            limpar((Container) component);
	        }
	    }
	}
}