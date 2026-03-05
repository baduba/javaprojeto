package com.folhapagamento.model;

/**
 * Classe abstrata base que representa um colaborador da empresa.
 * Aplica o princípio Open/Closed - aberta para extensão, fechada para modificação.
 */
public abstract class Colaborador {
    
    protected String nome;
    protected int matricula;
    protected static final double SALARIO_BASE = 2000.00;
    
    /**
     * Construtor do colaborador.
     * @param nome Nome completo do colaborador
     * @param matricula Número de matrícula único
     * @throws IllegalArgumentException se os parâmetros forem inválidos
     */
    public Colaborador(String nome, int matricula) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        if (matricula < 0) {
            throw new IllegalArgumentException("Matrícula não pode ser negativa");
        }
        this.nome = nome;
        this.matricula = matricula;
    }
    
    // Getters
    public String getNome() {
        return nome;
    }
    
    public int getMatricula() {
        return matricula;
    }
    
    public double getSalarioBase() {
        return SALARIO_BASE;
    }
    
    /**
     * Calcula o salário final do colaborador.
     * Template Method Pattern - permite que subclasses definam o cálculo específico.
     * @return Valor total do salário
     */
    public abstract double calcularSalarioFinal();
    
    /**
     * Calcula o valor extra além do salário base.
     * @return Valor dos extras (comissão, bônus, etc.)
     */
    public abstract double calcularExtras();
    
    /**
     * Retorna o tipo de colaborador (para exibição).
     * @return String descritiva do tipo
     */
    public abstract String getTipo();
    
    @Override
    public String toString() {
        return String.format("Nome: %s%nMatrícula: %d%nTipo: %s",
                nome, matricula, getTipo());
    }
}
