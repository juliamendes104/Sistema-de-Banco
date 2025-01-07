package com.mycompany.bancounesp;

/**
 * A classe Conta cria a estrutura de uma conta bancária.
 * @author Julia
 */
public class Conta {
    private int numeroConta;
    private int numeroAgencia;
    private double saldo;
    private String nome;
    private String endereco;
    private String cpf;
    private String dataNascimento;
    private String senha;
    private Extrato extrato;
    
    /**
     * Constroi uma conta com os dados do titular e número da agência e da conta.
     * @param nome o nome do titular
     * @param dataNascimento a data de nascimento do titular
     * @param endereco o endereço do titular
     * @param cpf o cpf do titular
     * @param saldo o saldo do titular
     * @param NumAgencia o número da agência da conta
     * @param NumConta o número para identificar a conta
     * @param senha a senha da conta
     */
    public Conta(String nome, String dataNascimento, String endereco, String cpf,
            double saldo, int NumAgencia, int NumConta, String senha){
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.cpf = cpf;
        this.saldo = saldo;
        this.numeroAgencia = NumAgencia;
        this.numeroConta = NumConta;
        this.senha = senha;
        this.extrato = new Extrato(this);
    }
    
    /**
     * Incrementar o saldo em um valor.
     * @param valor o valor do depósito
     */
    public void depositar(double valor){
        this.saldo += valor;
        this.extrato.setRegistros("Depósito de " + valor);
    }
    
    /**
     * Decrementar o saldo em um valor.
     * @param valor o valor do saque
     * @return verdadeiro, se o saldo foi decrementado, falso, se o saldo não
     * foi decrementado
     */
    public boolean sacar(double valor){
        if(this.saldo > valor){
            this.saldo -= valor;
            this.extrato.setRegistros("Saque de " + valor);
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Compara a senha dessa conta com a senha recebida.
     * @param senhaComparar a senha que é validada
     * @return verdadeiro, se as senhas são iguais, falso, se as senhas são
     * diferentes
     */
    public boolean validarSenha(String senhaComparar){
        return this.senha.equals(senhaComparar);
    }
    
    /**
     * Altera o valor da senha se a senha recebida for igual a senha dessa conta.
     * @param senhaAtual a senha que é validada
     * @param senhaNova a senha com que se quer alterar
     */
    public void setSenha(String senhaAtual, String senhaNova){
        if(this.validarSenha(senhaAtual)){
            this.senha = senhaNova;
        }
        else{
            System.out.println("Essa não é a senha atual correta.");
        }
    }
    
    /**
     * Essa conta chama o método da classe Extrato para imprimir as operações.
     */
    public void imprimirExtrato(){
        this.extrato.imprimirRegistros();
    }
    
    public void setEndereco(String enderecoNovo){
        this.endereco = enderecoNovo;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setNome(String nomeNovo){
        this.nome = nomeNovo;
    }
    
    public int getNumeroAgencia(){
        return numeroAgencia;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public String getSenha() {
        return senha;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getCpf() {
        return cpf;
    }
}
