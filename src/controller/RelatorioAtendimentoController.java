package controller;

import model.*;
import java.util.List;

public class RelatorioAtendimentoController {
    private RelatorioAtendimentoDAO dao;

    public RelatorioAtendimentoController() {
        Conexao.conectar();
        this.dao = new RelatorioAtendimentoDAO(Conexao.conexao);
    }

    public List<Atendimento> getTodosAtendimentos() {
        return dao.getAllAtendimentos();
    }

    public void excluirAtendimento(String cpf) {
        dao.excluirAtendimentoPorCpf(cpf);
    }

    public Atendimento getAtendimentoPorCpf(String cpf) {
        return dao.getAtendimentoPorCpf(cpf);
    }

    public void atualizarAtendimento(String cpfAntigo, Atendimento atendimento) {
        dao.atualizarAtendimento(cpfAntigo, atendimento);
    }

    public void desconectar() {
        Conexao.desconectar();
    }
    
	public String pegarRelatorio(String relatorio, String data, String cpf) {
		return dao.pegarRelatorio(relatorio, data, cpf);
	}
}