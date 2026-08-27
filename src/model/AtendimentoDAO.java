package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;

public class AtendimentoDAO {
	
	public void salvar(Atendimento atendimento) {
		String sql = "INSERT INTO atendimento (codvet, codanimal, data1, hora, custototal, pago, retorno, relatorio) "
				+ "VALUES "
				+ "(?, ?, ?, ?, ?, ?, ?, ?);";
		Conexao.conectar();
		try(Connection coon = Conexao.conexao;
			PreparedStatement stmt = coon.prepareStatement(sql)){
			stmt.setInt(1, atendimento.getCodVet());
			stmt.setInt(2, atendimento.getCodPet());
			stmt.setString(3, atendimento.getData());
			stmt.setString(4, atendimento.getHora());
			stmt.setFloat(5, atendimento.getCustoTotal());
			stmt.setBoolean(6, atendimento.isPago());
			stmt.setBoolean(7, atendimento.isRetorno());
			stmt.setString(8, atendimento.getRelatorio());
			
			stmt.executeUpdate();
			
		}catch (SQLException e) {
			System.err.println("Erro ao salvar atendimento:");
			e.printStackTrace();
		} finally {
			Conexao.desconectar();
		}
				
				
	}
	
	public void buscaPorCpf(Atendimento atendimento) {
		
		String sql = "Select a.codanimal as [codanimal] "
				+ "From animal a "
				+ "inner join ClientexAnimal cx "
				+ "on a.codanimal = cx.codanimal "
				+ "inner join Cliente c "
				+ "on cx.codcliente = c.codcliente "
				+ "where c.cpf = ?;";
		Conexao.conectar();
		try(Connection coon = Conexao.conexao;
				PreparedStatement stmt = coon.prepareStatement(sql)){
				
				stmt.setString(1, atendimento.getCpf());
				
				
				 try (ResultSet rs = stmt.executeQuery()) {
			            if (rs.next()) {
			               atendimento.setCodPet(rs.getInt("codanimal"));
			            }
			        }
				Conexao.desconectar();
			}catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public void buscaNomePet(JComboBox<String> comboPet, String cpf) {
		
		String sql = "Select nomeanimal "
				+ "from animal a "
				+ "inner join clientexanimal cx "
				+ "on a.codanimal = cx.codanimal "
				+ "inner join cliente c "
				+ "on cx.codcliente = c.codcliente "
				+ "where cpf = ?; ";
		
		comboPet.removeAllItems();
		comboPet.addItem("Selecione..."); // Item padrão

		Conexao.conectar();
		 try (Connection coon = Conexao.conexao) {
			 PreparedStatement stmt = coon.prepareStatement(sql);
			 stmt.setString(1, cpf);
	         ResultSet rs = stmt.executeQuery();
		        while (rs.next()) {
		        	comboPet.addItem(rs.getString("nomeanimal"));
		        }

		    } catch (SQLException e) {
		        System.err.println("Erro ao carregar Nomes:");
		        e.printStackTrace();
		    } finally {
		        Conexao.desconectar();
		    }
	}
	
	public void buscaPorVet(JComboBox<String> comboVet) {
		
		String sql = "Select veterinario "
				+ "from Veterinario; ";
		
		comboVet.removeAllItems();
		comboVet.addItem("Selecione..."); // Item padrão
		
		Conexao.conectar();
		 try (Connection coon = Conexao.conexao;
		         PreparedStatement stmt = coon.prepareStatement(sql);
		         ResultSet rs = stmt.executeQuery()) {

		        while (rs.next()) {
		        	comboVet.addItem(rs.getString("veterinario"));
		        }
		        

		    } catch (SQLException e) {
		        System.err.println("Erro ao carregar veterinarios:");
		        e.printStackTrace();
		    } finally {
		        Conexao.desconectar();
		    }
	}
	
	public int pegarCodVet(JComboBox<String> comboVet, String nomeVet) {
		
		String sql = "Select codvet "
				+ "from Veterinario "
				+ "where veterinario = ?";
		int codvet = 0;
		
		Conexao.conectar();
		
		try (Connection conn = Conexao.conexao;
		         PreparedStatement stmt = conn.prepareStatement(sql)) {
		        
		        stmt.setString(1, nomeVet);  // Passa o nomeVet para o ?

		        try (ResultSet rs = stmt.executeQuery()) {
		            if (rs.next()) {
		                codvet = rs.getInt("codvet");
		               
		            }
		        }

		    } catch (SQLException e) {
		        System.err.println("Erro ao buscar codvet:");
		        e.printStackTrace();
		    } finally {
		        Conexao.desconectar();
		    }
		return codvet;
		
	}
	
	public int pegarCodPet(JComboBox<String> comboPet, String nomePet) {
		
		String sql = "Select codanimal "
				+ "from animal "
				+ "where nomeanimal = ? ";
		int codPet = 0;
		
		Conexao.conectar();
		
		try (Connection conn = Conexao.conexao;
		         PreparedStatement stmt = conn.prepareStatement(sql)) {
		        
		        stmt.setString(1, nomePet);  // Passa o nomePet para o ?

		        try (ResultSet rs = stmt.executeQuery()) {
		            if (rs.next()) {
		                codPet = rs.getInt("codanimal");
		               
		            }
		        }

		    } catch (SQLException e) {
		        System.err.println("Erro ao buscar codanimal:");
		        e.printStackTrace();
		    } finally {
		        Conexao.desconectar();
		    }
		return codPet;
		
	}
	
	
	

}
