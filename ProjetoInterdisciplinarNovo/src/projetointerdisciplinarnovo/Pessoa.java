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
public class Pessoa {

// ====== variáveis declaradas ======
    String nome;
    private String cpf, rg, ri; // rgi seria registro interno da empresa 
// ====== métodos get's e set's ======

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getRi() {
        return ri;
    }

    public void setRi(String ri) {
        this.ri = ri;
    }
// ====== método construtor completo ======
    public Pessoa(String nome, String cpf, String rg, String ri) {
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.ri = ri;
    }
    
    
}
