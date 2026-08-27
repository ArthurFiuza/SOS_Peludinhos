package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RelatorioClienteDAO {
    private Connection connection;

    public RelatorioClienteDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Cliente> getAllClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT cliente, cpf, contato FROM cliente";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String cliente = rs.getString("cliente");
                String cpf = rs.getString("cpf");
                String contato = rs.getString("contato");

                clientes.add(new Cliente(cpf, cliente, contato));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    public Cliente getClientePorCpf(String cpf) {
        String query = "SELECT cliente, cpf, contato FROM cliente WHERE cpf = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cliente(
                    rs.getString("cpf"),
                    rs.getString("cliente"),
                    rs.getString("contato")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 

    }

    public void atualizarCliente(String cpfAntigo, Cliente cliente) {
        String sql = "UPDATE cliente SET cliente = ?, contato = ?, cpf = ? WHERE cpf = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cliente.getclientes());
            stmt.setString(2, cliente.getContato());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cpfAntigo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirCliente(String cpf) {
        String sql = "DELETE FROM cliente WHERE cpf = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
