package model;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class RelatorioAtendimentoDAO {
    private Connection connection;

    public RelatorioAtendimentoDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Atendimento> getAllAtendimentos() {
        List<Atendimento> atendimentos = new ArrayList<>();
        String query = "SELECT c.cpf, c.contato, a.nomeanimal, v.veterinario, ate.pago, ate.retorno, ate.data1 as 'dataAtendimento', CONVERT(time(0), ate.hora) as 'Hora' " +
                       "FROM Cliente c " +
                       "INNER JOIN ClientexAnimal cx ON c.codcliente = cx.codcliente " +
                       "INNER JOIN Animal a ON cx.codanimal = a.codanimal " +
                       "INNER JOIN Atendimento ate ON a.codanimal = ate.codanimal " +
                       "INNER JOIN Veterinario v ON ate.codvet = v.codvet";

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                atendimentos.add(new Atendimento(
                    rs.getString("cpf"),
                    rs.getString("contato"),
                    rs.getString("nomeanimal"),
                    rs.getString("veterinario"),
                    rs.getBoolean("pago"),
                    rs.getBoolean("retorno"),
                    rs.getString("dataAtendimento")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return atendimentos;
    }

    public void excluirAtendimentoPorCpf(String cpf) {
        String sql = "DELETE FROM Atendimento WHERE codanimal IN (SELECT codanimal FROM ClientexAnimal cx INNER JOIN Cliente c ON cx.codcliente = c.codcliente WHERE c.cpf = ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public Atendimento getAtendimentoPorCpf(String cpf) {
        String query = "SELECT c.cpf, c.contato, a.nomeanimal, v.veterinario, ate.pago, ate.retorno, ate.data1 as dataAtendimento "
                     + "FROM Atendimento ate "
                     + "INNER JOIN Animal a ON a.codanimal = ate.codanimal "
                     + "INNER JOIN ClientexAnimal ca ON ca.codanimal = a.codanimal "
                     + "INNER JOIN Cliente c ON c.codcliente = ca.codcliente "
                     + "INNER JOIN Veterinario v ON v.codvet = ate.codvet "
                     + "WHERE c.cpf = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Atendimento(
                        rs.getString("cpf"),
                        rs.getString("contato"),
                        rs.getString("nomeanimal"),
                        rs.getString("veterinario"),
                        rs.getBoolean("pago"),
                        rs.getBoolean("retorno"),
                        rs.getString("dataAtendimento")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void atualizarAtendimento(String cpfAntigo, Atendimento atendimento) {
        String query = "UPDATE Atendimento "
                     + "SET pago = ?, retorno = ?, data1 = ? "
                     + "WHERE codanimal IN ("
                     + "    SELECT a.codanimal FROM Animal a "
                     + "    INNER JOIN ClientexAnimal ca ON ca.codanimal = a.codanimal "
                     + "    INNER JOIN Cliente c ON c.codcliente = ca.codcliente "
                     + "    WHERE c.cpf = ?"
                     + ")";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setBoolean(1, atendimento.isPago());
            stmt.setBoolean(2, atendimento.isRetorno());
            stmt.setString(3, atendimento.getDataAtendimento());
            stmt.setString(4, cpfAntigo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public String pegarRelatorio(String relatorio, String data, String cpf) {
		String sql = "Select relatorio "
				+ "from atendimento "
				+ "where codatendimento =(select ate.codatendimento "
				+ "from atendimento ate "
				+ "inner join animal a "
				+ "on ate.codanimal = a.codanimal "
				+ "inner join clientexanimal cx "
				+ "on a.codanimal = cx.codanimal "
				+ "inner join cliente c "
				+ "on cx.codcliente = c.codcliente "
				+ "where (ate.data1 = ? and c.cpf = ?)) ";
		
		
		Conexao.conectar();
		
		try (Connection conn = Conexao.conexao;
		         PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		    LocalDate localDate = LocalDate.parse(data, inputFormat);  // converte de dd/MM/yyyy para LocalDate
		        
			stmt.setDate(1, java.sql.Date.valueOf(localDate));  // Passa a data para o ?
		        stmt.setString(2, cpf);  // Passa o cpf para o ?

		        try (ResultSet rs = stmt.executeQuery()) {
		            if (rs.next()) {
		                relatorio = rs.getString("relatorio");
		               
		            }
		        }

		    } catch (SQLException e) {
		        System.err.println("Erro ao buscar codanimal:");
		        e.printStackTrace();
		    } finally {
		        Conexao.desconectar();
		    }
		return relatorio;
		
	}
}

