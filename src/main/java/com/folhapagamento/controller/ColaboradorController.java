package com.folhapagamento.controller;

import com.folhapagamento.model.*;
import com.folhapagamento.service.FolhaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST Controller para operações da folha de pagamento.
 * Expõe endpoints API para cadastro e consulta de colaboradores.
 */
@RestController
@RequestMapping("/api/colaboradores")
@CrossOrigin(origins = "*")
public class ColaboradorController {
    
    @Autowired
    private FolhaPagamentoService service;
    
    /**
     * Cadastra um funcionário padrão.
     * POST /api/colaboradores/padrao
     */
    @PostMapping("/padrao")
    public ResponseEntity<Map<String, Object>> cadastrarPadrao(@RequestBody Map<String, Object> dados) {
        try {
            String nome = (String) dados.get("nome");
            int matricula = ((Number) dados.get("matricula")).intValue();
            
            service.cadastrarFuncionarioPadrao(nome, matricula);
            
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Funcionário padrão cadastrado com sucesso!"
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "success", false,
                "message", "Erro: " + e.getMessage()
            ));
        }
    }
    
    /**
     * Cadastra um funcionário comissionado.
     * POST /api/colaboradores/comissionado
     */
    @PostMapping("/comissionado")
    public ResponseEntity<Map<String, Object>> cadastrarComissionado(@RequestBody Map<String, Object> dados) {
        try {
            String nome = (String) dados.get("nome");
            int matricula = ((Number) dados.get("matricula")).intValue();
            double vendas = ((Number) dados.get("vendas")).doubleValue();
            double percentual = ((Number) dados.get("percentual")).doubleValue();
            
            service.cadastrarFuncionarioComissionado(nome, matricula, vendas, percentual);
            
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Funcionário comissionado cadastrado com sucesso!"
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "success", false,
                "message", "Erro: " + e.getMessage()
            ));
        }
    }
    
    /**
     * Cadastra um funcionário de produção.
     * POST /api/colaboradores/producao
     */
    @PostMapping("/producao")
    public ResponseEntity<Map<String, Object>> cadastrarProducao(@RequestBody Map<String, Object> dados) {
        try {
            String nome = (String) dados.get("nome");
            int matricula = ((Number) dados.get("matricula")).intValue();
            int quantidade = ((Number) dados.get("quantidade")).intValue();
            double valorPeca = ((Number) dados.get("valorPeca")).doubleValue();
            
            service.cadastrarFuncionarioProducao(nome, matricula, quantidade, valorPeca);
            
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Funcionário de produção cadastrado com sucesso!"
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "success", false,
                "message", "Erro: " + e.getMessage()
            ));
        }
    }
    
    /**
     * Obtém relatório da folha de pagamento.
     * GET /api/colaboradores/folha
     */
    @GetMapping("/folha")
    public ResponseEntity<Map<String, Object>> getFolhaPagamento() {
        String relatorio = service.gerarRelatorioFolhaPagamento();
        int total = service.getTotalColaboradores();
        
        return ResponseEntity.ok(Map.of(
            "success", true,
            "total", total,
            "relatorio", relatorio
        ));
    }
    
    /**
     * Popula dados de exemplo.
     * POST /api/colaboradores/popular
     */
    @PostMapping("/popular")
    public ResponseEntity<Map<String, Object>> popularDados() {
        try {
            com.folhapagamento.util.DataSeeder.popularDadosRequisitos(service);
            
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Dados de exemplo populados com sucesso!"
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "success", false,
                "message", "Erro: " + e.getMessage()
            ));
        }
    }
}
