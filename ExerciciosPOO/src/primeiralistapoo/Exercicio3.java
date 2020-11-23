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
public class Exercicio3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
        Scanner entrada = new Scanner(System.in);
    
        int comprimento, altura ;
    
    System.out.println("Digite o comprimento do retangulo");
    comprimento = entrada.nextInt();
    
    System.out.println("Entre com a altura do retangulo");
    altura = entrada.nextInt();
    
    System.out.println("O perimetro do retangulo é: "+(2*(comprimento + altura)));
    
    
    }
    
}
