package controller;

import javax.swing.JComboBox;

import model.Animal;
import model.AnimalDAO;


public class AnimalController {
	model.AnimalDAO dao = new AnimalDAO();
	
	public void salvar(String nomeAnimal, String DTNascimento, int codRaca) {
		Animal animal = new Animal(nomeAnimal, DTNascimento, codRaca);
		dao.salvar(animal, codRaca);
	}
	
	public void salvarClientexAnimal(String cpfCliente) {
		Animal clientexanimal = new Animal (cpfCliente);
		dao.salvarClientexAnimal(clientexanimal);
	}
	
	public void SelectRaca(JComboBox<String> comboRaca, int especie) {
		dao.SelectRaca(comboRaca, especie);
		
	}
	
	public void SelectEspecie(JComboBox<String> comboEspecie) {
		dao.SelectEspecie(comboEspecie);
		
	}
	public int PegarCodigoRacaPeloNome(JComboBox<String> comboRaca, String nomeRaca) {
	    return dao.PegarCodigoRaçaPeloNome(comboRaca, nomeRaca);
	}
	
	
}