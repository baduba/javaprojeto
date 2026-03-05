package repository;

import model.Colaborador;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Repositório para gerenciar a coleção de colaboradores.
 * Aplica o padrão Repository, isolando a lógica de acesso a dados.
 * Singleton Pattern para garantir uma única instância.
 */
public class ColaboradorRepository {
    
    private static ColaboradorRepository instance;
    private List<Colaborador> colaboradores;
    
    /**
     * Construtor privado para implementar Singleton.
     */
    private ColaboradorRepository() {
        this.colaboradores = new ArrayList<>();
    }
    
    /**
     * Obtém a instância única do repositório.
     * @return Instância do repositório
     */
    public static synchronized ColaboradorRepository getInstance() {
        if (instance == null) {
            instance = new ColaboradorRepository();
        }
        return instance;
    }
    
    /**
     * Adiciona um novo colaborador ao repositório.
     * @param colaborador Colaborador a ser adicionado
     * @return true se adicionado com sucesso
     * @throws IllegalArgumentException se colaborador for null
     */
    public boolean adicionar(Colaborador colaborador) {
        if (colaborador == null) {
            throw new IllegalArgumentException("Colaborador não pode ser null");
        }
        
        // Verifica se já existe matrícula duplicada
        if (buscarPorMatricula(colaborador.getMatricula()).isPresent()) {
            throw new IllegalArgumentException(
                "Já existe um colaborador com a matrícula: " + colaborador.getMatricula()
            );
        }
        
        return colaboradores.add(colaborador);
    }
    
    /**
     * Busca um colaborador por matrícula.
     * @param matricula Matrícula a ser buscada
     * @return Optional contendo o colaborador se encontrado
     */
    public Optional<Colaborador> buscarPorMatricula(int matricula) {
        return colaboradores.stream()
                .filter(c -> c.getMatricula() == matricula)
                .findFirst();
    }
    
    /**
     * Retorna todos os colaboradores cadastrados.
     * @return Lista imutável de colaboradores
     */
    public List<Colaborador> listarTodos() {
        return Collections.unmodifiableList(colaboradores);
    }
    
    /**
     * Remove um colaborador por matrícula.
     * @param matricula Matrícula do colaborador a remover
     * @return true se removido com sucesso
     */
    public boolean removerPorMatricula(int matricula) {
        return colaboradores.removeIf(c -> c.getMatricula() == matricula);
    }
    
    /**
     * Retorna a quantidade de colaboradores cadastrados.
     * @return Número de colaboradores
     */
    public int getTotalColaboradores() {
        return colaboradores.size();
    }
    
    /**
     * Verifica se o repositório está vazio.
     * @return true se não há colaboradores
     */
    public boolean isEmpty() {
        return colaboradores.isEmpty();
    }
    
    /**
     * Remove todos os colaboradores (usar com cuidado).
     */
    public void limpar() {
        colaboradores.clear();
    }
}
