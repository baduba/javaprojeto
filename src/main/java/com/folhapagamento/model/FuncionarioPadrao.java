package com.folhapagamento.model;

/**
 * Representa um funcionário com salário fixo sem bônus adicionais.
 */
public class FuncionarioPadrao extends Colaborador {
    
    public FuncionarioPadrao(String nome, int matricula) {
        super(nome, matricula);
    }
    
    @Override
    public double calcularSalarioFinal() {
        return SALARIO_BASE;
    }
    
    @Override
    public double calcularExtras() {
        return 0.0;
    }
    
    @Override
    public String getTipo() {
        return "Funcionário Padrão";
    }
}
