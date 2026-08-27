package model;

public class Animal {
    private String cpfCliente;
    private String nomeAnimal;
    private String dTNascimentoAnimal;
    private int codRaca;
    private String racaNome;
    private String especie;

    public Animal(String nomeAnimal, String dTNascimentoAnimal, int codRaca) {
        this.nomeAnimal = nomeAnimal;
        this.dTNascimentoAnimal = dTNascimentoAnimal;
        this.codRaca = codRaca;
    }

    public Animal(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    // Getter e Setter para especie
    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getNomeAnimal() {
        return nomeAnimal;
    }

    public void setNomeAnimal(String nomeAnimal) {
        this.nomeAnimal = nomeAnimal;
    }

    public String getdTNascimentoAnimal() {
        return dTNascimentoAnimal;
    }

    public void setdTNascimentoAnimal(String dTNascimentoAnimal) {
        this.dTNascimentoAnimal = dTNascimentoAnimal;
    }

    public int getCodRaca() {
        return codRaca;
    }

    public void setCodRaca(int codRaca) {
        this.codRaca = codRaca;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getRacaNome() {
        return racaNome;
    }

    public void setRacaNome(String racaNome) {
        this.racaNome = racaNome;
    }
}
