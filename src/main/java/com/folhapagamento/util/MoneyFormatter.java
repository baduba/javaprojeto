package com.folhapagamento.util;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Classe utilitária para formatação de valores monetários.
 * Facilita a exibição padronizada de valores em reais.
 */
public class MoneyFormatter {
    
    private static final Locale LOCALE_PT_BR = new Locale("pt", "BR");
    private static final NumberFormat FORMATO_MOEDA = 
        NumberFormat.getCurrencyInstance(LOCALE_PT_BR);
    
    /**
     * Formata um valor double para o formato de moeda brasileira.
     * @param valor Valor a ser formatado
     * @return String formatada (ex: R$ 1.234,56)
     */
    public static String formatar(double valor) {
        return FORMATO_MOEDA.format(valor);
    }
    
    /**
     * Formata um valor double com descrição.
     * @param descricao Descrição do valor
     * @param valor Valor a ser formatado
     * @return String formatada (ex: "Salário: R$ 2.000,00")
     */
    public static String formatarComDescricao(String descricao, double valor) {
        return descricao + ": " + formatar(valor);
    }
}
