package view;

import controller.RelatorioAtendimentoController;
import model.Atendimento;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RelatorioAtendimento {

    private JFrame RelatorioAtendimento;
    private JTable tabelaAtendimentos;
    private DefaultTableModel modeloTabela;
    private RelatorioAtendimentoController controller;
    Atendimento atendimento = new Atendimento();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                RelatorioAtendimento window = new RelatorioAtendimento();
                window.RelatorioAtendimento.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public RelatorioAtendimento() {
        controller = new RelatorioAtendimentoController();
        initialize();
    }

    private void initialize() {
        RelatorioAtendimento = new JFrame();
        RelatorioAtendimento.setResizable(false);
        RelatorioAtendimento.getContentPane().setBackground(new Color(244, 209, 204));
        RelatorioAtendimento.setTitle("Relatório de Clientes");
        RelatorioAtendimento.setBounds(100, 100, 650, 450);
        RelatorioAtendimento.setLocationRelativeTo(null);
        RelatorioAtendimento.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RelatorioAtendimento.getContentPane().setLayout(null);

        modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("CPF");
        modeloTabela.addColumn("Contato");
        modeloTabela.addColumn("Nome do pet");
        modeloTabela.addColumn("Veterinário");
        modeloTabela.addColumn("Pago");
        modeloTabela.addColumn("Retorno");
        modeloTabela.addColumn("Data");

        tabelaAtendimentos = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaAtendimentos);
        scrollPane.setBounds(10, 10, 610, 300);
        
        RelatorioAtendimento.getContentPane().add(scrollPane);

        JButton btnCarregar = new JButton("Carregar Lista");
        btnCarregar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCarregar.setFocusPainted(false);
        btnCarregar.setBackground(new Color(98, 68, 47));
        btnCarregar.setForeground(Color.WHITE);
        btnCarregar.setBounds(10, 320, 150, 30);
        btnCarregar.addActionListener(e -> carregarRelatorio());
        RelatorioAtendimento.getContentPane().add(btnCarregar);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnAtualizar.setFocusPainted(false);
        btnAtualizar.setBackground(new Color(44, 102, 54));
        btnAtualizar.setForeground(Color.WHITE);
        btnAtualizar.setBounds(230, 321, 150, 30);
        btnAtualizar.addActionListener(e -> atualizarAtendimentoSelecionado());
        RelatorioAtendimento.getContentPane().add(btnAtualizar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnExcluir.setFocusPainted(false);
        btnExcluir.setBackground(new Color(150, 30, 30));
        btnExcluir.setForeground(Color.WHITE);
        btnExcluir.setBounds(442, 321, 150, 30);
        btnExcluir.addActionListener(e -> excluirAtendimentoSelecionado());
        RelatorioAtendimento.getContentPane().add(btnExcluir);
        
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new TelaAtendimento();
        		RelatorioAtendimento.dispose();
        	}
        });
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVoltar.setFocusPainted(false);
        btnVoltar.setBackground(new Color(98, 68, 47));
        btnVoltar.setBounds(10, 372, 150, 30);
        RelatorioAtendimento.getContentPane().add(btnVoltar);
        
        JButton btnExibirRelatorio = new JButton("ExibirRelatorio");
        btnExibirRelatorio.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	
                            int linhaSelecionada = tabelaAtendimentos.getSelectedRow();
                            String cpf = (String) modeloTabela.getValueAt(linhaSelecionada, 0);
                            String dataOriginal = (String) modeloTabela.getValueAt(linhaSelecionada, 6);
                            LocalDate data = LocalDate.parse(dataOriginal); // yyyy-MM-dd
                            String dataFormatada = data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                            
                            atendimento.setCpf(cpf);
                            atendimento.setData(dataFormatada);
                            
                           String relatorio = controller.pegarRelatorio(atendimento.getRelatorio(),  atendimento.getData(), atendimento.getCpf());

                            Atendimento atendimento = controller.getAtendimentoPorCpf(cpf);
                            if (atendimento != null) {
                          
                                if (relatorio == null || relatorio.isBlank()) {
                                    relatorio = "Sem relatório registrado para este atendimento.";
                                }

                                JTextArea areaTexto = new JTextArea(relatorio);
                                areaTexto.setEditable(false);
                                areaTexto.setWrapStyleWord(true);
                                areaTexto.setLineWrap(true);
                                areaTexto.setCaretPosition(0);

                                JScrollPane scrollPane = new JScrollPane(areaTexto);
                                scrollPane.setPreferredSize(new Dimension(400, 200));

                                JOptionPane.showMessageDialog(RelatorioAtendimento, scrollPane, "Relatório do Atendimento", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(RelatorioAtendimento, "Relatório não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                            }
                        
        	}
        });
        btnExibirRelatorio.setForeground(Color.WHITE);
        btnExibirRelatorio.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnExibirRelatorio.setFocusPainted(false);
        btnExibirRelatorio.setBackground(new Color(98, 68, 47));
        btnExibirRelatorio.setBounds(230, 372, 150, 30);
        RelatorioAtendimento.getContentPane().add(btnExibirRelatorio);
    }

    private void carregarRelatorio() {
        List<Atendimento> atendimentos = controller.getTodosAtendimentos();
        modeloTabela.setRowCount(0);

        if (atendimentos.isEmpty()) {
            JOptionPane.showMessageDialog(RelatorioAtendimento, "Nenhum Atendimento encontrado.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            for (Atendimento a : atendimentos) {
                modeloTabela.addRow(new Object[]{
                    a.getCpf(),
                    a.getContato(),
                    a.getNomeanimal(),
                    a.getVeterinario(),
                    a.isPago() ? "Sim" : "Não",
                    a.isRetorno() ? "Sim" : "Não",
                    a.getDataAtendimento()
                });
            }
        }
    }

    private void atualizarAtendimentoSelecionado() {
        int linhaSelecionada = tabelaAtendimentos.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(RelatorioAtendimento, "Selecione um atendimento para atualizar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String cpfAntigo = (String) modeloTabela.getValueAt(linhaSelecionada, 0);
        Atendimento atendimentoAtual = controller.getAtendimentoPorCpf(cpfAntigo);
        if (atendimentoAtual == null) {
            JOptionPane.showMessageDialog(RelatorioAtendimento, "Atendimento não encontrado para atualização.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String novoCpf = JOptionPane.showInputDialog(RelatorioAtendimento, "Novo CPF:", atendimentoAtual.getCpf());
        if (novoCpf == null || novoCpf.trim().isEmpty()) return;

        String novoContato = JOptionPane.showInputDialog(RelatorioAtendimento, "Novo Contato:", atendimentoAtual.getContato());
        if (novoContato == null || novoContato.trim().isEmpty()) return;

        String novoNomeAnimal = JOptionPane.showInputDialog(RelatorioAtendimento, "Novo Nome do Pet:", atendimentoAtual.getNomeanimal());
        if (novoNomeAnimal == null || novoNomeAnimal.trim().isEmpty()) return;

        String novoVeterinario = JOptionPane.showInputDialog(RelatorioAtendimento, "Novo Veterinário:", atendimentoAtual.getVeterinario());
        if (novoVeterinario == null || novoVeterinario.trim().isEmpty()) return;

        int pago = JOptionPane.showConfirmDialog(RelatorioAtendimento, "O atendimento foi pago?", "Pago", JOptionPane.YES_NO_OPTION);
        boolean isPago = (pago == JOptionPane.YES_OPTION);

        int retorno = JOptionPane.showConfirmDialog(RelatorioAtendimento, "O atendimento é retorno?", "Retorno", JOptionPane.YES_NO_OPTION);
        boolean isRetorno = (retorno == JOptionPane.YES_OPTION);

        String novaData = JOptionPane.showInputDialog(RelatorioAtendimento, "Nova Data (YYYY-MM-DD):", atendimentoAtual.getDataAtendimento());
        if (novaData == null || novaData.trim().isEmpty()) return;

        Atendimento atendimentoAtualizado = new Atendimento(
                novoCpf.trim(), novoContato.trim(), novoNomeAnimal.trim(),
                novoVeterinario.trim(), isPago, isRetorno, novaData.trim()
        );

        controller.atualizarAtendimento(cpfAntigo, atendimentoAtualizado);
        carregarRelatorio();
        JOptionPane.showMessageDialog(RelatorioAtendimento, "Atendimento atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void excluirAtendimentoSelecionado() {
        int linhaSelecionada = tabelaAtendimentos.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(RelatorioAtendimento, "Selecione um Atendimento para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String cpf = (String) modeloTabela.getValueAt(linhaSelecionada, 0);
        int confirmar = JOptionPane.showConfirmDialog(RelatorioAtendimento,
                "Deseja realmente excluir o atendimento com CPF: " + cpf + "?",
                "Confirmação", JOptionPane.YES_NO_OPTION);

        if (confirmar == JOptionPane.YES_OPTION) {
            controller.excluirAtendimento(cpf);
            carregarRelatorio();
            JOptionPane.showMessageDialog(RelatorioAtendimento, "Atendimento excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void abrir() {
        RelatorioAtendimento.setVisible(true);
    }
}


