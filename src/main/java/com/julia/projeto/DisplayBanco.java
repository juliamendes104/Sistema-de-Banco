package com.mycompany.bancounesp;

import java.util.Scanner;

/**
 * A classe DisplayBanco controla as operações e funcionamento de um banco.
 * @author Julia
 */
public class DisplayBanco {
    private Banco meuBanco;
    Scanner scan = new Scanner(System.in);
    
    /**
     * Constrói um mostrador que intermedeia as operações entre o banco e o usuário.
     * @param banco um array de Strings com os campos da classe Banco
     * @param agencias um array de Strings com os campos da classe Agencia
     * @param contas um array de Strings com os campos da classe Conta
     */
    public DisplayBanco(String[] banco, String[] agencias, String[] contas){
        this.meuBanco = new Banco(banco[0],Integer.parseInt(banco[1]),banco[2],banco[3]);
        
        for(int i=0; i<agencias.length; i+=3){
            this.meuBanco.cadastrarAgencia(Integer.parseInt(agencias[i+1]),
                    agencias[i],agencias[i+2]);
        }
        
        for(int i=0; i<contas.length; i+=8){
            this.meuBanco.cadastrarConta(Integer.parseInt(contas[i+5]),
                    Integer.parseInt(contas[i+6]), contas[i], contas[i+1],
                    contas[i+2], contas[i+3], Double.parseDouble(contas[i+4]),
                    contas[i+7]);
        }
    }
    
    /**
     * Permite que o usuário faça o login e logue na sua conta.
     */
    public void login(){
        Scanner sc = new Scanner(System.in);
        System.out.println("*--*--*--*--*--BEM-VINDO!--*--*--*--*--*");
        System.out.println("Digite o número da agência:");
        int numAgencia = sc.nextInt();
        
        System.out.println("Digite o número da conta:");
        int numConta = sc.nextInt();
        
        sc.nextLine();//Para evitar a quebra de linha
        
        System.out.println("Digite a senha:");
        String senha = sc.nextLine();
        
        if(!this.meuBanco.logarCliente(numAgencia,numConta,senha)){
            System.out.println("Algo deu errado.");
            login();
        }
        else{
            System.out.println("Logado com sucesso.");
            telaUsuario();
        }
    }
    
    /**
     * Mostra no console as operações possíveis de fazer na conta e invoca os 
     * outros métodos.
     */
    private void telaUsuario(){
        Scanner sc = new Scanner(System.in);
        int operacoes;
        do{
            System.out.println();
            System.out.println("*--*--*--*--*--Selecione uma opçao:");
            System.out.println("1- Consultar Saldo");
            System.out.println("2- Depósito");
            System.out.println("3- Saque");
            System.out.println("4- Transferência");
            System.out.println("5- Pix");
            System.out.println("6- Atualizar dados");
            System.out.println("7- Extrato");
            System.out.println("8- Sair");
            operacoes = sc.nextInt();
        
            switch(operacoes){
                case 1: operacaoSaldo();
                    break;
                case 2: operacaoDeposito();
                    break;
                case 3: operacaoSaque();
                    break;
                case 4: operacaoTransferencia();
                    break;
                case 5: operacaoPix();
                    break;
                case 6: operacaoAtualizar();
                    break;
                case 7: operacaoExtrato();
                    break;
                case 8: operacaoSair();
                    break;
                default:
                    System.out.println("Selecione um número de 1 a 8.");
                    break;
            }
        }while(operacoes!=8);
        
    }
    
    /**
     * Operação para invocar o método da classe Banco para realizar um dopósito.
     */
    private void operacaoDeposito(){
        System.out.println("Digite o valor para depósito:");
        double valor = scan.nextDouble();
        this.meuBanco.realizarDeposito(valor);
        System.out.println("Depósito realizado.");
    }
    
    /**
     * Operação para invocar o método da classe Banco para realizar um saque.
     */
    private void operacaoSaque(){
        System.out.println("Digite o valor para saque:");
        double valor = scan.nextDouble();
        if(this.meuBanco.realizarSaque(valor)){
            System.out.println("Saque realizado.");
        }
        else{
            System.out.println("Não foi possível realizar o saque.");
        }
    }
    
    /**
     * Operação para invocar o método da classe Banco para fazer um PIX.
     */
    private void operacaoPix(){
        scan.nextLine();
        System.out.println("Digite a chave PIX: ");
        String chavePix = scan.nextLine();
        //System.out.println("Digite o valor para pagar por PIX: ");
        this.meuBanco.pix(chavePix);
    }
    
    /**
     * Operação para invocar o método da classe Banco para realizar uma transferência.
     */
    private void operacaoTransferencia(){
        System.out.println("Digite o número da agência da conta para que deseja transferir:");
        int numAgencia = scan.nextInt();
        System.out.println("Digite o número da conta para que deseja transferir:");
        int numConta = scan.nextInt();
        System.out.println("Digite o valor para transferência:");
        double valor = scan.nextDouble();
        
        this.meuBanco.transferencia(numAgencia,numConta,valor);  
    }
    
    /**
     * Operação para invocar o método da classe Banco para mostrar o saldo.
     */
    private void operacaoSaldo(){
        System.out.println("Saldo: " + this.meuBanco.saldo());
    }
    
    /**
     * Operação para invocar o método da classe Banco para deslogar da conta.
     */
    private void operacaoSair(){
        this.meuBanco.deslogarConta();
        System.out.println("VOLTE SEMPRE!");
        BancoUNESP.clearConsole();
        login();
    }
    
    /**
     * Operação para invocar os métodos da classe Banco para atualizar ou alterar
     * um dado.
     */
    private void operacaoAtualizar(){
        int operacao;
        System.out.println("1- Atualizar Nome Completo");
        System.out.println("2- Atualizar Endereço");
        System.out.println("3- Alterar Senha");
        operacao = scan.nextInt();
        
        switch(operacao){
            case 1: this.meuBanco.nome();
                break;
            case 2: this.meuBanco.endereco();
                break;
            case 3: this.meuBanco.senha();
                break;
            default:
                System.out.println("Para outras atualizações vá até uma de nossas agências físicas.");
                break;
        }
        
    }
    
    /**
     * Operação para invocar o método da classe Banco para imprimir o extrato.
     */
    private void operacaoExtrato(){
        this.meuBanco.extrato();
    }
    
    public Banco getBanco(){
        return meuBanco;
    }
    
}
