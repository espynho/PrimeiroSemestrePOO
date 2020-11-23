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
public class Usuario extends Pessoa{
// ====== variáveis declaradas ======
    private String nomeUsuario, senhaUsuario;

// ====== métodos get's e set's ======

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }
// ====== método construtor completo ======
    public Usuario(String nomeUsuario, String senhaUsuario, String nome, String cpf, String rg, String ri) {
        super(nome, cpf, rg, ri);
        this.nomeUsuario = nomeUsuario;
        this.senhaUsuario = senhaUsuario;
    }
    
}

