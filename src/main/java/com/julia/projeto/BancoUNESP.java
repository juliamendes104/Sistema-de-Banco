package com.mycompany.bancounesp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

/**
 * A classe BancoUNESP cria um sistema de banco.
 * @author Julia
 */
public class BancoUNESP {

    private static final String BANCO_FILE = "C:/Users/julia/OneDrive/Documentos/BCC/banco.txt";
    private static final String AGENCIAS_FILE = "C:\\Users\\julia\\OneDrive\\Documentos\\BCC\\agencias.txt";
    private static final String CONTAS_FILE = "C:\\Users\\julia\\OneDrive\\Documentos\\BCC\\contas.txt";
    
    /**
     * Cria um arquivo com o caminho e desenvolve os campos em um array de String.
     * @return um array de String com os campos do arquivo Banco
     */
    public static String[] banco(){
        File arquivo = new File(BANCO_FILE);
        Scanner scanner = null;
        
        try{
            scanner = new Scanner(arquivo);
        }catch(FileNotFoundException ex){
            Logger . getLogger ( BancoUNESP . class . getName () ) . log ( Level .SEVERE , null ,ex) ;
        }
        
        String linha = scanner.nextLine();
            
        String[] camposBanco = linha.split("#");
            
            //for(String campo: camposBanco){
                //System.out.println(campo + " | ");
            //}
        return camposBanco;
    }
    
    /**
     * Cria um arquivo com o caminho e desenvolve os campos em arraya de String.
     * @return um array de String com os campos do arquivo Agencias
     */
    public static String[] agencias(){
        File arquivo = new File(AGENCIAS_FILE);
        Scanner scanner = null;
        ArrayList<String[]> agenciasList = new ArrayList<>();
        
        try{
            scanner = new Scanner(arquivo);
        }catch(FileNotFoundException ex){
            Logger . getLogger ( BancoUNESP . class . getName () ) . log ( Level .SEVERE , null ,ex) ;
        }
        
        while(scanner.hasNextLine()){
            String linha = scanner.nextLine();
            
             String[] camposAgencia = linha.split("#");
             agenciasList.add(camposAgencia);
            
            //for(String campo: camposBanco){
                //System.out.println(campo + " | ");
            //}
        }
        String[] camposAgencia = new String[agenciasList.size() * agenciasList.get(0).length];
        int i = 0;
        for (String[] agencia : agenciasList) {
            for (String campo : agencia) {
                camposAgencia[i] = campo;
                i++;
            }
        }
        return camposAgencia;
    }
    
    /**
     * Cria um arquivo com o caminho e desenvolve os campos em arrays de String.
     * @return um array de String com os campos do arquivo Contas
     */
    public static String[] contas(){
       File arquivo = new File(CONTAS_FILE);
        Scanner scanner = null;
        ArrayList<String[]> contasLista = new ArrayList<>();
        
        try{
            scanner = new Scanner(arquivo);
        }catch(FileNotFoundException ex){
            Logger . getLogger ( BancoUNESP . class . getName () ) . log ( Level .SEVERE , null ,ex) ;
        }
        
        while(scanner.hasNextLine()){
            String linha = scanner.nextLine();
            
            String[] camposContas = linha.split("#");
            contasLista.add(camposContas);
            
            //for(String campo: camposContas){
                //System.out.println(campo + " | ");
            //}
        }
        String[] camposContas = new String[contasLista.size() * contasLista.get(0).length];
        int i = 0;
        for(String[] contas: contasLista){
            for(String campo: contas){
                camposContas[i] = campo;
                i++;
            }
        }
        return camposContas;
    }
    
    /**
     * Limpa o console.
     */
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        }  catch (IOException | InterruptedException e) {
              System.out.println("Erro.");
        }
    }
    
    public static void main(String[] args) {
        
        String[] camposBanco = banco();
        String[] camposAgencia = agencias();
        String[] camposContas = contas();
        
        DisplayBanco mostrador = new DisplayBanco(camposBanco, camposAgencia,camposContas);
        mostrador.login();
    }  
}

