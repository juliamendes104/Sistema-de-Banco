package com.mycompany.bancounesp;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A classe Banco cria a estrutura de um banco com uma lista de agências.
 * @author Julia
 */
public class Banco {
    private int numero;
    private String nome;
    private String cnpj;
    private String endereco;
    private Conta contaLogada;
    private ArrayList<Agencia> agencias;
    
    Scanner sc = new Scanner(System.in);
    
    /**
     * Constrói um banco com nome, número, cnpj e endereço e cria a lista de
     * agências.
     * @param nome o nome do banco
     * @param numero o número do banco
     * @param cnpj o cnpj do banco
     * @param endereco o endereço do banco
     */
    public Banco(String nome, int numero, String cnpj, String endereco){
        this.nome = nome;
        this.numero = numero;
        this.cnpj = cnpj;
        this.endereco = endereco;
        agencias = new ArrayList<>();
    }
    
    /**
     * Atribui uma conta ao atributo contaLogada desse banco.
     * @param numAgencia o número de agência da conta
     * @param numConta a identificação da conta
     * @param senha a senha da conta
     * @return verdadeiro, se encontrar uma conta com os valores correspondentes,
     * falso, se não encontrar a conta
     */
    public boolean logarCliente(int numAgencia, int numConta, String senha){
        Agencia item = this.buscarAgencia(numAgencia);
        if(item != null){
            for(Conta conta : item.getContas()){
                if(conta.getNumeroConta()==numConta){
                    if(conta.validarSenha(senha)){
                        this.contaLogada = item.buscarConta(numConta, senha);
                        return true;
                    }
                    else{
                        return false;
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * A contaLogada desse banco chama um método para sacar um valor.
     * @param valor o valor do saque
     * @return verdadeiro, se o método chamado retornar verdadeiro, falso, se o
     * método chamado retornar falso
     */
    public boolean realizarSaque(double valor){
        return this.contaLogada.sacar(valor);
    }
    
    /**
     * A contaLogada desse banco chama um método para depositar um valor.
     * @param valor o valor do depósito
     */
    public void realizarDeposito(double valor){
        this.contaLogada.depositar(valor);
    }
    
    /**
     * Acessa o saldo da contaLogada desse banco.
     * @return o saldo da conta
     */
    public double saldo(){
        return this.contaLogada.getSaldo();
    }
    
    /**
     * Esse banco chama um método para adicionar a agência criada com código, nome
     * e endereço.
     * @param codigo o código da agência
     * @param nome o nome da agência
     * @param endereco o endereço da agência
     */
    public void cadastrarAgencia(int codigo, String nome, String endereco){
        this.cadastrarAgencia(new Agencia(nome,codigo,endereco));
    }
    
    /**
     * Adiciona a agência na lista.
     * @param agencia a nova agência
     */
    public void cadastrarAgencia(Agencia agencia){
        this.agencias.add(agencia);
    }
    
    /**
     * Cria uma conta e chama o método da classe Agencia para adicionar a conta
     * na sua agência correspondente.
     * @param numAgencia o número da agência da conta
     * @param numConta a identificação da conta
     * @param nome o nome do titular da conta
     * @param dataNascimento a data de nascimento do titular
     * @param endereco o endereço do titular
     * @param cpf o cpf do titular
     * @param saldo o saldo do titular
     * @param senha a senha da conta
     */
    public void cadastrarConta(int numAgencia, int numConta, String nome, 
            String dataNascimento, String endereco, String cpf, double saldo, String senha){
        for(Agencia item: this.getAgencias()){
                if(item.getCodigo()== numAgencia){
                    item.cadastrarConta(new Conta(nome,dataNascimento,endereco,
                            cpf,saldo,numAgencia,numConta,senha));
                    break;
                }
            }
    }
    
    /**
     * Busca a agência pelo código.
     * @param codigo o código da agência
     * @return a agência, se encontrada, null, se não encontrada
     */
    public Agencia buscarAgencia(int codigo){
        for(Agencia agencia : this.agencias){
            if(agencia.getCodigo() == codigo){
                return agencia;
            }
        }
        return null;
    }
    
    /**
     * Transferir um valor da contaLogada desse banco para outra conta com os
     * dados correspondentes.
     * @param numAgencia o número da agência da conta para que se quer transferir
     * @param numConta a identificação da conta para que se quer transferir
     * @param valor o valor da transferência
     */
    public void transferencia(int numAgencia, int numConta, double valor){
        Agencia agencia = buscarAgencia(numAgencia);
        if(agencia!=null){
            Conta conta = agencia.buscarConta(numConta);
            if(conta!=null){
                if(realizarSaque(valor)){
                    conta.depositar(valor);
                    System.out.println("Transferência realizada.");
                }
                else{
                    System.out.println("Não há saldo suficiente para a transferência.");
                }
            }
            else{
                System.out.println("Conta não encontrada.");
            }
        }
        else{
            System.out.println("Conta não encontrada.");
        }
    }
    
    /**
     * Fazer um PIX da contaLogada desse banco para outra conta pela chavePIX dela.
     * @param chaveCPF o cpf do titular
     */
    public void pix(String chaveCPF){
        Conta conta = null;
        for(Agencia agencia: this.getAgencias()){
            if(agencia.buscarConta(chaveCPF)!=null){
                conta = agencia.buscarConta(chaveCPF);
            }
        }
        if(conta!=null){
            System.out.println("Digite o valor para pagar por PIX:");
            double valor = sc.nextDouble();
            if(realizarSaque(valor)){
                    conta.depositar(valor);
            }
            else{
                System.out.println("Não há saldo suficiente para a transferência.");
            }
        }
        else{
            System.out.println("Chave não encontrada.");
        }
    }
    
    /**
     * Atribuir null a contaLogada desse banco.
     */
    public void deslogarConta(){
        this.contaLogada = null;
    }
    
    /**
     * Alterar o nome do titular da contaLogada desse banco
     */
    public void nome(){
        System.out.println("Digite o novo Nome: ");
        String nomeNovo = sc.nextLine();
        this.contaLogada.setNome(nomeNovo);
    }
    
    /**
     * Alterar o endereço do titular da contaLogada desse banco
     */
    public void endereco(){
        System.out.println("Digite o novo Endereço: ");
        String enderecoNovo = sc.nextLine();
        this.contaLogada.setEndereco(enderecoNovo);
    }
    
    /**
     * Alterar a senha da contaLogada desse banco
     */
    public void senha(){
        System.out.println("Digite a Senha atual: ");
        String senhaAtual = sc.nextLine();
        System.out.println("Digite a nova Senha: ");
        String senhaNova = sc.nextLine();
        this.contaLogada.setSenha(senhaAtual,senhaNova);
    }
    
    /**
     * Imprimir o extrato da contaLogada por meio de um método
     */
    public void extrato(){
        this.contaLogada.imprimirExtrato();
    }
    
    public ArrayList<Agencia> getAgencias() {
        return agencias;
    }

    public void setAgencias(ArrayList<Agencia> agencias) {
        this.agencias = agencias;
    }
    
    //testando os campos Banco e a lista de Agencias
    public void imprimir(){
        System.out.println("Nome do banco:" + this.nome + " Número do banco:" + this.numero + " CNPJ:" + this.cnpj + " Endereço:" + this.endereco);
        for(Agencia item: agencias){
            System.out.println("Nome da agência:" + item.getNome() + " Código:" + item.getCodigo() + " Endereço:" + item.getEndereco());
            for(Conta i: item.getContas()){
                System.out.println("   Nome do proprietario da conta:" + i.getNome() + " Código da agência:" + i.getNumeroAgencia());
            }
        }
    }
}
