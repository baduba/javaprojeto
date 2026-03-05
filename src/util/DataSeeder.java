package util;

import service.FolhaPagamentoService;

/**
 * Classe utilitária para popular o sistema com dados de teste.
 * Facilita demonstração e testes do sistema.
 */
public class DataSeeder {
    
    /**
     * Popula o sistema com dados de exemplo conforme requisitos.
     * Cria 3 funcionários: Padrão, Comissionado e Produção.
     * 
     * @param service Serviço de folha de pagamento a ser populado
     * @return Número de funcionários cadastrados
     */
    public static int popularDadosExemplo(FolhaPagamentoService service) {
        int count = 0;
        
        try {
            // 1. Funcionário Padrão - Flavio
            service.cadastrarFuncionarioPadrao("Flavio", 123);
            count++;
            
            // 2. Funcionário Comissionado - Maria
            // Vendas: 8000, Comissão: 5%
            service.cadastrarFuncionarioComissionado("Maria", 234, 8000.0, 5.0);
            count++;
            
            // 3. Funcionário Produção - Paulo
            // Quantidade: 150 peças, Valor: R$ 0,20 por peça
            service.cadastrarFuncionarioProducao("Paulo", 456, 150, 0.20);
            count++;
            
            System.out.println("\n[INFO] Sistema populado com " + count + " funcionários de exemplo.");
            System.out.println("       - Flavio (Padrão)");
            System.out.println("       - Maria (Comissionado)");
            System.out.println("       - Paulo (Produção)");
            
        } catch (IllegalArgumentException e) {
            System.out.println("\n[AVISO] Alguns dados já foram cadastrados anteriormente.");
            System.out.println("        Use a opção 4 para ver os funcionários existentes.");
        }
        
        return count;
    }
    
    /**
     * Popula o sistema com dados variados para testes mais completos.
     * 
     * @param service Serviço de folha de pagamento a ser populado
     * @return Número de funcionários cadastrados
     */
    public static int popularDadosCompletos(FolhaPagamentoService service) {
        int count = 0;
        
        try {
            // Funcionários Padrão
            service.cadastrarFuncionarioPadrao("Flavio", 123);
            service.cadastrarFuncionarioPadrao("Ana Silva", 124);
            service.cadastrarFuncionarioPadrao("Carlos Santos", 125);
            count += 3;
            
            // Funcionários Comissionados
            service.cadastrarFuncionarioComissionado("Maria", 234, 8000.0, 5.0);
            service.cadastrarFuncionarioComissionado("João Oliveira", 235, 15000.0, 7.5);
            service.cadastrarFuncionarioComissionado("Lucia Costa", 236, 25000.0, 10.0);
            count += 3;
            
            // Funcionários de Produção
            service.cadastrarFuncionarioProducao("Paulo", 456, 150, 0.20);
            service.cadastrarFuncionarioProducao("Marcos Pereira", 457, 200, 0.25);
            service.cadastrarFuncionarioProducao("Fernanda Lima", 458, 180, 0.30);
            count += 3;
            
            System.out.println("\n[INFO] Sistema populado com " + count + " funcionários.");
            
        } catch (IllegalArgumentException e) {
            System.out.println("\n[AVISO] Alguns dados já foram cadastrados anteriormente.");
        }
        
        return count;
    }
    
    /**
     * Popula dados seguindo exatamente os exemplos dos requisitos.
     * Valores conforme especificação original.
     * 
     * @param service Serviço de folha de pagamento
     */
    public static void popularDadosRequisitos(FolhaPagamentoService service) {
        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("║     POPULANDO DADOS DE EXEMPLO (REQUISITOS)       ║");
        System.out.println("╚════════════════════════════════════════════════════╝\n");
        
        try {
            // Exemplo 1: Funcionário Padrão
            System.out.println("Cadastrando Funcionário Padrão...");
            System.out.println("  Nome: Flavio");
            System.out.println("  Matrícula: 123");
            service.cadastrarFuncionarioPadrao("Flavio", 123);
            System.out.println("  [OK] Cadastrado\n");
            
            // Exemplo 2: Funcionário Comissionado
            System.out.println("Cadastrando Funcionário Comissionado...");
            System.out.println("  Nome: Maria");
            System.out.println("  Matrícula: 234");
            System.out.println("  Valor das vendas: 8000");
            System.out.println("  Comissão percentual: 5");
            service.cadastrarFuncionarioComissionado("Maria", 234, 8000.0, 5.0);
            System.out.println("  [OK] Cadastrado\n");
            
            // Exemplo 3: Funcionário Produção
            System.out.println("Cadastrando Funcionário de Produção...");
            System.out.println("  Nome: Paulo");
            System.out.println("  Matrícula: 456");
            System.out.println("  Quantidade de peças: 150");
            System.out.println("  Valor da peça: 0.20");
            service.cadastrarFuncionarioProducao("Paulo", 456, 150, 0.20);
            System.out.println("  [OK] Cadastrado\n");
            
            System.out.println("════════════════════════════════════════════════════");
            System.out.println("    3 funcionários cadastrados com sucesso!");
            System.out.println("    Use a opção 4 para visualizar a folha.");
            System.out.println("════════════════════════════════════════════════════\n");
            
        } catch (IllegalArgumentException e) {
            System.out.println("\n[ERRO] " + e.getMessage());
            System.out.println("       Limpe o sistema e tente novamente.");
        }
    }
}
