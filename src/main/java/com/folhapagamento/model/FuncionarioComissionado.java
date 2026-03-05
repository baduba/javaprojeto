package com.folhapagamento.model;

/**
 * Representa um funcionário que recebe comissão sobre vendas.
 * Salário = Salário Base + (Vendas × Percentual / 100)
 */
public class FuncionarioComissionado extends Colaborador {
    
    private double vendas;
    private double percentualComissao;
    
    /**
     * Construtor do funcionário comissionado.
     * @param nome Nome do funcionário
     * @param matricula Matrícula do funcionário
     * @param vendas Valor total de vendas realizadas
     * @param percentualComissao Percentual de comissão (ex: 5 para 5%)
     * @throws IllegalArgumentException se valores forem negativos
     */
    public FuncionarioComissionado(String nome, int matricula, 
                                   double vendas, double percentualComissao) {
        super(nome, matricula);
        
        if (vendas < 0) {
            throw new IllegalArgumentException("Vendas não podem ser negativas");
        }
        if (percentualComissao < 0 || percentualComissao > 100) {
            throw new IllegalArgumentException("Percentual deve estar entre 0 e 100");
        }
        
        this.vendas = vendas;
        this.percentualComissao = percentualComissao;
    }
    
    public double getVendas() {
        return vendas;
    }
    
    public double getPercentualComissao() {
        return percentualComissao;
    }
    
    /**
     * Calcula a comissão sobre as vendas.
     * @return Valor da comissão
     */
    @Override
    public double calcularExtras() {
        return (vendas * percentualComissao) / 100;
    }
    
    @Override
    public double calcularSalarioFinal() {
        return SALARIO_BASE + calcularExtras();
    }
    
    @Override
    public String getTipo() {
        return "Funcionário Comissionado";
    }
}
