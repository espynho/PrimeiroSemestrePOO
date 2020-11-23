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
public class Multa {
// ====== declaração das variáveis ======

    private String descricao;
    private float valor;
    private int pontuacao;

// ====== métodos get's e set's ======
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
    

// ====== construtores ======
    public Multa() {
    }

    public Multa(String descricao, float valor, int pontuacao) {
        this.descricao = descricao;
        this.valor = valor;
        this.pontuacao = pontuacao;
    }

}
