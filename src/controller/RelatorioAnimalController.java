package controller;

import model.Animal;
import model.RelatorioAnimalDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class RelatorioAnimalController {

    private RelatorioAnimalDAO dao;

    public RelatorioAnimalController(RelatorioAnimalDAO dao) {
        this.dao = dao;
    }

    public RelatorioAnimalController() {
        this(new RelatorioAnimalDAO());
    }

    public List<Animal> listarAnimais() {
        return dao.listarAnimais();
    }

    public Animal buscarAnimal(String cpf, String nomeAnimal) {
        return dao.buscarAnimal(cpf, nomeAnimal);
    }

    public String atualizarAnimal(String cpfAntigo, String nomeAntigo, Animal animalAtualizado) {
        try {
            dao.atualizarAnimal(cpfAntigo, nomeAntigo, animalAtualizado);
            return "Animal atualizado com sucesso!";
        } catch (IllegalArgumentException e) {
            return "Erro: " + e.getMessage();
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao acessar o banco ao atualizar animal.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro inesperado ao atualizar animal.";
        }
    }

    public String excluirAnimal(String cpf, String nomeAnimal) {
        try {
            dao.excluirAnimal(cpf, nomeAnimal);
            return "Animal excluído com sucesso!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao acessar o banco ao excluir animal.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro inesperado ao excluir animal.";
        }
    }

    public int buscarCodRacaPorNome(String nomeRaca) {
        try {
            return dao.buscarCodRacaPorNome(nomeRaca);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public List<String> listarNomesEspecies() {
        try {
            return dao.listarNomesEspecies();
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public List<String> listarRacasPorEspecie(String especie) {
        try {
            return dao.listarRacasPorEspecie(especie);
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }

  
    public Map<String, Integer> listarRacasMapPorEspecie(String especie) {
        try {
            List<String> racas = dao.listarRacasPorEspecie(especie);
            Map<String, Integer> mapa = new HashMap<>();
            for (String r : racas) {
                int cod = dao.buscarCodRacaPorNome(r);
                mapa.put(r, cod);
            }
            return mapa;
        } catch (SQLException e) {
            e.printStackTrace();
            return Map.of();
        }
    }
}
