package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RelatorioAnimalDAO {

    public List<Animal> listarAnimais() {
        List<Animal> lista = new ArrayList<>();
        String sql = "SELECT c.cpf, a.nomeAnimal, a.DTNascimento, r.Raca " +
                     "FROM Animal a " +
                     "JOIN ClientexAnimal ca ON a.codAnimal = ca.codAnimal " +
                     "JOIN Cliente c ON ca.codCliente = c.codCliente " +
                     "JOIN Raca r ON a.codRaca = r.codRaca";

        Conexao.conectar();
        try (Connection con = Conexao.conexao;
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Animal animal = new Animal(
                    rs.getString("nomeAnimal"),
                    rs.getString("DTNascimento"),
                    0
                );
                animal.setCpfCliente(rs.getString("cpf"));
                animal.setRacaNome(rs.getString("Raca"));
                lista.add(animal);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.desconectar();
        }
        return lista;
    }

    public Animal buscarAnimal(String cpf, String nomeAnimal) {
        Animal animal = null;
        String sql = "SELECT c.cpf, a.nomeAnimal, a.DTNascimento, r.Raca, a.codRaca " +
                     "FROM Animal a " +
                     "JOIN ClientexAnimal ca ON a.codAnimal = ca.codAnimal " +
                     "JOIN Cliente c ON ca.codCliente = c.codCliente " +
                     "JOIN Raca r ON a.codRaca = r.codRaca " +
                     "WHERE c.cpf = ? AND a.nomeAnimal = ?";

        Conexao.conectar();
        try (Connection con = Conexao.conexao;
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.setString(2, nomeAnimal);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    animal = new Animal(
                        rs.getString("nomeAnimal"),
                        rs.getString("DTNascimento"),
                        rs.getInt("codRaca")
                    );
                    animal.setCpfCliente(rs.getString("cpf"));
                    animal.setRacaNome(rs.getString("Raca"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.desconectar();
        }
        return animal;
    }

    public int buscarCodRacaPorNome(String nomeRaca) throws SQLException {
        int codRaca = -1;
        String sql = "SELECT codRaca FROM Raca WHERE Raca = ?";
        Conexao.conectar();
        try (Connection con = Conexao.conexao;
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nomeRaca);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    codRaca = rs.getInt("codRaca");
                }
            }
        } finally {
            Conexao.desconectar();
        }
        return codRaca;
    }

    public List<String> listarNomesRacas() throws SQLException {
        List<String> racas = new ArrayList<>();
        String sql = "SELECT Raca FROM Raca ORDER BY Raca";

        Conexao.conectar();
        try (Connection con = Conexao.conexao;
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                racas.add(rs.getString("Raca"));
            }

        } finally {
            Conexao.desconectar();
        }

        return racas;
    }

    public void atualizarAnimal(String cpfAntigo, String nomeAntigo, Animal animalAtualizado) throws SQLException {
        Conexao.conectar();
        Connection con = null;

        String verificaRacaSQL = "SELECT COUNT(*) FROM Raca WHERE codRaca = ?";
        String updateAnimalSQL = "UPDATE Animal SET nomeAnimal = ?, DTNascimento = ?, codRaca = ? " +
                                 "WHERE codAnimal = (SELECT ca.codAnimal FROM ClientexAnimal ca " +
                                                  "JOIN Cliente c ON ca.codCliente = c.codCliente " +
                                                  "JOIN Animal a ON ca.codAnimal = a.codAnimal " +
                                                  "WHERE c.cpf = ? AND a.nomeAnimal = ?)";
        
        @SuppressWarnings("unused")
		String updateClientexAnimalSQL = "UPDATE ClientexAnimal SET codCliente = (SELECT codCliente FROM Cliente WHERE cpf = ?) " +
                                         "WHERE codAnimal = (SELECT a.codAnimal FROM Animal a " +
                                                           "JOIN ClientexAnimal ca ON a.codAnimal = ca.codAnimal " +
                                                           "JOIN Cliente c ON ca.codCliente = c.codCliente " +
                                                           "WHERE c.cpf = ? AND a.nomeAnimal = ?)";

        try {
            con = Conexao.conexao;
            con.setAutoCommit(false);

            try (PreparedStatement psVerificaRaca = con.prepareStatement(verificaRacaSQL)) {
                psVerificaRaca.setInt(1, animalAtualizado.getCodRaca());
                try (ResultSet rs = psVerificaRaca.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt(1);
                        if (count == 0) {
                            throw new IllegalArgumentException("Raça com código " + animalAtualizado.getCodRaca() + " não existe.");
                        }
                    } else {
                        throw new IllegalArgumentException("Falha ao verificar raça.");
                    }
                }
            }

            try (PreparedStatement psUpdateAnimal = con.prepareStatement(updateAnimalSQL)) {
                psUpdateAnimal.setString(1, animalAtualizado.getNomeAnimal());
                psUpdateAnimal.setString(2, animalAtualizado.getdTNascimentoAnimal());
                psUpdateAnimal.setInt(3, animalAtualizado.getCodRaca());
                psUpdateAnimal.setString(4, cpfAntigo);
                psUpdateAnimal.setString(5, nomeAntigo);

                int linhasAfetadas = psUpdateAnimal.executeUpdate();
                if (linhasAfetadas == 0) {
                    throw new SQLException("Animal não encontrado para atualização.");
                }
            }

            con.commit();

        } catch (SQLException | IllegalArgumentException e) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw e;
        } finally {
            if (con != null) {
                try {
                    if (!con.isClosed()) {
                        con.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            Conexao.desconectar();
        }
    }

    public void excluirAnimal(String cpf, String nomeAnimal) throws SQLException {
        String sqlBuscarCodAnimal =
            "SELECT a.codAnimal FROM Animal a " +
            "JOIN ClientexAnimal ca ON a.codAnimal = ca.codAnimal " +
            "JOIN Cliente c ON ca.codCliente = c.codCliente " +
            "WHERE c.cpf = ? AND a.nomeAnimal = ?";

        String sqlDeleteClientexAnimal = "DELETE FROM ClientexAnimal WHERE codAnimal = ?";
        String sqlDeleteAnimal = "DELETE FROM Animal WHERE codAnimal = ?";

        Conexao.conectar();
        Connection con = null;
        try {
            con = Conexao.conexao;
            con.setAutoCommit(false);

            int codAnimal = -1;
            try (PreparedStatement psBuscar = con.prepareStatement(sqlBuscarCodAnimal)) {
                psBuscar.setString(1, cpf);
                psBuscar.setString(2, nomeAnimal);
                try (ResultSet rs = psBuscar.executeQuery()) {
                    if (rs.next()) {
                        codAnimal = rs.getInt("codAnimal");
                    } else {
                        throw new SQLException("Animal não encontrado para exclusão.");
                    }
                }
            }

            try (PreparedStatement psDelClientex = con.prepareStatement(sqlDeleteClientexAnimal)) {
                psDelClientex.setInt(1, codAnimal);
                psDelClientex.executeUpdate();
            }

            try (PreparedStatement psDelAnimal = con.prepareStatement(sqlDeleteAnimal)) {
                psDelAnimal.setInt(1, codAnimal);
                psDelAnimal.executeUpdate();
            }

            con.commit();
        } catch (SQLException e) {
            if (con != null) {
                con.rollback();
            }
            throw e;
        } finally {
            if (con != null && !con.isClosed()) {
                con.close();
            }
            Conexao.desconectar();
        }
    }

    
    public List<String> listarNomesEspecies() throws SQLException {
        List<String> especies = new ArrayList<>();
        String sql = "SELECT Especie FROM Especie ORDER BY Especie";

        Conexao.conectar();
        try (Connection con = Conexao.conexao;
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                especies.add(rs.getString("Especie"));
            }
        } finally {
            Conexao.desconectar();
        }
        return especies;
    }
    public List<String> listarRacasPorEspecie(String nomeEspecie) throws SQLException {
        List<String> racas = new ArrayList<>();
        String sql = "SELECT r.Raca FROM Raca r " +
                     "JOIN Especie e ON r.codEspecie = e.codEspecie " +
                     "WHERE e.Especie = ? " +
                     "ORDER BY r.Raca";

        Conexao.conectar();
        try (Connection con = Conexao.conexao;
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, nomeEspecie);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    racas.add(rs.getString("Raca"));
                }
            }
        } finally {
            Conexao.desconectar();
        }
        return racas;
    }
}
