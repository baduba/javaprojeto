package com.folhapagamento.model;

/**
 * Representa um funcionário que recebe bônus por produção.
 * Salário = Salário Base + (Quantidade de Peças × Valor por Peça)
 */
public class FuncionarioProducao extends Colaborador {
    
    private int quantidadePecas;
    private double valorPorPeca;
    
    /**
     * Construtor do funcionário de produção.
     * @param nome Nome do funcionário
     * @param matricula Matrícula do funcionário
     * @param quantidadePecas Quantidade de peças produzidas
     * @param valorPorPeca Valor pago por cada peça
     * @throws IllegalArgumentException se valores forem negativos
     */
    public FuncionarioProducao(String nome, int matricula, 
                              int quantidadePecas, double valorPorPeca) {
        super(nome, matricula);
        
        if (quantidadePecas < 0) {
            throw new IllegalArgumentException("Quantidade de peças não pode ser negativa");
        }
        if (valorPorPeca < 0) {
            throw new IllegalArgumentException("Valor por peça não pode ser negativo");
        }
        
        this.quantidadePecas = quantidadePecas;
        this.valorPorPeca = valorPorPeca;
    }
    
    public int getQuantidadePecas() {
        return quantidadePecas;
    }
    
    public double getValorPorPeca() {
        return valorPorPeca;
    }
    
    /**
     * Calcula o bônus de produtividade.
     * @return Valor do bônus
     */
    @Override
    public double calcularExtras() {
        return valorPorPeca * quantidadePecas;
    }
    
    @Override
    public double calcularSalarioFinal() {
        return SALARIO_BASE + calcularExtras();
    }
    
    @Override
    public String getTipo() {
        return "Funcionário de Produção";
    }
}
