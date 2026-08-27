package view;

import controller.RelatorioAnimalController;
import model.Animal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RelatorioAnimal {

    private JFrame frame;
    private JTable tabelaAnimais;
    private DefaultTableModel modeloTabela;
    private RelatorioAnimalController controller;

    private JComboBox<String> comboEspecies;
    private JComboBox<String> comboRacas;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                RelatorioAnimal window = new RelatorioAnimal();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public RelatorioAnimal() {
        controller = new RelatorioAnimalController();
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(244, 209, 204));
        frame.setTitle("Relatório de Animais");
        frame.setBounds(100, 100, 650, 450);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("CPF Cliente");
        modeloTabela.addColumn("Nome Animal");
        modeloTabela.addColumn("Data Nascimento");
        modeloTabela.addColumn("Raça");

        tabelaAnimais = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaAnimais);
        scrollPane.setBounds(10, 10, 610, 300);
        frame.getContentPane().add(scrollPane);

        JButton btnCarregar = new JButton("Carregar Lista");
        btnCarregar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCarregar.setFocusPainted(false);
        btnCarregar.setBackground(new Color(98, 68, 47));
        btnCarregar.setForeground(Color.WHITE);
        btnCarregar.setBounds(10, 320, 150, 30);
        btnCarregar.addActionListener(e -> carregarRelatorio());
        frame.getContentPane().add(btnCarregar);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnAtualizar.setFocusPainted(false);
        btnAtualizar.setBackground(new Color(44, 102, 54));
        btnAtualizar.setForeground(Color.WHITE);
        btnAtualizar.setBounds(230, 320, 150, 30);
        btnAtualizar.addActionListener(e -> atualizarAnimalSelecionado());
        frame.getContentPane().add(btnAtualizar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnExcluir.setFocusPainted(false);
        btnExcluir.setBackground(new Color(150, 30, 30));
        btnExcluir.setForeground(Color.WHITE);
        btnExcluir.setBounds(442, 320, 150, 30);
        btnExcluir.addActionListener(e -> excluirAnimalSelecionado());
        frame.getContentPane().add(btnExcluir);

       
        comboEspecies = new JComboBox<>();
        comboEspecies.setEnabled(false);
        comboEspecies.setBounds(592, 392, 0, 0);
        comboEspecies.setVisible(false);
        frame.getContentPane().add(comboEspecies);

        comboRacas = new JComboBox<>();
        comboRacas.setEnabled(false);
        comboRacas.setBounds(620, 361, 0, 0);
        comboRacas.setVisible(false); 
        frame.getContentPane().add(comboRacas);
        
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new TelaAnimal();
        		frame.dispose();        	}
        });
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVoltar.setFocusPainted(false);
        btnVoltar.setBackground(new Color(98, 68, 47));
        btnVoltar.setBounds(10, 361, 150, 30);
        frame.getContentPane().add(btnVoltar);

        
        carregarEspecies();

        comboEspecies.addActionListener(e -> {
            String especieSelecionada = (String) comboEspecies.getSelectedItem();
            if (especieSelecionada != null) {
                carregarRacasPorEspecie(especieSelecionada);
            }
        });
    }

    private void carregarRelatorio() {
        List<Animal> animais = controller.listarAnimais();
        modeloTabela.setRowCount(0);

        if (animais == null || animais.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Nenhum animal encontrado.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            for (Animal animal : animais) {
                modeloTabela.addRow(new Object[]{
                        animal.getCpfCliente(),
                        animal.getNomeAnimal(),
                        animal.getdTNascimentoAnimal(),
                        animal.getRacaNome()
                });
            }
        }
    }

    private void atualizarAnimalSelecionado() {
        int linhaSelecionada = tabelaAnimais.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(frame, "Selecione um animal para atualizar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String cpfAntigo = (String) modeloTabela.getValueAt(linhaSelecionada, 0);
        String nomeAntigo = (String) modeloTabela.getValueAt(linhaSelecionada, 1);

        Animal animalAtual = controller.buscarAnimal(cpfAntigo, nomeAntigo);
        if (animalAtual == null) {
            JOptionPane.showMessageDialog(frame, "Animal não encontrado para atualização.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String novoNome = JOptionPane.showInputDialog(frame, "Novo nome do animal:", animalAtual.getNomeAnimal());
        if (novoNome == null || novoNome.trim().isEmpty()) return;

        String novaData = JOptionPane.showInputDialog(frame, "Nova data de nascimento (AAAA-MM-DD):", animalAtual.getdTNascimentoAnimal());
        if (novaData == null || novaData.trim().isEmpty()) return;

        String novaEspecie = (String) JOptionPane.showInputDialog(
                frame,
                "Selecione a nova espécie:",
                "Atualizar Espécie",
                JOptionPane.PLAIN_MESSAGE,
                null,
                getEspeciesArray(),
                animalAtual.getEspecie());

        if (novaEspecie == null) return;

        List<String> racasPorEspecie = controller.listarRacasPorEspecie(novaEspecie);
        if (racasPorEspecie.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Nenhuma raça encontrada para a espécie selecionada.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String novaRaca = (String) JOptionPane.showInputDialog(
                frame,
                "Selecione a nova raça:",
                "Atualizar Raça",
                JOptionPane.PLAIN_MESSAGE,
                null,
                racasPorEspecie.toArray(new String[0]),
                animalAtual.getRacaNome());

        if (novaRaca == null) return;

        int novoCodRaca = controller.buscarCodRacaPorNome(novaRaca);

        Animal animalAtualizado = new Animal(novoNome.trim(), novaData.trim(), novoCodRaca);
        animalAtualizado.setCpfCliente(cpfAntigo); 
        animalAtualizado.setRacaNome(novaRaca.trim());
        animalAtualizado.setEspecie(novaEspecie.trim());

        String mensagem = controller.atualizarAnimal(cpfAntigo, nomeAntigo, animalAtualizado);

        carregarRelatorio();

        JOptionPane.showMessageDialog(frame, mensagem,
                mensagem.startsWith("Erro") ? "Erro" : "Sucesso",
                mensagem.startsWith("Erro") ? JOptionPane.ERROR_MESSAGE : JOptionPane.INFORMATION_MESSAGE);
    }


    private void excluirAnimalSelecionado() {
        int linhaSelecionada = tabelaAnimais.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(frame, "Selecione um animal para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String cpf = (String) modeloTabela.getValueAt(linhaSelecionada, 0);
        String nomeAnimal = (String) modeloTabela.getValueAt(linhaSelecionada, 1);

        int confirmar = JOptionPane.showConfirmDialog(frame,
                "Deseja realmente excluir o animal \"" + nomeAnimal + "\" do cliente com CPF: " + cpf + "?",
                "Confirmação", JOptionPane.YES_NO_OPTION);

        if (confirmar == JOptionPane.YES_OPTION) {
            String mensagem = controller.excluirAnimal(cpf, nomeAnimal);
            carregarRelatorio();
            JOptionPane.showMessageDialog(frame, mensagem,
                    mensagem.startsWith("Erro") ? "Erro" : "Sucesso",
                    mensagem.startsWith("Erro") ? JOptionPane.ERROR_MESSAGE : JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void carregarEspecies() {
        List<String> especies = controller.listarNomesEspecies();
        comboEspecies.removeAllItems();
        for (String especie : especies) {
            comboEspecies.addItem(especie);
        }
        if (!especies.isEmpty()) {
            comboEspecies.setSelectedIndex(0);
            carregarRacasPorEspecie(especies.get(0));
        }
    }

    private void carregarRacasPorEspecie(String especie) {
        List<String> racas = controller.listarRacasPorEspecie(especie);
        comboRacas.removeAllItems();
        for (String raca : racas) {
            comboRacas.addItem(raca);
        }
    }

    private String[] getEspeciesArray() {
        int count = comboEspecies.getItemCount();
        String[] array = new String[count];
        for (int i = 0; i < count; i++) {
            array[i] = comboEspecies.getItemAt(i);
        }
        return array;
    }
    public void abrir() {
        frame.setVisible(true);
    }
}
