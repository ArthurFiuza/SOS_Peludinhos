package controller;



import model.Cliente;
import model.ClienteDAO;

public class ClienteController {
	model.ClienteDAO dao = new ClienteDAO();
	
	public void salvar(String clientes, String contato, String CPF) {
		Cliente cliente = new Cliente(clientes, contato, CPF);
		dao.salvar(cliente);
	}

	
}
