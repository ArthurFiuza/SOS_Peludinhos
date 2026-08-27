package view;

import controller.RelatorioClienteController;
import model.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class RelatorioCliente {

    private JFrame RelatorioCliente;
    private JTable tabelaClientes;
    private DefaultTableModel modeloTabela;
    private RelatorioClienteController controller;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                RelatorioCliente window = new RelatorioCliente();
                window.RelatorioCliente.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public RelatorioCliente() {
        controller = new RelatorioClienteController();
        initialize();
    }

    private void initialize() {
        RelatorioCliente = new JFrame();
        RelatorioCliente.setResizable(false);
        RelatorioCliente.getContentPane().setBackground(new Color(244, 209, 204));
        RelatorioCliente.setTitle("Relatório de Clientes");
        RelatorioCliente.setBounds(100, 100, 650, 450);
        RelatorioCliente.setLocationRelativeTo(null);
        RelatorioCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RelatorioCliente.getContentPane().setLayout(null);

        modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("Nome");
        modeloTabela.addColumn("CPF");
        modeloTabela.addColumn("Contato");

        tabelaClientes = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaClientes);
        scrollPane.setBounds(10, 10, 610, 300);
        RelatorioCliente.getContentPane().add(scrollPane);

        JButton btnCarregar = new JButton("Carregar Lista");
        btnCarregar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCarregar.setFocusPainted(false);
        btnCarregar.setBackground(new Color(98, 68, 47));
        btnCarregar.setForeground(Color.WHITE);
        btnCarregar.setBounds(10, 320, 150, 30);
        btnCarregar.addActionListener(e -> carregarRelatorio());
        RelatorioCliente.getContentPane().add(btnCarregar);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnAtualizar.setFocusPainted(false);
        btnAtualizar.setBackground(new Color(44, 102, 54));
        btnAtualizar.setForeground(Color.WHITE);
        btnAtualizar.setBounds(230, 321, 150, 30);
        btnAtualizar.addActionListener(e -> atualizarClienteSelecionado());
        RelatorioCliente.getContentPane().add(btnAtualizar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnExcluir.setFocusPainted(false);
        btnExcluir.setBackground(new Color(150, 30, 30));
        btnExcluir.setForeground(Color.WHITE);
        btnExcluir.setBounds(442, 321, 150, 30);
        btnExcluir.addActionListener(e -> excluirClienteSelecionado());
        RelatorioCliente.getContentPane().add(btnExcluir);
        
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new TelaCliente();
        		RelatorioCliente.dispose();
        	}
        });
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVoltar.setFocusPainted(false);
        btnVoltar.setBackground(new Color(98, 68, 47));
        btnVoltar.setBounds(10, 372, 150, 30);
        RelatorioCliente.getContentPane().add(btnVoltar);
    }

    private void carregarRelatorio() {
        List<Cliente> clientes = controller.getTodosClientes();
        modeloTabela.setRowCount(0);

        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(RelatorioCliente, "Nenhum cliente encontrado.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            for (Cliente cliente : clientes) {
                modeloTabela.addRow(new Object[]{
                        cliente.getclientes(),
                        cliente.getCpf(),
                        cliente.getContato()
                });
            }
        }
    }

    private void atualizarClienteSelecionado() {
        int linhaSelecionada = tabelaClientes.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(RelatorioCliente, "Selecione um cliente para atualizar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String cpfAntigo = (String) modeloTabela.getValueAt(linhaSelecionada, 1);
        Cliente clienteAtual = controller.getClientePorCpf(cpfAntigo);
        if (clienteAtual == null) {
            JOptionPane.showMessageDialog(RelatorioCliente, "Cliente não encontrado para atualização.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String novoCpf = JOptionPane.showInputDialog(RelatorioCliente, "Novo CPF:", clienteAtual.getCpf());
        if (novoCpf == null || novoCpf.trim().isEmpty()) return;

        String novoNome = JOptionPane.showInputDialog(RelatorioCliente, "Novo nome:", clienteAtual.getclientes());
        if (novoNome == null || novoNome.trim().isEmpty()) return;

        String novoContato = JOptionPane.showInputDialog(RelatorioCliente, "Novo contato:", clienteAtual.getContato());
        if (novoContato == null || novoContato.trim().isEmpty()) return;

        Cliente clienteAtualizado = new Cliente(novoCpf.trim(), novoNome.trim(), novoContato.trim());
        controller.atualizarCliente(cpfAntigo, clienteAtualizado);
        carregarRelatorio();
        JOptionPane.showMessageDialog(RelatorioCliente, "Cliente atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void excluirClienteSelecionado() {
        int linhaSelecionada = tabelaClientes.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(RelatorioCliente, "Selecione um cliente para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String cpf = (String) modeloTabela.getValueAt(linhaSelecionada, 1);

        int confirmar = JOptionPane.showConfirmDialog(RelatorioCliente,
                "Deseja realmente excluir o cliente com CPF: " + cpf + "?",
                "Confirmação", JOptionPane.YES_NO_OPTION);

        if (confirmar == JOptionPane.YES_OPTION) {
            controller.excluirCliente(cpf);
            carregarRelatorio();
            JOptionPane.showMessageDialog(RelatorioCliente, "Cliente excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void abrir() {
        RelatorioCliente.setVisible(true);
    }
}
