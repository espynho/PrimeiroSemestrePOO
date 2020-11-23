/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetointerdisciplinarnovo;

/**
 *
 * @author fabinho
 */
public class Veiculo {
// ====== declaração de variáveis ======
    private String montadora, modelo, placa;
    private int ano;

// ====== métodos get's e set's ======
    public String getMontadora() {
        return montadora;
    }

    public void setMontadora(String montadora) {
        this.montadora = montadora;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

// ====== construtor ======
    public Veiculo() {
    }

    public Veiculo(String montadora, String modelo, String placa, int ano) {
        this.montadora = montadora;
        this.modelo = modelo;
        this.placa = placa;
        this.ano = ano;
    }

    
    
}
