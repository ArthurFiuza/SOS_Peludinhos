package model;

public class Atendimento {
    String cpf;
    String contato;
    String nomeanimal;
    String veterinario;

    
    String dataAtendimento;
    
    int codVet;
    int codPet;
    String data;
    String hora;
    float custoTotal;
    boolean pago;
    boolean retorno;
    String relatorio;
    

    
    
	public Atendimento(String cpf, String contato, String nomeanimal, String veterinario, boolean pago, boolean retorno,
			String dataAtendimento) {
		super();
		this.cpf = cpf;
		this.contato = contato;
		this.nomeanimal = nomeanimal;
		this.veterinario = veterinario;
		this.pago = pago;
		this.retorno = retorno;
		this.dataAtendimento = dataAtendimento;
	}
	
	
	public Atendimento(int codVet, int codPet, String data, String hora, float custoTotal, boolean pago, boolean retorno, String relatorio) {
		this.codVet = codVet;
		this.codPet = codPet;
		this.data = data;
		this.hora = hora;
		this.custoTotal = custoTotal;
		this.pago = pago;
		this.retorno = retorno;
		this.relatorio = relatorio;
	}
	
	public Atendimento() {
		
	}
	
	
	
	
	public String getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(String relatorio) {
		this.relatorio = relatorio;
	}

	public String getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(String dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public int getCodVet() {
		return codVet;
	}

	public void setCodVet(int codVet) {
		this.codVet = codVet;
	}

	public int getCodPet() {
		return codPet;
	}

	public void setCodPet(int codPet) {
		this.codPet = codPet;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public float getCustoTotal() {
		return custoTotal;
	}

	public void setCustoTotal(float custoTotal) {
		this.custoTotal = custoTotal;
	}

	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getContato() {
		return contato;
	}
	public void setContato(String contato) {
		this.contato = contato;
	}
	public String getNomeanimal() {
		return nomeanimal;
	}
	public void setNomeanimal(String nomeanimal) {
		this.nomeanimal = nomeanimal;
	}
	public String getVeterinario() {
		return veterinario;
	}
	public void setVeterinario(String veterinario) {
		this.veterinario = veterinario;
	}
	public boolean isPago() {
		return pago;
	}
	public void setPago(boolean pago) {
		this.pago = pago;
	}
	public boolean isRetorno() {
		return retorno;
	}
	public void setRetorno(boolean retorno) {
		this.retorno = retorno;
	}
	

    
    

}
