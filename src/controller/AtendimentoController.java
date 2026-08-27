package controller;

import javax.swing.JComboBox;

import model.Atendimento;
import model.AtendimentoDAO;

public class AtendimentoController {
	model.AtendimentoDAO dao = new AtendimentoDAO();
	
	
	public void salvar(int codVet, int codPet, String data, String hora, float custoTotal, boolean pago, boolean retorno, String relatorio) {
		Atendimento atendimento = new Atendimento(codVet, codPet, data, hora, custoTotal, pago, retorno, relatorio);
		dao.salvar(atendimento);
	}
	
	public void buscaNomePet(JComboBox<String> comboPet, String cpf) {
		dao.buscaNomePet(comboPet, cpf);
		
	}
	
	public void buscaPorVet(JComboBox<String> comboVet) {
		dao.buscaPorVet(comboVet);
	}
	
	public void buscaPorCpf(Atendimento atendimento) {
		dao.buscaPorCpf(atendimento);
	}
	
	public int pegarCodVet(JComboBox<String> comboVet, String nomeVet) {
		return dao.pegarCodVet(comboVet, nomeVet);
	}
	
	public int pegarCodPet(JComboBox<String> comboPet, String nomePet) {
		return dao.pegarCodPet(comboPet, nomePet);
	}
	


}
