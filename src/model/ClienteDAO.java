package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ClienteDAO {
	
	public void salvar(Cliente cliente) {
		String sql = "INSERT INTO Cliente (CPF, cliente, Contato) "
				+ "VALUES (?, ?, ?)";
		Conexao.conectar();
		try(Connection coon = Conexao.conexao;
			PreparedStatement stmt = coon.prepareStatement(sql)){
			stmt.setString(1, cliente.getclientes());
			stmt.setString(2, cliente.getCpf());
			stmt.setString(3, cliente.getContato());
			stmt.executeUpdate();
			Conexao.desconectar();
		}catch (SQLException e) {
			e.printStackTrace();
		}
				
				
	}
	
}
