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
public class Cadastro {
// ====== dados para cadastro dos gerentes ======
    private Gerente[] gerentes = new Gerente[5];
    private int qtdGerentes = 1;
// ====== dados para cadastro dos agentes ======
    private Agente[] agentes = new Agente[5];
    private int qtdAgentes = 1;
// ====== dados para cadastro dos motoristas ======
    private Motorista[] motoristas = new Motorista[10]; // vetor motoristas com 10 posições
    private int qtdMotoristas = 1;
// ====== dados para cadastro dos veiculos ======
    private Veiculo[] veiculos = new Veiculo[10];
    private int qtdVeiculos = 1;

// ====== dados para cadastro das multas ======
    private Multa[] multas = new Multa[10];
    private int qtdMultas = 0;

// ====== Administração geral ======

// ====== cadastro dos gerentes no vetor ======
    public void cadastroGerentes(Gerente recebeGerente) {
        if (this.qtdGerentes < gerentes.length) {
            gerentes[qtdGerentes] = recebeGerente;
            qtdGerentes++;
            System.out.println("Gerente Cadastrado");
            System.out.println("Posição do vetor: " + qtdGerentes);
        } else {
            System.out.println("Tamanho maximo atingido");
        }
    }
// ====== cadastro dos agentes no vetor ======
    public void cadastroAgentes(Agente recebeAgente) {
        if (this.qtdAgentes < agentes.length) {
            agentes[qtdAgentes] = recebeAgente;
            qtdAgentes++;
            System.out.println("Agente Cadastrado");
            System.out.println("Posição do vetor: " + qtdAgentes);
        } else {
            System.out.println("Tamanho maximo atingido");
        }
    }
// ====== usuários pré cadastrados ======
    public void usuariosFixos() {
        System.out.println("Ola mundo");// confirmar se o método inicia sozinho
        motoristas[0] = new Motorista("fabinho", "123", "Fábio Pereira Reis", "123.321.111-01", "12.210.222-1", "0001");
        agentes[0] = new Agente("ariel", "12345", "Ariel da Silva Dias", "123.456.789-01", "12.345.678-1", "0101");
        gerentes[0] = new Gerente("ledon", "12345", "Manuel Fernándes Paradela Ledón", "321.654.987-10", "21.543.876-1", "0201");
        veiculos[0] = new Veiculo("AUDI", "A3", "JPT-6666", 2005);
    }
// ====== enviando motorista fixo ======
    public String motoristaFixo() {
        return motoristas[0].getNome();
    }
// ====== enviando veiculo fixo ======
    public String veiculoFixo() {
        return veiculos[0].getPlaca();
    }
// ====== cadastro de motoristas no vetor ======
    public void cadastroMotoristas(Motorista recebeMotorista) {
        if (this.qtdMotoristas < motoristas.length) {
            motoristas[qtdMotoristas] = recebeMotorista;
            qtdMotoristas++;
            System.out.println("Motorista Cadastrado");
            System.out.println("Posição do vetor: " + qtdMotoristas);
        } else {
            System.out.println("Tamanho maximo atingido");
        }
    }
// ====== motoristas cadastrados ======
    public void motoristasCadastrados() {
        String logMotoristas;
        if (qtdMotoristas > 0) {
            for (int i = 0; i < qtdMotoristas; i++) {
                logMotoristas = motoristas[i].getNome();
                System.out.println(logMotoristas);
            }
        } else {
            System.out.println("Sem motoristas cadastrados");
        }
    }
    // ====== retorno dos morotistas para o gerente ====== 
     public String motoristasCadastradosRetorno() {
        String logMotoristas = "";
        logMotoristas += "Nome - Registro interno \n";
        if (qtdMotoristas > 0) {
            for (int i = 0; i < qtdMotoristas; i++) {
                logMotoristas += motoristas[i].getNome()+" - "+motoristas[i].getRi()+"\n";
            }return logMotoristas;
        }
        return "Sem Motoristas Cadastrados";
    }
// ====== cadastro de veiculos no vetor ======
    public void cadastroVeiculos(Veiculo recebeVeiculo) {
        if (qtdVeiculos < veiculos.length) {
            veiculos[qtdVeiculos] = recebeVeiculo;
            qtdVeiculos++;
            System.out.println("Veiculo Cadastrado");
            System.out.println("Posição do vetor: " + qtdVeiculos);
        } else {
            System.out.println("Tamanho maximo atingido");
        }
    }
// ====== veiculos cadastrados ======
    public void veiculosCadastrados() {
        String logVeiculos = "";
        if (qtdVeiculos > 0) {
            for (int i = 0; i < qtdVeiculos; i++) {
                logVeiculos += veiculos[i].getModelo() + "\n";
                //System.out.println(logVeiculos);
            }
            System.out.println(logVeiculos);
        } else {
            System.out.println("Sem veiculos cadastrados");
        }
    }
// ====== retorno dos veiculos para o gerente ======
    public String veiculosCadastradosRetorno() {
        String logVeiculos = "";
        logVeiculos += "Montadora - Modelo - Ano \n";
        if (qtdVeiculos > 0) {
            for (int i = 0; i < qtdVeiculos; i++) {
                logVeiculos += veiculos[i].getMontadora() + " - " + veiculos[i].getModelo() + " - " + veiculos[i].getAno()+"\n";
                //System.out.println(logVeiculos);
            }
            return logVeiculos;
        }
        return "Sem veiculos cadastrados";
    }
    // ====== cadastro de multas no vetor ======
    public void cadastroMultas(Multa recebeMulta) {
        if (qtdMultas < multas.length) {
            multas[qtdMultas] = recebeMulta;
            qtdMultas++;
            System.out.println("Multa Cadastrada");
            System.out.println("Posição do vetor: " + qtdMultas);
        } else {
            System.out.println("Tamanho maximo atingido");
        }
    }
    // ====== aplicação de multa ======
    // ------ verificação de multa existente ------
    public Multa verificarMulta(String multaRecebida) {
        if (qtdMultas > 0) {
            for (int i = 0; i < qtdMultas; i++) {
                if (multas[i].getDescricao().equals(multaRecebida)) {
                    return multas[i];
                }
            }
        }
        return null;
    }
    // ====== verificação de motorista existente pelo nome ======
    public Motorista verificarMotorista(String motoristaRecebido) {
        if (qtdMotoristas > 0) {
            for (int i = 0; i < qtdMotoristas; i++) {
                if (motoristas[i].getNome().equals(motoristaRecebido)) {
                    //if (motoristas[i].getNomeUsuario().equals(motoristaRecebido)) {
                    return motoristas[i];
                }
            }
        }
        return null;
    }
    // ====== verificação de veiculo existente pela descrição ======
    public Veiculo verificarVeiculo(String veiculoRecebido) {
        if (qtdVeiculos > 0) {
            for (int i = 0; i < qtdVeiculos; i++) {
                //if (veiculos[i].getModelo().equals(veiculoRecebido)) {
                if (veiculos[i].getPlaca().equals(veiculoRecebido)) {
                    //if (motoristas[i].getNomeUsuario().equals(motoristaRecebido)) {
                    return veiculos[i];
                }
            }
        }
        return null;
    }
    // ====== verificação de veiculo existente pela placa ======
    public String verificarVeiculoPlaca(String placaRecebida) {
        if (qtdVeiculos > 0) {
            for (int i = 0; i < qtdVeiculos; i++) {
                if (veiculos[i].getPlaca().equals(placaRecebida)) {
                    //if (motoristas[i].getNomeUsuario().equals(motoristaRecebido)) {
                    return "cadastrado";
                }
            }
        }
        return "não cadastrado";
    }
    // ====== verificação de motorista existente pelo nome de usuário ======
    public Motorista verificarMotoristaCadastrado(String motoristaRecebido) {
        if (qtdMotoristas > 0) {
            for (int i = 0; i < qtdMotoristas; i++) {
                if (motoristas[i].getNomeUsuario().equals(motoristaRecebido)) {
                    //if (motoristas[i].getNomeUsuario().equals(motoristaRecebido)) {
                    return motoristas[i];
                }
            }
        }
        return null;
    }
    /*    
    // ====== aplicação de multa ======
    public void aplicarMulta(Multa multaRecebida) {
     String nome,carro,multa;
     nome = multaRecebida.getMotoristaMultado();
     carro = multaRecebida.getVeiculoMultado();
     multa = multaRecebida.getDescricao();
        System.out.println("Dados da multa: "+nome);
        System.out.println("Dados da multa: "+carro);
        System.out.println("Dados da multa: "+multa);
        if(qtdMotoristas > 0 && qtdVeiculos > 0){ // confirmçao se tem motoristas e carros cadastrados
            for(int i = 0; i < qtdMotoristas; i++){
            if(motoristas[i].getNome().equals(nome)){
                System.out.println("Motorista encontrado");
               motoristas[i].cadastroMultaMotorista(nome, carro, multa); 
            }  else{
                System.out.println("Não encontrado");
            }  
            }
        }
    }
     */
 /*
// ====== aplicação de multa ======
    public void aplicarMulta(Multa multaRecebida) {
        if (qtdMultas < multas.length) {
            multas[qtdMultas] = multaRecebida;
            qtdMultas++;
            System.out.println("Motorista Multadotado");
            System.out.println("Posição do vetor: " + qtdMultas);
        } else {
            System.out.println("Tamanho maximo atingido");
        }
    }
     */
    // ====== verificação para login ======
    public String nomeMotoristaCadastrado(String motRecebido) {
        String nomeUsuario = "";
        if (qtdMotoristas > 0) {
            for (int i = 0; i < qtdMotoristas; i++) {
                if (motRecebido.equals(motoristas[i].getNomeUsuario())) {
                    nomeUsuario += motoristas[i].getNomeUsuario();
                    return nomeUsuario;
                }
            }
        } else {
            return "Sem motoristas cadastrados";
        }
        return null;
    }
    // ====== verificação para login motoristas ======
    public int loginMotoristaCadastrado(String usrRecebido, String senRecebido) {
        if (qtdMotoristas > 0) {
            for (int i = 0; i < qtdMotoristas; i++) {
                if (usrRecebido.equals(motoristas[i].getNomeUsuario())
                        && senRecebido.equals(motoristas[i].getSenhaUsuario())) {
                    return 1;
                }
            }
        }
        return 0;
    }
    // ====== verificação para login agentes ======
    public int loginAgenteCadastrado(String usrRecebido, String senRecebido) {
        if (qtdAgentes > 0) {
            for (int i = 0; i < qtdAgentes; i++) {
                if (usrRecebido.equals(agentes[i].getNomeUsuario())
                        && senRecebido.equals(agentes[i].getSenhaUsuario())) {
                    return 2;
                }
            }
        }
        return 0;
    }
    // ====== verificação para login gerentes ======
    public int loginGerenteCadastrado(String usrRecebido, String senRecebido) {
        if (qtdGerentes > 0) {
            for (int i = 0; i < qtdGerentes; i++) {
                if (usrRecebido.equals(gerentes[i].getNomeUsuario())
                        && senRecebido.equals(gerentes[i].getSenhaUsuario())) {
                    return 3;
                }
            }
        }
        return 0;
    }
    // ====== verificação para login Admin ======
    public int loginADM(String usrRecebido, String senRecebido) {
        if (usrRecebido.equals("Admin") && senRecebido.equals("admin1234")) {  
                    return 4;}
        return 0;
    }
    // ====== motorista logado ======
    // public Motorista motoristaLogadoDados(String motRecebido) {
/*        public String motoristaLogadoDados(String motRecebido) {
        if (qtdMotoristas > 0) {
            for (int i = 0; i < qtdMotoristas; i++) {
                if (motRecebido.equals(motoristas[i].getNomeUsuario())) {
                    return motoristas[i].mostrarMultaMotorista();
                }
            }
        }
        return null;
    }
        // public Motorista motoristaLogadoDados(String motRecebido) {
     */ public Motorista motoristaLogadoDados(String motRecebido) {
        if (qtdMotoristas > 0) {
            for (int i = 0; i < qtdMotoristas; i++) {
                if (motRecebido.equals(motoristas[i].getNomeUsuario())) {
                    return motoristas[i];
                }
            }
        }
        return null;
    }
}
