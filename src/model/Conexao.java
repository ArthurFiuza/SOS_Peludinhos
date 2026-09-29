package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;
public class Conexao {
	// Sintaxe correta para parâmetros na URL do PostgreSQL
	private static String Url = "jdbc:postgresql://localhost:5432/postgres"
			+ "?user=Fiuza&password=&ssl=false&loginTimeout=30";

			//"jdbc:sqlserver://DESKTOP-E47HG2N:1433;databaseName=DA123_Exerc_G03;integratedSecurity=true;encrypt=false;";
	

	public static Connection conexao; // Conecta com o banco

	public static void conectar() { // Efetua a conexão
		try {
			// Conexão com o banco
			conexao = DriverManager.getConnection(Url);		
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro de conexão!\nERRO: " + ex.getMessage());
		}
	}

	public static void desconectar() { // Fecha a conexão
		try {
			conexao.close(); // Fechar conexão
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão!\nERRO: " + ex.getMessage());
		}
	}

}
