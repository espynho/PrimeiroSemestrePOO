/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetointerdisciplinarnovo;

import java.sql.*;
import bancoDeDados.ModuloDeConexao;
import javax.swing.JOptionPane;

/**
 *
 * @author fabinho
 */
public class Cadastro {
// ====== migração para banco de dados ======

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public int verificarMulta(String multaRecebida) {

        conexao = ModuloDeConexao.conector();
        String sql = "select * from Multa where descricao = ?"; // pesquisa descrição da multa no banco de dados
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, multaRecebida);
            rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {

        }
        return 0;
    }

    // ====== verificação de motorista existente pelo nome ======
    // ****** banco de dados ******
    public int verificarMotorista(String motoristaRecebido) {
        conexao = ModuloDeConexao.conector();
        String sql = "select * from Pessoa where nome = ? and cargo ='motorista'"; // pesquisa o nome dos motoristas no banco de dados
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, motoristaRecebido);
            rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {

        }
        return 0;
    }

    // ====== verificação de veiculo existente pela descrição ======
    // ****** banco de dados ******
    public int verificarVeiculo(String veiculoRecebido) {
        conexao = ModuloDeConexao.conector();
        String sql = "select * from Veiculo where placa = ? "; // pesquisa a placa no banco de dados
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, veiculoRecebido);
            rs = pst.executeQuery();
            if (rs.next()) {

                return rs.getInt(1);
            }
        } catch (Exception e) {

        }
        return 0;
    }

    // ====== verificação de veiculo existente pela placa ======
    // ****** banco de dados ******
    public String verificarVeiculoPlaca(String placaRecebida) {
        conexao = ModuloDeConexao.conector();
        String sql = "select * from Veiculo where placa = ?"; // pesquisa a placa do veiculo no banco de dados
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, placaRecebida);
            rs = pst.executeQuery();
            if (rs.next()) {
                return "cadastrado";
            }
        } catch (Exception e) {

        }

        return "não cadastrado";
    }

    // ====== verificação de motorista existente pelo nome de usuário ======
    // ****** Banco de Dados *******
    public String verificarMotoristaCadastrado(String motoristaRecebido) {
        conexao = ModuloDeConexao.conector();
        String sql = "select * from Pessoa where usuario = ? "; // comandos sql
        try {
            pst = conexao.prepareCall(sql);
            pst.setString(1, motoristaRecebido);
            rs = pst.executeQuery();
            if (rs.next()) {
                String nome = rs.getString(2); // variavel recebendo dados da tabela no campo 2

                System.out.println("Nome recebido do banco de dados: " + nome);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    // ====== aplicação da multa ======
    // ****** banco de dados ******
    public void aplicarMulta(int id_motorista, int id_veiculo, int id_multa) {
        conexao = ModuloDeConexao.conector();
        String sql = "INSERT into AplicarMulta "
                + "(motorista_multado, veiculo_multado, multa_aplicada)"
                + "values (?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, id_motorista);
            pst.setInt(2, id_veiculo);
            pst.setInt(3, id_multa);
            pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
    }

    // ====== verificação para login motoristas ======
    // ****** Banco de Dados *******
    public int login(String usrRecebido, String senRecebido) {
        conexao = ModuloDeConexao.conector();
        String sql = "select * from Pessoa where usuario = ? and senha = ?"; // comandos sql
        try {
            pst = conexao.prepareCall(sql);
            pst.setString(1, usrRecebido);
            pst.setString(2, senRecebido);
            rs = pst.executeQuery();
            if (rs.next()) {
                switch (rs.getString(8)) {
                    case "motorista":
                        return 1;
                    case "agente":
                        return 2;
                    case "gerente":
                        return 3;
                    case "admin":
                        return 4;
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
}

