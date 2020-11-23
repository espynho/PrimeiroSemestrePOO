/* local
 * web
 * ok
 * 
 */
package primeiralistapoo;
import java.util.Scanner ;
/**
 *
 * @author fabinho
 */
public class Exercicio1 {

    /**
     * Soma de dois números
     * 
     * @param args
     */
    public static void main(String[] args) {
        
        Scanner entrada = new Scanner(System.in);
        
        int numero1, numero2 ;
        
        System.out.println("Entre com o primeiro número");
        numero1 = entrada.nextInt();
        
        System.out.println("Entre com o segundo numero");
        numero2 = entrada.nextInt();
        
        System.out.println(" A soma dos números é: "+ (numero1+numero2));
        
    }
    
}
