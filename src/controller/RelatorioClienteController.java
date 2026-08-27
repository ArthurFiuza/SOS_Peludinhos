package controller;

import model.Cliente;
import model.RelatorioClienteDAO;
import model.Conexao;

import java.util.List;

public class RelatorioClienteController {
    private RelatorioClienteDAO relatorioClienteDAO;

    public RelatorioClienteController() {
        Conexao.conectar();
        this.relatorioClienteDAO = new RelatorioClienteDAO(Conexao.conexao);
    }

    public List<Cliente> getTodosClientes() {
        return relatorioClienteDAO.getAllClientes();
    }

    public Cliente getClientePorCpf(String cpf) {
        return relatorioClienteDAO.getClientePorCpf(cpf);
    }

    public void atualizarCliente(String cpfAntigo, Cliente cliente) {
        relatorioClienteDAO.atualizarCliente(cpfAntigo, cliente);
    }

    public void excluirCliente(String cpf) {
        relatorioClienteDAO.excluirCliente(cpf);
    }

    public void desconectar() {
        Conexao.desconectar();
    }
}
