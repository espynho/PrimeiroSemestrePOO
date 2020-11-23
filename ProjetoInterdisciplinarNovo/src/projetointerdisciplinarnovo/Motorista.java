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
public class Motorista extends Usuario{
// ====== declaração de variáveis ======
   private Multa[] multaTomada = new Multa[10];
   private int qtdMulta = 0;
  
   private Veiculo[] veiculoMultado = new Veiculo[10];
   private int qtdVeiculo = 0;
   private int pontuacao = 0; // pontuação do motorista individual;
   //String multasTomadas = "" ; // para mostrar multas
   

// ====== métodos get's e set's ======
 
 // ====== aplicação da multa ====== 
    public void cadastroMultaMotorista(Multa multaRecebida, Veiculo veiculoRecebido ) {
    multaTomada[qtdMulta] = multaRecebida  ;
    pontuacao += multaTomada[qtdMulta].getPontuacao();
    qtdMulta++ ;
    System.out.println("Multa aplicada");
    veiculoMultado[qtdVeiculo] = veiculoRecebido;
    qtdVeiculo++;
        System.out.println("Veiculo multado");
    }   
// ----------------------------------------------------------------------------
/* funcionando ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // ====== aplicação da multa ======
    public void cadastroMultaMotorista(Multa multaRecebida ) {
    multaTomada[qtdMulta] = multaRecebida  ;
    qtdMulta++ ;
        System.out.println("Multa aplicada");
    }
*/    
    // ====== mostrar multas ======
    public String multasTomadas(){
        String multasTomadasLog = "" ; // para mostrar multas
        if(qtdMulta > 0){
            for(int i = 0; i < qtdMulta; i++){
            multasTomadasLog += "Multa de: "+ multaTomada[i].getDescricao()+"\n";
            multasTomadasLog += "Veiculo: "+veiculoMultado[i].getMontadora()+" - "+veiculoMultado[i].getModelo()+"\n";
            }
            return multasTomadasLog+"\n"+"Total de pontos: "+pontuacao;
        }
       return "Você não tem multas";
    }   

// ====== método construtor completo ======
    public Motorista(String nomeUsuario, String senhaUsuario, String nome, String cpf, String rg, String ri) {
        super(nomeUsuario, senhaUsuario, nome, cpf, rg, ri);
    }
// ====== método para imprimir os dados dos motoristas ======

    

}
