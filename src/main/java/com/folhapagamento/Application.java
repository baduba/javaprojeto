package com.folhapagamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação Spring Boot.
 * Point of Entry - ponto de entrada do sistema web.
 * 
 * @author Sistema de Folha de Pagamento
 * @version 2.0 - Web Edition
 */
@SpringBootApplication
public class Application {
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
