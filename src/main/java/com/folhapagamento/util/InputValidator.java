package com.folhapagamento.util;

import java.util.Scanner;

/**
 * Classe utilitária para validação de entrada de dados.
 * Centraliza toda lógica de validação, seguindo o princípio DRY (Don't Repeat Yourself).
 */
public class InputValidator {
    
    /**
     * Lê e valida um número inteiro positivo.
     * @param scanner Scanner para leitura
     * @param mensagem Mensagem para o usuário
     * @return Número inteiro positivo válido
     */
    public static int lerInteiroPositivo(Scanner scanner, String mensagem) {
        int numero;
        System.out.print(mensagem);
        
        do {
            while (!scanner.hasNextInt()) {
                System.out.print("Valor inválido! Digite um número inteiro positivo: ");
                scanner.next(); // Limpa entrada inválida
            }
            numero = scanner.nextInt();
            
            if (numero < 0) {
                System.out.print("Número não pode ser negativo! Digite novamente: ");
            }
        } while (numero < 0);
        
        return numero;
    }
    
    /**
     * Lê e valida um número decimal positivo.
     * @param scanner Scanner para leitura
     * @param mensagem Mensagem para o usuário
     * @return Número decimal positivo válido
     */
    public static double lerDoublePositivo(Scanner scanner, String mensagem) {
        double numero;
        System.out.print(mensagem);
        
        do {
            while (!scanner.hasNextDouble()) {
                System.out.print("Valor inválido! Digite um número positivo: ");
                scanner.next(); // Limpa entrada inválida
            }
            numero = scanner.nextDouble();
            
            if (numero < 0) {
                System.out.print("Número não pode ser negativo! Digite novamente: ");
            }
        } while (numero < 0);
        
        return numero;
    }
    
    /**
     * Lê e valida um percentual (0-100).
     * @param scanner Scanner para leitura
     * @param mensagem Mensagem para o usuário
     * @return Percentual válido entre 0 e 100
     */
    public static double lerPercentual(Scanner scanner, String mensagem) {
        double percentual;
        System.out.print(mensagem);
        
        do {
            while (!scanner.hasNextDouble()) {
                System.out.print("Valor inválido! Digite um percentual entre 0 e 100: ");
                scanner.next();
            }
            percentual = scanner.nextDouble();
            
            if (percentual < 0 || percentual > 100) {
                System.out.print("Percentual deve estar entre 0 e 100! Digite novamente: ");
            }
        } while (percentual < 0 || percentual > 100);
        
        return percentual;
    }
    
    /**
     * Lê uma string não vazia.
     * @param scanner Scanner para leitura
     * @param mensagem Mensagem para o usuário
     * @return String não vazia
     */
    public static String lerStringNaoVazia(Scanner scanner, String mensagem) {
        String texto;
        System.out.print(mensagem);
        
        do {
            texto = scanner.nextLine().trim();
            
            if (texto.isEmpty()) {
                System.out.print("Campo obrigatório! Digite novamente: ");
            }
        } while (texto.isEmpty());
        
        return texto;
    }
}
