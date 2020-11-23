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
public class Exercicio5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    Scanner entrada = new Scanner(System.in);
    
    int raio ;
    float PI = 3.14f;
    
    System.out.println("Didite o raio do circulo");
    raio = entrada.nextInt();
    
    System.out.println("O diametero do circulo é "+ (raio*2));
    System.out.println("A área do circulo é "+ (PI*Math.pow(raio, 2)));
    System.out.println("A circunferencia do circulo é "+ (2*PI*raio));
    }
    
}
