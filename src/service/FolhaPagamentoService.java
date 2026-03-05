package service;

import model.Colaborador;
import model.FuncionarioPadrao;
import model.FuncionarioComissionado;
import model.FuncionarioProducao;
import repository.ColaboradorRepository;
import util.MoneyFormatter;

import java.util.List;

/**
 * Serviço responsável pela lógica de negócio da folha de pagamento.
 * Aplica o padrão Service Layer, separando lógica de negócio da apresentação.
 */
public class FolhaPagamentoService {
    
    private ColaboradorRepository repository;
    
    public FolhaPagamentoService() {
        this.repository = ColaboradorRepository.getInstance();
    }
    
    /**
     * Cadastra um novo funcionário padrão.
     * @param nome Nome do funcionário
     * @param matricula Matrícula única
     * @return true se cadastrado com sucesso
     * @throws IllegalArgumentException se dados inválidos
     */
    public boolean cadastrarFuncionarioPadrao(String nome, int matricula) {
        FuncionarioPadrao funcionario = new FuncionarioPadrao(nome, matricula);
        return repository.adicionar(funcionario);
    }
    
    /**
     * Cadastra um novo funcionário comissionado.
     * @param nome Nome do funcionário
     * @param matricula Matrícula única
     * @param vendas Valor de vendas
     * @param percentual Percentual de comissão
     * @return true se cadastrado com sucesso
     * @throws IllegalArgumentException se dados inválidos
     */
    public boolean cadastrarFuncionarioComissionado(String nome, int matricula,
                                                    double vendas, double percentual) {
        FuncionarioComissionado funcionario = 
            new FuncionarioComissionado(nome, matricula, vendas, percentual);
        return repository.adicionar(funcionario);
    }
    
    /**
     * Cadastra um novo funcionário de produção.
     * @param nome Nome do funcionário
     * @param matricula Matrícula única
     * @param quantidade Quantidade de peças produzidas
     * @param valorPeca Valor por peça
     * @return true se cadastrado com sucesso
     * @throws IllegalArgumentException se dados inválidos
     */
    public boolean cadastrarFuncionarioProducao(String nome, int matricula,
                                                int quantidade, double valorPeca) {
        FuncionarioProducao funcionario = 
            new FuncionarioProducao(nome, matricula, quantidade, valorPeca);
        return repository.adicionar(funcionario);
    }
    
    /**
     * Gera o relatório da folha de pagamento.
     * @return String formatada com a folha completa
     */
    public String gerarRelatorioFolhaPagamento() {
        StringBuilder relatorio = new StringBuilder();
        
        relatorio.append("\n╔════════════════════════════════════════════════╗\n");
        relatorio.append("║        RELATÓRIO DE FOLHA DE PAGAMENTO         ║\n");
        relatorio.append("╚════════════════════════════════════════════════╝\n\n");
        
        List<Colaborador> colaboradores = repository.listarTodos();
        
        if (colaboradores.isEmpty()) {
            relatorio.append("[AVISO] Nenhum funcionário cadastrado no sistema.\n");
            return relatorio.toString();
        }
        
        relatorio.append(String.format("Total de colaboradores: %d\n\n", 
                                       colaboradores.size()));
        
        double totalFolha = 0;
        int contador = 1;
        
        for (Colaborador colaborador : colaboradores) {
            relatorio.append(String.format("─── Colaborador #%d ───\n", contador++));
            relatorio.append(formatarDadosColaborador(colaborador));
            relatorio.append("\n");
            totalFolha += colaborador.calcularSalarioFinal();
        }
        
        relatorio.append("═══════════════════════════════════════════════════\n");
        relatorio.append(String.format("TOTAL DA FOLHA: %s\n", 
                                      MoneyFormatter.formatar(totalFolha)));
        relatorio.append("═══════════════════════════════════════════════════\n");
        
        return relatorio.toString();
    }
    
    /**
     * Formata os dados de um colaborador para exibição.
     * @param colaborador Colaborador a ser formatado
     * @return String formatada com os dados
     */
    private String formatarDadosColaborador(Colaborador colaborador) {
        StringBuilder dados = new StringBuilder();
        
        dados.append(String.format("Nome: %s\n", colaborador.getNome()));
        dados.append(String.format("Matrícula: %d\n", colaborador.getMatricula()));
        dados.append(String.format("Tipo: %s\n", colaborador.getTipo()));
        dados.append(String.format("Salário Base: %s\n", 
                                  MoneyFormatter.formatar(colaborador.getSalarioBase())));
        
        // Detalhes específicos por tipo
        if (colaborador instanceof FuncionarioComissionado) {
            FuncionarioComissionado fc = (FuncionarioComissionado) colaborador;
            dados.append(String.format("Vendas: %s\n", 
                                      MoneyFormatter.formatar(fc.getVendas())));
            dados.append(String.format("Comissão (%,.1f%%): %s\n",
                                      fc.getPercentualComissao(),
                                      MoneyFormatter.formatar(fc.calcularExtras())));
        } else if (colaborador instanceof FuncionarioProducao) {
            FuncionarioProducao fp = (FuncionarioProducao) colaborador;
            dados.append(String.format("Peças Produzidas: %d\n", fp.getQuantidadePecas()));
            dados.append(String.format("Valor por Peça: %s\n", 
                                      MoneyFormatter.formatar(fp.getValorPorPeca())));
            dados.append(String.format("Bônus Produtividade: %s\n", 
                                      MoneyFormatter.formatar(fp.calcularExtras())));
        } else {
            dados.append("Extras: R$ 0,00\n");
        }
        
        dados.append(String.format("==> SALÁRIO FINAL: %s\n", 
                                  MoneyFormatter.formatar(colaborador.calcularSalarioFinal())));
        
        return dados.toString();
    }
    
    /**
     * Retorna a quantidade total de colaboradores.
     * @return Número de colaboradores
     */
    public int getTotalColaboradores() {
        return repository.getTotalColaboradores();
    }
    
    /**
     * Verifica se há colaboradores cadastrados.
     * @return true se há colaboradores
     */
    public boolean hasColaboradores() {
        return !repository.isEmpty();
    }
}
