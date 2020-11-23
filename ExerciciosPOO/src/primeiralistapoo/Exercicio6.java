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
public class Exercicio6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {       
    Scanner entrada = new Scanner(System.in);
    
    float comp ;
    
    System.out.println("Digite o comprimento em cm");
    comp = entrada.nextInt();
    
    System.out.println("O comprimento de " + comp + "/cm equivale a: " + (comp/100) + "/m e " + (comp/100000) + "/km");
    
    }   
}
