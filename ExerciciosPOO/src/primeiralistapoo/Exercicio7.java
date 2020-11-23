/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primeiralistapoo;
import java.util.Scanner;
/**
 *
 * @author fabinho
 */
public class Exercicio7 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    Scanner entrada = new Scanner(System.in);
    
    int celc ;
    
    System.out.println("Entre com a temperatura em graus celcios");
    celc = entrada.nextInt();
    
    System.out.println(celc+ " graus celcios equivalem a: " + ((celc*1.8)+32) + " graus Fahrenheit");
    }
    
}
