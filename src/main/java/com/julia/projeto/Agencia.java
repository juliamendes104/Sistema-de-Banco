package com.mycompany.bancounesp;

import java.util.ArrayList;

/**
 * A classe Agencia cria a estrutura de uma agência de banco com uma lista de
 * contas.
 * @author Julia
 */
public class Agencia {
    private int codigo;
    private String nome;
    private String endereco;
    private ArrayList<Conta> contas;
    
    /**
     * Constrói uma agência com nome, código e endereço e cria a lista de contas.
     * @param nome o nome da agência
     * @param codigo o código da agência
     * @param endereco o endereço da agência
     */
    public Agencia(String nome, int codigo, String endereco){
        this.nome = nome;
        this.codigo = codigo;
        this.endereco = endereco;
        contas = new ArrayList<>();
    }
    
    /**
     * Adiciona uma conta na lista dessa agência.
     * @param conta a conta que é adicionada
     */
    public void cadastrarConta(Conta conta){
        this.contas.add(conta);
    }
    
    /**
     * Busca uma conta dessa agência pelo número da conta e senha.
     * @param numConta a identificação da conta procurada
     * @param senha a senha da conta procurada
     * @return a conta, se encontrada, null, se não encontrada
     */
    public Conta buscarConta(int numConta, String senha){
        for(Conta item : this.getContas()){
            if(item.getNumeroConta()==numConta && item.validarSenha(senha)){
                return item;
            }
        }
        return null;
    }
    
    /**
     * Busca uma conta dessa agência somente pelo número da conta.
     * @param numConta a identificação da conta procurada
     * @return a conta, se encontrada, null, se não encontrada
     */
    public Conta buscarConta(int numConta){
        for(Conta item : this.getContas()){
            if(item.getNumeroConta()==numConta){
                return item;
            }
        }
        return null;
    }
    
    /**
     * Busca a conta dessa agência pelo cpf do titular.
     * @param chaveCPF a chave pix do titular
     * @return a conta, se encontrada, null, se não encontrada
     */
    public Conta buscarConta(String chaveCPF){
        for(Conta item : this.getContas()){
            if(item.getCpf().equals(chaveCPF)){
                return item;
            }
        }
        return null;
    }
    
    public String getNome(){
        return nome;
    }
    
    public int getCodigo(){
        return codigo;
    }
    
    public String getEndereco(){
        return endereco;
    }

    public ArrayList<Conta> getContas() {
        return contas;
    }
}
