package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;

public class AnimalDAO {
	public void salvar(Animal animal, int codraca) {
		String sql = "INSERT INTO Animal (nomeAnimal, DTNascimento, codraca)"
				+ "VALUES (?, ?, ?)";
		Conexao.conectar();
		try(Connection coon = Conexao.conexao;
			PreparedStatement stmt = coon.prepareStatement(sql)){
			stmt.setString(1, animal.getNomeAnimal());
			stmt.setString(2, animal.getdTNascimentoAnimal());
			stmt.setInt(3, codraca);
			stmt.executeUpdate();
			Conexao.desconectar();
		}catch (SQLException e) {
			e.printStackTrace();
		}		
				
	}
	
	public void salvarClientexAnimal(Animal clientexanimal) {
		String sql = "INSERT INTO ClientexAnimal (codcliente, codanimal)\r\n"
				+ "VALUES ((SELECT codcliente from cliente where cpf = ?), (SELECT MAX(codanimal) FROM animal));";
		Conexao.conectar();
		try(Connection coon = Conexao.conexao;
				PreparedStatement stmt = coon.prepareStatement(sql)){
				stmt.setString(1, clientexanimal.getCpfCliente());
				stmt.executeUpdate();
				Conexao.desconectar();
			}catch (SQLException e) {
				e.printStackTrace();
			}		
		}
	
	public void SelectEspecie(JComboBox<String> comboBox) {
		String sql = "SELECT especie FROM especie";


	    comboBox.removeAllItems();
	    comboBox.addItem("Selecione..."); // Item padrão

	    Conexao.conectar();

	    try (Connection coon = Conexao.conexao;
	         PreparedStatement stmt = coon.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            comboBox.addItem(rs.getString("especie"));
	        }

	    } catch (SQLException e) {
	        System.err.println("Erro ao carregar especies:");
	        e.printStackTrace();
	    } finally {
	        Conexao.desconectar();
	    }
	}
	
	
	
	
	
	public void SelectRaca(JComboBox<String> comboBox, int especie) {
	    String sql = "SELECT Raca, codraca FROM Raca where codespecie = " + especie;


	    comboBox.removeAllItems();
	    comboBox.addItem("Selecione..."); // Item padrão

	    Conexao.conectar();

	    try (Connection coon = Conexao.conexao;
	         PreparedStatement stmt = coon.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            comboBox.addItem(rs.getString("Raca"));
	        }

	    } catch (SQLException e) {
	        System.err.println("Erro ao carregar raças:");
	        e.printStackTrace();
	    } finally {
	        Conexao.desconectar();
	    }
	}
	
	public int PegarCodigoRaçaPeloNome(JComboBox<String> comboBox, String nomeRaca) {
	    String sql = "SELECT codraca FROM Raca WHERE Raca = ?";
	    int codRaca = 0;

	    Conexao.conectar();

	    try (Connection coon = Conexao.conexao;
	         PreparedStatement stmt = coon.prepareStatement(sql)) {
	    	stmt.setString(1, nomeRaca);
	         
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                codRaca = rs.getInt("codraca");
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("Erro ao buscar codraca: ");
	        e.printStackTrace();
	    } finally {
	        Conexao.desconectar();
	    }
	    return codRaca;
	}
}