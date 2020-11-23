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
public class Exercicio2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        
        int numero1, numero2 ;
        
        System.out.println("Entre com o primeiro número");
        numero1 = entrada.nextInt();
        
        System.out.println("Entre com o segundo número");
        numero2 = entrada.nextInt();
        
        System.out.println("A soma dos números é: " + (numero1+numero2));
        System.out.println("A subtração dos números é: " + (numero1-numero2));
        System.out.println("A multiplicação dos números é: " + (numero1*numero2));
        System.out.println("A divisão dos números é: " + (numero1/numero2));
                
               
    }
    
}
