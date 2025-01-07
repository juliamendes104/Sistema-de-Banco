package com.mycompany.bancounesp;

import java.util.ArrayList;

/**
 * A classe Extrato cria uma lista contendo registros sobre a movimentacao de
 * uma conta bancária.
 * @author Julia
 */
public class Extrato {
    private ArrayList<String> registros;
    private Conta conta;
    
    /**
     * Constroi um extrato com uma conta e cria a lista de registros.
     * @param conta a conta bancária que criou o extrato
     */
    public Extrato(Conta conta){
        this.conta = conta;
        registros = new ArrayList<>();
    }
    
    /**
     * Imprime no console o nome, número da conta e saldo do titular com as
     * operações de depósito e saque.
     */
    public void imprimirRegistros(){
        System.out.println("Nome do titular: " + conta.getNome());
        System.out.println("Número da conta: " + conta.getNumeroConta());
        for(String registro: this.registros){
            System.out.println(registro);
        }
        System.out.println("Saldo: " + conta.getSaldo());
    }
    
    public ArrayList<String> getRegistros(){
        return registros;
    }
    
    /**
     * Adiciona um registro na lista.
     * @param registro a frase sobre a operação de depósito ou saque
     */
    public void setRegistros(String registro){
        this.registros.add(registro);
    }
}
