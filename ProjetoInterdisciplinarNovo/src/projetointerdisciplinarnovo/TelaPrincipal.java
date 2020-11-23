/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetointerdisciplinarnovo;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author fabinho
 */
public class TelaPrincipal extends javax.swing.JFrame {

    Cadastro funcoes = new Cadastro();
    // ====== dados motorista logado ======
    String nomeMotoristaLogado;
    // ====== valor das multas tomadas ======
    private float valorDasMultas = 0f; // valor das multas da empresa
    private int qtdMultasEmpresa = 0;

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
        //cxEntrada1CadV3.setDocument(new SoNumero()); // para o campo ano carro só aceitar números, senão da erro.
        funcoes.usuariosFixos();
        cadastroFixo();
        travarAbas();
    }
// ====== meus métodos ======
// ====== janela erro 400 dados vazios =====
    public void janelaErro400() {
        String erro = "Erro 400";
        JFrame login = new JFrame("janela");
        JOptionPane.showMessageDialog(login, "Preencha todos os campos", erro, JOptionPane.INFORMATION_MESSAGE);
    }
    // ====== janela erro 401 senha ou usuário incorretos =====
    public void janelaErro401() {
        String erro = "Erro 401";
        JFrame login = new JFrame("janela");
        JOptionPane.showMessageDialog(login, "Usuário ou senha inválidos", erro, JOptionPane.INFORMATION_MESSAGE);
    }
  // ====== janela erro 402 senha veiculo ja cadastrado =====
    public void janelaErro402() {
        String erro = "Erro 402";
        JFrame login = new JFrame("janela");
        JOptionPane.showMessageDialog(login, "Veículo ja cadastrado", erro, JOptionPane.INFORMATION_MESSAGE);
    }
     // ====== janela erro 403 senhas não são iguais =====
    public void janelaErro403() {
        String erro = "Erro 402";
        JFrame login = new JFrame("janela");
        JOptionPane.showMessageDialog(login, "As senhas digitadas não são iguais", erro, JOptionPane.INFORMATION_MESSAGE);
    }
// ====== adiciona itens iniciais no menus de multas ======
    public void cadastroFixo() {
        String motorista0 = funcoes.motoristaFixo();
        selecaoAgenteMot.addItem(motorista0); // adiciona motorista no menu da tela agente
        String veiculo0 = funcoes.veiculoFixo();
        selecaoAgenteCar.addItem(veiculo0); // adiciona motorista no menu da tela agente
    }
// ====== campos somente números ======
    public void camposSoNumeros(){
    cxEntrada1CadV3.setDocument(new SoNumero()); // para o campo ano carro só aceitar números, senão da erro.
    cxEntrada4Agente.setDocument(new SoNumero());
    cxEntrada2CadM.setDocument(new SoNumero());
    cxEntrada3CadM.setDocument(new SoNumero());
    }
// ====== aplicação de multas ======
    public void aplicacaoMulta() {
        //System.out.println(selecaoAgenteMot.getSelectedItem()); // objeto selecionado motorista
        //System.out.println(selecaoAgenteCar.getSelectedItem()); // objeto selecionado carro
        //System.out.println(selecaoAgenteMul.getSelectedItem()); // objeto selecionado multa
        String modelo = (String) selecaoAgenteCar.getSelectedItem();// netBeans completou o código
        String nome = (String) selecaoAgenteMot.getSelectedItem();// netBeans completou o código
        String descricao = (String) selecaoAgenteMul.getSelectedItem();// netBeans completou o código
        if (modelo.equals("Selecione um veiculo") || nome.equals("Selecione um motorista")
                || descricao.equals("Selecione uma Multa")) {
           //System.out.println("Falta dados, tente novamente");
           janelaErro400();
        } else {
            Multa tomada = funcoes.verificarMulta(descricao); // verificar se a multa está cadastrada
            Motorista motoristaMultado = funcoes.verificarMotorista(nome); // verificar se o motorista está cadastrado
            Veiculo veiculoMultado = funcoes.verificarVeiculo(modelo); // verificar se o veiculo está cadastrado
            motoristaMultado.cadastroMultaMotorista(tomada, veiculoMultado);
            valorDasMultas += tomada.getValor();
            qtdMultasEmpresa++;
            limparTelaMultas();
        }
    }

    // ====== checagem dados da aba login ======
    public void checagemDeDadosLogin() {
        String nomeDigitado = entradaNomeLogin.getText();
        String senhaDigitado = entradaSenhaLogin.getText();
        //System.out.println("Cod retornado: "+funcoes.loginMotoristaCadastrado(nomeDigitado, senhaDigitado));
        if (funcoes.loginMotoristaCadastrado(nomeDigitado, senhaDigitado) == 1) {
            System.out.println("Motorista logado");
            painelDeAbas.setEnabledAt(4, true); // aba motorista
            motoristaLogado(nomeDigitado);
            limparTelaLogin();
        } else if (funcoes.loginAgenteCadastrado(nomeDigitado, senhaDigitado) == 2) {
            System.out.println("Agente logado");
            painelDeAbas.setEnabledAt(1, true); // aba cadastro motoristas
            painelDeAbas.setEnabledAt(2, true); // aba cadastro veiculos
            painelDeAbas.setEnabledAt(3, true); // aba multas
            camposSoNumeros();
            limparTelaLogin();
        } else if (funcoes.loginGerenteCadastrado(nomeDigitado, senhaDigitado) == 3) {
            System.out.println("Gerente logado");
            painelDeAbas.setEnabledAt(5, true); // aba gerente
            limparTelaLogin();
        } else if (funcoes.loginADM(nomeDigitado, senhaDigitado) == 4) {
            System.out.println("!====== Modo Administrador ======!");
            painelDeAbas.setEnabledAt(1, false); // aba cadastro motoristas
            painelDeAbas.setEnabledAt(2, false); // aba cadastro veiculos
            painelDeAbas.setEnabledAt(3, false); // aba multas
            painelDeAbas.setEnabledAt(4, false); // aba motorista
            painelDeAbas.setEnabledAt(5, false); // aba gerente
            painelDeAbas.setEnabledAt(6, true); // aba adm
            limparTelaLogin();
        } else {
            //System.out.println("Usuário ou senha inválidos");
            janelaErro401();
        }
    }

    // ====== mostrando motorista logado ======
    public void motoristaLogado(String nomeRecebidoLogin) {
        System.out.println(nomeRecebidoLogin);
        Motorista motoristaLogado = funcoes.verificarMotoristaCadastrado(nomeRecebidoLogin);
        nomeMotoristaLogado = motoristaLogado.getNome();
        //nomeMotoristaLogado = funcoes.verificarMotorista(nomeRecebidoLogin).getNome();
        //nomeMotoristaLogado = nomeMotorista;
        System.out.println("o motorista atual é: " + nomeMotoristaLogado);
    }

    /*    String nomeDigitado = entradaNomeLogin.getText();
        if(nomeDigitado.equals(funcoesParaCadastrar.nomeMotoristaCadastrado(nomeDigitado))){
            System.out.println("Motorista encontrado tela");
        }else{
            System.out.println("Motorista não encontrado tela");
        }
    }
     */
    // ====== mostrar valor de todas multas da empresa ======
    public String valorTotalMultas() {
        if (valorDasMultas > 0) {
            return "Valor das multas: " + valorDasMultas;
        } else {
            return "A empresa não tem multas";
        }
    }

    // ====== travar todas abas ======
    public void travarAbas() {
        painelDeAbas.setEnabledAt(1, false); // aba cadastro motoristas
        painelDeAbas.setEnabledAt(2, false); // aba cadastro veiculos
        painelDeAbas.setEnabledAt(3, false); // aba multas
        painelDeAbas.setEnabledAt(4, false); // aba motorista
        painelDeAbas.setEnabledAt(5, false); // aba gerente
        painelDeAbas.setEnabledAt(6, false); // aba adm
    }

    // ====== limpar tela login ======
    public void limparTelaLogin() {
        entradaNomeLogin.setText("");
        entradaSenhaLogin.setText("");
        entradaNomeLogin.setEditable(false);
        entradaSenhaLogin.setEditable(false);
        botaoEntrarLogin.setEnabled(false);
    }
    // ====== liberar tela login ======

    public void liberarTelaLogin() {
        entradaNomeLogin.setEditable(true);
        entradaSenhaLogin.setEditable(true);
        botaoEntrarLogin.setEnabled(true);
    }

    // ====== verificar campos veículos ======
    public void cadastroVeiculos() {
        String placa, resposta;
        placa = cxEntrada1CadV2.getText();
        resposta = funcoes.verificarVeiculoPlaca(placa); // verifica se a placa já está cadastrada
        if (cxEntrada1CadV.getText().isEmpty()
                || cxEntrada1CadV1.getText().isEmpty()
                || cxEntrada1CadV2.getText().isEmpty()
                || //Integer.parseInt(cxEntrada1CadV3.getText().isEmpty())){
                cxEntrada1CadV3.getText().isEmpty()) {
            //System.out.println("Algum campo está vazio");
            janelaErro400();
        } else if (resposta.equals("cadastrado")) {
            //System.out.println("Veículo ja cadastrado");
        } else {
            //Veiculo cadastroveiculo = new Veiculo(cxEntrada1CadV.getText(), cxEntrada1CadV1.getText(),Integer.parseInt(cxEntrada1CadV2.getText()));           
            Veiculo cadastroveiculo = new Veiculo(cxEntrada1CadV.getText(), cxEntrada1CadV1.getText(), cxEntrada1CadV2.getText(), Integer.parseInt(cxEntrada1CadV3.getText()));
            //Veiculo cadastroveiculo = new Veiculo(montadora, modelo, placa, ABORT);
            funcoes.cadastroVeiculos(cadastroveiculo);
            selecaoAgenteCar.addItem(cxEntrada1CadV2.getText()); // adiciona veiculo no menu da tela agente
            //selecaoAgenteCar.addItem(cxEntrada1CadV1.getText()); // adiciona veiculo no menu da tela agente
            limpartelaVeiculos(); // limpa a tela de cadastro de veículos
        }
    }

    // ====== limpar tela cadastro motoristas ======
    public void limparTelaMotoristas() {
        String vazio = "";
        cxEntrada5Agente.setText(vazio);
        cxEntrada6Agente.setText(vazio);
        cxEntrada6Agente1.setText(vazio);
        cxEntrada1Agente.setText(vazio);
        cxEntrada2Agente.setText(vazio);
        cxEntrada3Agente.setText(vazio);
        cxEntrada4Agente.setText(vazio);
    }

    // ====== limpar tela motorista ======
    public void limparTelaMotorista() {
        String vazio = "";
        saidaTextoMotorista.setText(vazio);
    }

    // ====== limpar tela cadastro veículos ======
    public void limpartelaVeiculos() {
        String vazio = "";
        cxEntrada1CadV.setText(vazio);
        cxEntrada1CadV1.setText(vazio);
        cxEntrada1CadV2.setText(vazio);
        cxEntrada1CadV3.setText(vazio);
    }

    // ====== limpar tela de multas ======
    public void limparTelaMultas() {
        selecaoAgenteCar.setSelectedIndex(0);
        selecaoAgenteMot.setSelectedIndex(0);
        selecaoAgenteMul.setSelectedIndex(0);
    }

    public void limparTelaMultas1() {
        String vazio = "";
        cxEntrada1CadM.setText(vazio);
        cxEntrada2CadM.setText(vazio);
        cxEntrada3CadM.setText(vazio);
    }
    // ====== limpar tela gerente ======
    public void limparTelaGerente(){
        String vazio = "";
        saidaGerente.setText(vazio);
        selecaoGerente.setSelectedIndex(0);
    }

    // ====== dados gerente ======
    public void dadosGerente() {
        if (selecaoGerente.getSelectedItem().equals("Veiculos")) {
            //funcoes.veiculosCadastrados();
            String veiculos;
            veiculos = funcoes.veiculosCadastradosRetorno();
            saidaGerente.setText(veiculos);
        }
        if (selecaoGerente.getSelectedItem().equals("Motoristas")) {
            //funcoes.motoristasCadastrados();
            String motoristas;
            motoristas = funcoes.motoristasCadastradosRetorno();
            saidaGerente.setText(motoristas);
        }
        if (selecaoGerente.getSelectedItem().equals("Multas")) {
            String multasEmpresaLog = "";
            saidaGerente.setText(multasEmpresaLog);
            if (qtdMultasEmpresa > 1) {
                saidaGerente.setText("A empresa tem " + qtdMultasEmpresa + " multas \nno valor total de R$: " + valorDasMultas);
            } else if (qtdMultasEmpresa == 1) {
                saidaGerente.setText("A empresa tem " + qtdMultasEmpresa + " multa \nno valor total de R$: " + valorDasMultas);
            } else {
                saidaGerente.setText("A empresa não tem multas");
            }
            //System.out.println(valorTotalMultas());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelDeAbas = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        entradaNomeLogin = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        entradaSenhaLogin = new javax.swing.JPasswordField();
        botaoEntrarLogin = new javax.swing.JButton();
        botaoSairLogin = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cxEntrada1Agente = new javax.swing.JTextField();
        cxEntrada2Agente = new javax.swing.JTextField();
        cxEntrada3Agente = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cxEntrada5Agente = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cxEntrada4Agente = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        cxEntrada6Agente = new javax.swing.JPasswordField();
        cxEntrada6Agente1 = new javax.swing.JPasswordField();
        jPanel3 = new javax.swing.JPanel();
        cxEntrada1CadV = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cxEntrada1CadV1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cxEntrada1CadV2 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        cxEntrada1CadV3 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        selecaoAgenteMot = new javax.swing.JComboBox<>();
        botaoAplicarMulta = new javax.swing.JButton();
        selecaoAgenteCar = new javax.swing.JComboBox<>();
        jButton6 = new javax.swing.JButton();
        selecaoAgenteMul = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cxEntrada1CadM = new javax.swing.JTextField();
        cxEntrada2CadM = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        cxEntrada3CadM = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        saidaTextoMotorista = new javax.swing.JTextArea();
        jButton10 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();
        selecaoGerente = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        saidaGerente = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        saidaMotoristaAdmin = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        entradaMotoristaAdmin = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        saidaVeiculoAdmin = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        entradaVeiculoAdmin = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        saidaVeiculoAdmin1 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        entradaMotoristaAdmin2 = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel15.setText("Usuário:");

        jLabel16.setText("Senha:");

        botaoEntrarLogin.setText("Entrar");
        botaoEntrarLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEntrarLoginActionPerformed(evt);
            }
        });

        botaoSairLogin.setText("Sair");
        botaoSairLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSairLoginActionPerformed(evt);
            }
        });

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/logo.png"))); // NOI18N
        jLabel24.setText("jLabel24");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(entradaSenhaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(entradaNomeLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(botaoEntrarLogin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoSairLogin)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(entradaNomeLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(entradaSenhaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botaoEntrarLogin)
                            .addComponent(botaoSairLogin)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel24)))
                .addContainerGap(171, Short.MAX_VALUE))
        );

        painelDeAbas.addTab("Entrada", jPanel1);

        cxEntrada1Agente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cxEntrada1AgenteActionPerformed(evt);
            }
        });

        cxEntrada2Agente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cxEntrada2AgenteActionPerformed(evt);
            }
        });

        cxEntrada3Agente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cxEntrada3AgenteActionPerformed(evt);
            }
        });

        jLabel1.setText("Nome:");

        jLabel2.setText("CPF:");

        jLabel3.setText("RG:");

        cxEntrada5Agente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cxEntrada5AgenteActionPerformed(evt);
            }
        });

        jLabel4.setText("Nome usuário:");

        jLabel5.setText("RI:");

        jLabel6.setText("Senha:");

        jButton1.setText("Cadastrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel14.setText("Repita senha:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(44, 44, 44))
                            .addComponent(cxEntrada5Agente, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cxEntrada3Agente, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                            .addComponent(cxEntrada1Agente))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cxEntrada6Agente1)
                        .addComponent(cxEntrada2Agente, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                        .addComponent(cxEntrada4Agente)
                        .addComponent(cxEntrada6Agente))
                    .addComponent(jButton2))
                .addContainerGap(112, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cxEntrada1Agente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(cxEntrada2Agente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cxEntrada4Agente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cxEntrada3Agente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cxEntrada5Agente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cxEntrada6Agente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cxEntrada6Agente1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(146, Short.MAX_VALUE))
        );

        painelDeAbas.addTab("Cadastro Motoristas", jPanel2);

        cxEntrada1CadV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cxEntrada1CadVActionPerformed(evt);
            }
        });

        jLabel7.setText("Montadora:");

        jLabel8.setText("Modelo:");

        cxEntrada1CadV1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cxEntrada1CadV1ActionPerformed(evt);
            }
        });

        jLabel9.setText("Placa:");

        cxEntrada1CadV2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cxEntrada1CadV2ActionPerformed(evt);
            }
        });

        jButton3.setText("Cadastrar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Cancelar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel10.setText("Ano:");

        cxEntrada1CadV3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cxEntrada1CadV3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(cxEntrada1CadV2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cxEntrada1CadV, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cxEntrada1CadV1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(cxEntrada1CadV3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(348, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cxEntrada1CadV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cxEntrada1CadV1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cxEntrada1CadV2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cxEntrada1CadV3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(139, Short.MAX_VALUE))
        );

        painelDeAbas.addTab("Cadastro Veiculos", jPanel3);

        selecaoAgenteMot.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione um motorista" }));

        botaoAplicarMulta.setText("Aplicar");
        botaoAplicarMulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAplicarMultaActionPerformed(evt);
            }
        });

        selecaoAgenteCar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione um veiculo" }));

        jButton6.setText("Cancelar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        selecaoAgenteMul.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione uma Multa" }));

        jLabel11.setText("Descrição:");

        jLabel12.setText("Valor:");

        jButton7.setText("Cadastrar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Cancelar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel13.setText("Pontuação:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selecaoAgenteCar, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(selecaoAgenteMot, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(selecaoAgenteMul, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(botaoAplicarMulta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton6)))
                        .addGap(75, 75, 75)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jButton7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton8))
                            .addComponent(cxEntrada2CadM, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cxEntrada1CadM, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cxEntrada3CadM, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selecaoAgenteMot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(cxEntrada1CadM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selecaoAgenteCar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cxEntrada2CadM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cxEntrada3CadM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13))
                    .addComponent(selecaoAgenteMul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoAplicarMulta)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addContainerGap(118, Short.MAX_VALUE))
        );

        painelDeAbas.addTab("Aplicação de Multa", jPanel4);

        saidaTextoMotorista.setEditable(false);
        saidaTextoMotorista.setColumns(20);
        saidaTextoMotorista.setRows(5);
        jScrollPane1.setViewportView(saidaTextoMotorista);

        jButton10.setText("Consultar");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton10)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(270, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton10)
                .addContainerGap(125, Short.MAX_VALUE))
        );

        painelDeAbas.addTab("Motorista", jPanel5);

        jButton11.setText("Mostrar");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        selecaoGerente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione um item", "Motoristas", "Veiculos", "Multas" }));

        saidaGerente.setEditable(false);
        saidaGerente.setColumns(20);
        saidaGerente.setRows(5);
        jScrollPane2.setViewportView(saidaGerente);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                    .addComponent(selecaoGerente, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton11, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(319, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(selecaoGerente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton11)
                .addContainerGap(106, Short.MAX_VALUE))
        );

        painelDeAbas.addTab("Gerência", jPanel6);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 0, 0));
        jLabel17.setText("Administração");

        jLabel18.setText("Quantidade de motoristas:");

        saidaMotoristaAdmin.setEditable(false);

        jLabel19.setText("Novo valor:");

        jButton5.setText("Aplicar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel20.setText("Quantidade de veiculos:");

        saidaVeiculoAdmin.setEditable(false);

        jLabel21.setText("Novo valor:");

        jButton12.setText("Aplicar");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jLabel22.setText("Quantidade de multas:");

        saidaVeiculoAdmin1.setEditable(false);

        jLabel23.setText("Novo valor:");

        jButton13.setText("Aplicar");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(saidaMotoristaAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(entradaMotoristaAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(saidaVeiculoAdmin1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(30, 30, 30)
                                .addComponent(saidaVeiculoAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(entradaVeiculoAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton12))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(entradaMotoristaAdmin2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton13)))))
                .addContainerGap(208, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(saidaMotoristaAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(entradaMotoristaAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton12)
                    .addComponent(entradaVeiculoAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(saidaVeiculoAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton13)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22)
                        .addComponent(saidaVeiculoAdmin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel23)
                        .addComponent(entradaMotoristaAdmin2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(202, Short.MAX_VALUE))
        );

        painelDeAbas.addTab("Admin", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelDeAbas, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(painelDeAbas, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cxEntrada1AgenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cxEntrada1AgenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cxEntrada1AgenteActionPerformed

    private void cxEntrada3AgenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cxEntrada3AgenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cxEntrada3AgenteActionPerformed

    private void cxEntrada5AgenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cxEntrada5AgenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cxEntrada5AgenteActionPerformed

    private void cxEntrada2AgenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cxEntrada2AgenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cxEntrada2AgenteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (cxEntrada5Agente.getText().isEmpty() || cxEntrada6Agente.getText().isEmpty() || cxEntrada6Agente1.getText().isEmpty() || cxEntrada1Agente.getText().isEmpty() || cxEntrada2Agente.getText().isEmpty() || cxEntrada3Agente.getText().isEmpty() || cxEntrada4Agente.getText().isEmpty()) {
           // System.out.println("Algum campo está vazio");
           janelaErro400();
        } else if (cxEntrada6Agente.getText().equals(cxEntrada6Agente1.getText())) {
            Motorista cadastroMotorista = new Motorista(cxEntrada5Agente.getText(), cxEntrada6Agente.getText(), cxEntrada1Agente.getText(), cxEntrada2Agente.getText(), cxEntrada3Agente.getText(), cxEntrada4Agente.getText());
            //Motorista cadastroMotorista = new Motorista(nomeUsuario, senhaUsuario, nome, cpf, rg, ri);
            funcoes.cadastroMotoristas(cadastroMotorista); // método para cadastrar motorista
            selecaoAgenteMot.addItem(cxEntrada1Agente.getText()); // adiciona motorista no menu da tela agente
            limparTelaMotoristas();
            // adiciona motorista no menu da tela agente
        } else {
           // System.out.println("Senhas não são iguais");
           janelaErro403();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        limparTelaMotoristas();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cxEntrada1CadVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cxEntrada1CadVActionPerformed
        // TODO add your handling code here:
        funcoes.veiculosCadastrados();
    }//GEN-LAST:event_cxEntrada1CadVActionPerformed

    private void cxEntrada1CadV1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cxEntrada1CadV1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cxEntrada1CadV1ActionPerformed

    private void cxEntrada1CadV2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cxEntrada1CadV2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cxEntrada1CadV2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        cadastroVeiculos();
    }

    /*        
        //Veiculo cadastroveiculo = new Veiculo(cxEntrada1CadV.getText(), cxEntrada1CadV1.getText(),Integer.parseInt(cxEntrada1CadV2.getText()));           
        Veiculo cadastroveiculo = new Veiculo(cxEntrada1CadV.getText(), cxEntrada1CadV1.getText(), cxEntrada1CadV2.getText(), Integer.parseInt(cxEntrada1CadV3.getText()));
        //Veiculo cadastroveiculo = new Veiculo(montadora, modelo, placa, ABORT);
        funcoes.cadastroVeiculos(cadastroveiculo);
        selecaoAgenteCar.addItem(cxEntrada1CadV1.getText()); // adiciona veiculo no menu da tela agente
    }//GEN-LAST:event_jButton3ActionPerformed
*/
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        limpartelaVeiculos();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cxEntrada1CadV3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cxEntrada1CadV3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cxEntrada1CadV3ActionPerformed

    private void botaoAplicarMultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAplicarMultaActionPerformed
        // TODO add your handling code here:
        aplicacaoMulta();
    }//GEN-LAST:event_botaoAplicarMultaActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        limparTelaMultas();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        if (cxEntrada1CadM.getText().isEmpty()
                || cxEntrada2CadM.getText().isEmpty()
                || cxEntrada3CadM.getText().isEmpty()) {
            //System.out.println("Preencha todos os campos");
            janelaErro400();
        } else {
            //Multa cadastromulta = new Multa(cxEntrada1CadM.getText(), Integer.parseInt(cxEntrada2CadM.getText()),Integer.parseInt(cxEntrada3CadM.getText()));
            Multa cadastromulta = new Multa(cxEntrada1CadM.getText(), Float.valueOf(cxEntrada2CadM.getText()), Integer.parseInt(cxEntrada3CadM.getText()));
            //Multa cadastromulta = new Multa(descricao, TOP_ALIGNMENT, NORMAL));
            funcoes.cadastroMultas(cadastromulta);
            selecaoAgenteMul.addItem(cxEntrada1CadM.getText()); // adiciona a multa no menu 
            limparTelaMultas1();
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        limparTelaMultas1();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void botaoEntrarLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEntrarLoginActionPerformed
        // TODO add your handling code here:
        if (entradaNomeLogin.getText().isEmpty() || entradaSenhaLogin.getText().isEmpty()) {
            //System.out.println("Campos vazios");
            janelaErro400();
        } else {
            checagemDeDadosLogin();
        }
    }//GEN-LAST:event_botaoEntrarLoginActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        //System.out.println(nomeMotoristaLogado);
        String dados;
        Motorista motoristaDados = funcoes.verificarMotorista(nomeMotoristaLogado); // retorna um motorista cadastrado
        dados = motoristaDados.multasTomadas();
        System.out.println(dados);
        saidaTextoMotorista.setText(dados);
        // motoristaDados.multasTomadas(); 
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        dadosGerente();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void botaoSairLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSairLoginActionPerformed
        // TODO add your handling code here:
        limparTelaMotoristas();
        limparTelaMultas();
        limparTelaMultas1();
        limpartelaVeiculos();
        limparTelaMotorista();
        travarAbas();
        liberarTelaLogin();
        limparTelaGerente();
    }//GEN-LAST:event_botaoSairLoginActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        int valorNovo = Integer.parseInt(entradaMotoristaAdmin.getText());

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        int valorNovo = Integer.parseInt(entradaVeiculoAdmin.getText());

    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAplicarMulta;
    private javax.swing.JButton botaoEntrarLogin;
    private javax.swing.JButton botaoSairLogin;
    private javax.swing.JTextField cxEntrada1Agente;
    private javax.swing.JTextField cxEntrada1CadM;
    private javax.swing.JTextField cxEntrada1CadV;
    private javax.swing.JTextField cxEntrada1CadV1;
    private javax.swing.JTextField cxEntrada1CadV2;
    private javax.swing.JTextField cxEntrada1CadV3;
    private javax.swing.JTextField cxEntrada2Agente;
    private javax.swing.JTextField cxEntrada2CadM;
    private javax.swing.JTextField cxEntrada3Agente;
    private javax.swing.JTextField cxEntrada3CadM;
    private javax.swing.JTextField cxEntrada4Agente;
    private javax.swing.JTextField cxEntrada5Agente;
    private javax.swing.JPasswordField cxEntrada6Agente;
    private javax.swing.JPasswordField cxEntrada6Agente1;
    private javax.swing.JTextField entradaMotoristaAdmin;
    private javax.swing.JTextField entradaMotoristaAdmin2;
    private javax.swing.JTextField entradaNomeLogin;
    private javax.swing.JPasswordField entradaSenhaLogin;
    private javax.swing.JTextField entradaVeiculoAdmin;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane painelDeAbas;
    private javax.swing.JTextArea saidaGerente;
    private javax.swing.JTextField saidaMotoristaAdmin;
    private javax.swing.JTextArea saidaTextoMotorista;
    private javax.swing.JTextField saidaVeiculoAdmin;
    private javax.swing.JTextField saidaVeiculoAdmin1;
    private javax.swing.JComboBox<String> selecaoAgenteCar;
    private javax.swing.JComboBox<String> selecaoAgenteMot;
    private javax.swing.JComboBox<String> selecaoAgenteMul;
    private javax.swing.JComboBox<String> selecaoGerente;
    // End of variables declaration//GEN-END:variables
}
