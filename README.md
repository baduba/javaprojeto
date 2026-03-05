# Sistema de Folha de Pagamento

## 📋 Descrição
Sistema completo para gerenciamento de folha de pagamento com suporte a diferentes tipos de colaboradores.

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas (Layered Architecture) com separação clara de responsabilidades:

```
src/
├── model/              # Camada de Domínio (Entities)
│   ├── Colaborador.java
│   ├── FuncionarioPadrao.java
│   ├── FuncionarioComissionado.java
│   └── FuncionarioProducao.java
│
├── repository/         # Camada de Dados (Data Access)
│   └── ColaboradorRepository.java
│
├── service/           # Camada de Negócio (Business Logic)
│   └── FolhaPagamentoService.java
│
├── view/              # Camada de Apresentação (UI)
│   └── MenuView.java
│
├── util/              # Utilitários
│   ├── InputValidator.java
│   ├── MoneyFormatter.java
│   └── DataSeeder.java
│
└── Main.java          # Ponto de Entrada
```

## 🎯 Princípios Aplicados

### SOLID
- **S** - Single Responsibility: Cada classe tem uma única responsabilidade
- **O** - Open/Closed: Classes abertas para extensão, fechadas para modificação
- **L** - Liskov Substitution: Subclasses podem substituir classes base
- **I** - Interface Segregation: Interfaces específicas (implícito)
- **D** - Dependency Inversion: Dependência de abstrações, não implementações

### Padrões de Projeto
- **Repository Pattern**: Isolamento da lógica de acesso a dados
- **Service Layer Pattern**: Separação da lógica de negócio
- **Singleton Pattern**: Instância única do repositório
- **Template Method**: Método abstrato calcularSalarioFinal()
- **MVC**: Separação Model-View-Controller

### Boas Práticas
- ✅ Validação de entrada centralizada
- ✅ Tratamento de exceções apropriado
- ✅ Código documentado (JavaDoc)
- ✅ Nomes descritivos e significativos
- ✅ Encapsulamento adequado
- ✅ Imutabilidade quando apropriado
- ✅ Formatação consistente

## 🚀 Como Executar

### Via VS Code (Recomendado)
1. Abra o arquivo `src/Main.java`
2. Clique em **"Run"** acima do método `main`
3. Ou pressione **F5** para executar

O VS Code compila automaticamente com as extensões Java instaladas.

### Via Docker
```bash
# Build da imagem
docker build -t folha-pagamento .

# Executar (modo interativo)
docker run -it folha-pagamento
```

### Via Linha de Comando
```bash
# Compilar (se necessário)
javac -d bin -sourcepath src src/Main.java src/**/*.java

# Executar
java -cp bin Main
```

## 💼 Tipos de Funcionários

1. **Funcionário Padrão**: Salário fixo (R$ 2.000,00)
2. **Funcionário Comissionado**: Salário base + comissão sobre vendas
3. **Funcionário de Produção**: Salário base + bônus por produtividade

## 📊 Funcionalidades

- ✅ Cadastro de diferentes tipos d
- ✅ Popular dados de exemplo automaticamentee funcionários
- ✅ Validação de dados de entrada
- ✅ Cálculo automático de salários
- ✅ Geração de relatório completo da folha
- ✅ Formatação monetária em Real (R$)
- ✅ Interface amigável e intuitiva

## 🔧 Requisitos

- VS Code com Extension Pack for Java (recomendado)
- Sistema operacional: Windows, Linux ou macOS

## 📖 Documentação Adicional

Para detalhes sobre a arquitetura do sistema, consulte [ARCHITECTURE.md](ARCHITECTURE.md)
- Sistema operacional: Windows, Linux ou macOS

## 📝 Licença

Projeto educacional - Livre para uso e modificação.
