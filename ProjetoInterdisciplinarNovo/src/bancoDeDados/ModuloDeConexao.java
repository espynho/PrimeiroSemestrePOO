/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancoDeDados;

import java.sql.*;

/**
 *
 * @author fabinho
 */
public class ModuloDeConexao {

    // método para estabalecer a conexão com o bando de dados
    public static Connection conector() {
        java.sql.Connection conexao = null;
        // chama driver que foi importado
        //String driver = "com.mysql.jdbc.Driver"; ===== não é mais necessário
        // Armazenando informações referente ao banco de dados
        String url = "jdbc:mysql://localhost:3306/FRLogistica";
        //String url = "jdbc:mysql://localhost:3306/FRLogistica";
        String user = "root";
        String password = "";
        // Estabelecendo a conexão com o banco de dados
        try {
            // Class.forName(driver); ==== não é mais necessário
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
