package view;

import service.FolhaPagamentoService;
import util.InputValidator;
import util.DataSeeder;

import java.util.Scanner;

/**
 * Classe responsável pela interface com o usuário (View/Presentation Layer).
 * Aplica o padrão MVC, separando apresentação da lógica de negócio.
 */
public class MenuView {
    
    private Scanner scanner;
    private FolhaPagamentoService service;
    
    public MenuView() {
        this.scanner = new Scanner(System.in);
        this.service = new FolhaPagamentoService();
    }
    
    /**
     * Inicia a aplicação e exibe o menu principal.
     */
    public void iniciar() {
        exibirBoasVindas();
        
        int opcao;
        do {
            opcao = exibirMenuPrincipal();
            processarOpcao(opcao);
        } while (opcao != 0);
        
        exibirDespedida();
        scanner.close();
    }
    
    /**
     * Exibe mensagem de boas-vindas.
     */
    private void exibirBoasVindas() {
        limparTela();
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║     SISTEMA DE FOLHA DE PAGAMENTO - v2.0           ║");
        System.out.println("║     Gestão Completa de Colaboradores               ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.println();
    }
    
    /**
     * Exibe o menu principal e retorna a opção escolhida.
     * @return Opção selecionada pelo usuário
     */
    private int exibirMenuPrincipal() {
        System.out.println("\n┌────────────────────────────────────────────────┐");
        System.out.println("│           MENU PRINCIPAL                       │");
        System.out.println("└────────────────────────────────────────────────┘");
        System.out.println("  1 - Cadastrar Funcionário Padrão");
        System.out.println("  2 - Cadastrar Funcionário Comissionado");
        System.out.println("  3 - Cadastrar Funcionário de Produção");
        System.out.println("  4 - Gerar Folha de Pagamento");
        System.out.println("  5 - Popular Dados de Exemplo (Requisitos)");
        System.out.println("  0 - Sair do Sistema");
        System.out.println("────────────────────────────────────────────────");
        
        return InputValidator.lerInteiroPositivo(scanner, ">> Escolha uma opção: ");
    }
    
    /**
     * Processa a opção escolhida pelo usuário.
     * @param opcao Opção do menu
     */
    private void processarOpcao(int opcao) {
        scanner.nextLine(); // Limpar buffer
        
        try {
            switch (opcao) {
                case 1:
                    cadastrarFuncionarioPadrao();
                    break;
                case 2:
                    cadastrarFuncionarioComissionado();
                    break;
                case 3:
                    cadastrarFuncionarioProducao();
                    break;
                case 4:
                    gerarFolhaPagamento();
                    break;
                case 5:
                    popularDadosExemplo();
                    break;
                case 0:
                    // Sair - processado no loop principal
                    break;
                default:
                    exibirMensagemErro("Opção inválida! Escolha entre 0 e 5.");
            }
        } catch (IllegalArgumentException e) {
            exibirMensagemErro("Erro: " + e.getMessage());
        } catch (Exception e) {
            exibirMensagemErro("Erro inesperado: " + e.getMessage());
        }
        
        if (opcao != 0) {
            aguardarContinuacao();
        }
    }
    
    /**
     * Realiza o cadastro de um funcionário padrão.
     */
    private void cadastrarFuncionarioPadrao() {
        exibirCabecalho("CADASTRO DE FUNCIONÁRIO PADRÃO");
        
        String nome = InputValidator.lerStringNaoVazia(scanner, "Nome completo: ");
        int matricula = InputValidator.lerInteiroPositivo(scanner, "Matrícula: ");
        scanner.nextLine(); // Limpar buffer
        
        service.cadastrarFuncionarioPadrao(nome, matricula);
        exibirMensagemSucesso("Funcionário cadastrado com sucesso!");
    }
    
    /**
     * Realiza o cadastro de um funcionário comissionado.
     */
    private void cadastrarFuncionarioComissionado() {
        exibirCabecalho("CADASTRO DE FUNCIONÁRIO COMISSIONADO");
        
        String nome = InputValidator.lerStringNaoVazia(scanner, "Nome completo: ");
        int matricula = InputValidator.lerInteiroPositivo(scanner, "Matrícula: ");
        double vendas = InputValidator.lerDoublePositivo(scanner, "Valor das vendas (R$): ");
        double percentual = InputValidator.lerPercentual(scanner, "Percentual de comissão (%): ");
        scanner.nextLine(); // Limpar buffer
        
        service.cadastrarFuncionarioComissionado(nome, matricula, vendas, percentual);
        exibirMensagemSucesso("Funcionário comissionado cadastrado com sucesso!");
    }
    
    /**
     * Realiza o cadastro de um funcionário de produção.
     */
    private void cadastrarFuncionarioProducao() {
        exibirCabecalho("CADASTRO DE FUNCIONÁRIO DE PRODUÇÃO");
        
        String nome = InputValidator.lerStringNaoVazia(scanner, "Nome completo: ");
        int matricula = InputValidator.lerInteiroPositivo(scanner, "Matrícula: ");
        int quantidade = InputValidator.lerInteiroPositivo(scanner, "Quantidade de peças: ");
        double valorPeca = InputValidator.lerDoublePositivo(scanner, "Valor por peça (R$): ");
        scanner.nextLine(); // Limpar buffer
        
        service.cadastrarFuncionarioProducao(nome, matricula, quantidade, valorPeca);
        exibirMensagemSucesso("Funcionário de produção cadastrado com sucesso!");
    }
    
    /**
     * Gera e exibe a folha de pagamento.
     */
    private void gerarFolhaPagamento() {
        String relatorio = service.gerarRelatorioFolhaPagamento();
        System.out.println(relatorio);
    }
    
    /**
     * Popula o sistema com dados de exemplo dos requisitos.
     */
    private void popularDadosExemplo() {
        DataSeeder.popularDadosRequisitos(service);
    }
    
    /**
     * Exibe um cabeçalho formatado.
     * @param titulo Título do cabeçalho
     */
    private void exibirCabecalho(String titulo) {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println(String.format("║  %-44s  ║", titulo));
        System.out.println("╚════════════════════════════════════════════════╝");
    }
    
    /**
     * Exibe uma mensagem de sucesso.
     * @param mensagem Mensagem a exibir
     */
    private void exibirMensagemSucesso(String mensagem) {
        System.out.println("\n[OK] " + mensagem);
    }
    
    /**
     * Exibe uma mensagem de erro.
     * @param mensagem Mensagem a exibir
     */
    private void exibirMensagemErro(String mensagem) {
        System.out.println("\n[ERRO] " + mensagem);
    }
    
    /**
     * Aguarda que o usuário pressione Enter para continuar.
     */
    private void aguardarContinuacao() {
        System.out.print("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }
    
    /**
     * Exibe mensagem de despedida.
     */
    private void exibirDespedida() {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║  Obrigado por usar o sistema!                  ║");
        System.out.println("║  Até logo!                                     ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
    }
    
    /**
     * Limpa a tela (simulado com quebras de linha).
     */
    private void limparTela() {
        for (int i = 0; i < 2; i++) {
            System.out.println();
        }
    }
}
