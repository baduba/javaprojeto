import view.MenuView;

/**
 * Classe principal da aplicação.
 * Point of Entry - ponto de entrada do sistema.
 * 
 * @author Sistema de Folha de Pagamento
 * @version 2.0
 */
public class Main {
    
    /**
     * Método principal que inicia a aplicação.
     * @param args Argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        MenuView menu = new MenuView();
        menu.iniciar();
    }
}
