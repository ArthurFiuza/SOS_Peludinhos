package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexao {
	private static String Url = "jdbc:sqlserver://10.109.8.9:1433;databaseName=DA123_Exerc_G03;"
			+ "user=DA123_Exerc_G03;password=;" + "encrypt=false;trustServerCertificate=true;loginTimeout=30;";
			
			
			//"jdbc:sqlserver://DESKTOP-E47HG2N:1433;databaseName=DA123_Exerc_G03;integratedSecurity=true;encrypt=false;";
	

	public static Connection conexao; // Conecta com o banco

	public static void conectar() { // Efetua a conexão
		try {
			// Conexão com o banco
			conexao = DriverManager.getConnection(Url);		
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro deconexão!\nERRO: " + ex.getMessage());
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
