package view;

public class Funcoes {
	
	public static boolean validarCpf(String cpf) {
		cpf = cpf.replaceAll("[^0-9]", "");
		return cpf.length() == 11;
	}

	public static boolean validarNumero(String numero) {
		numero = numero.replaceAll("[^0-9]", "");
		return numero.length() >= 10;
	}

	public static String FormatarCpf(String cpf) {

		cpf = cpf.replaceAll("[^0-9]", "");

		return cpf;

	}

	public static String FormatarContato(String contato) {

		contato = contato.replaceAll("[^0-9]", "");

		return contato;

	}

}
