package model;

public class Cliente {
	private String cpf;
	private String clientes;
	private String contato;
	
	
	
	public Cliente(String cpf, String clientes, String contato) {
		super();
		this.cpf = cpf;
		this.clientes = clientes;
		this.contato = contato;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getclientes() {
		return clientes;
	}
	public void setclientes(String clientes) {
		this.clientes = clientes;
	}
	public String getContato() {
		return contato;
	}
	public void setContato(String contato) {
		this.contato = contato;
	}
	
	
	
}
