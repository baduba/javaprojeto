# Documentação Técnica - Arquitetura do Sistema

## 📐 Diagrama de Arquitetura em Camadas

```
┌─────────────────────────────────────────────────┐
│           CAMADA DE APRESENTAÇÃO                │
│              (Presentation Layer)               │
│                                                 │
│  ┌───────────────────────────────────────────┐  │
│  │          MenuView.java                    │  │
│  │  - Interface com usuário                  │  │
│  │  - Captura de entrada                     │  │
│  │  - Exibição de menus e relatórios         │  │
│  └───────────────────────────────────────────┘  │
└─────────────────────────────────────────────────┘
                       ↓
┌─────────────────────────────────────────────────┐
│           CAMADA DE SERVIÇO                     │
│             (Service Layer)                     │
│                                                 │
│  ┌───────────────────────────────────────────┐  │
│  │    FolhaPagamentoService.java             │  │
│  │  - Lógica de negócio                      │  │
│  │  - Orquestração de operações              │  │
│  │  - Geração de relatórios                  │  │
│  └───────────────────────────────────────────┘  │
└─────────────────────────────────────────────────┘
                       ↓
┌─────────────────────────────────────────────────┐
│        CAMADA DE ACESSO A DADOS                 │
│           (Data Access Layer)                   │
│                                                 │
│  ┌───────────────────────────────────────────┐  │
│  │   ColaboradorRepository.java              │  │
│  │  - Padrão Repository                      │  │
│  │  - CRUD de colaboradores                  │  │
│  │  - Singleton Pattern                      │  │
│  └───────────────────────────────────────────┘  │
└─────────────────────────────────────────────────┘
                       ↓
┌─────────────────────────────────────────────────┐
│           CAMADA DE DOMÍNIO                     │
│             (Domain Layer)                      │
│                                                 │
│  ┌─────────────────────────────────────────┐   │
│  │        Colaborador (abstract)           │   │
│  └────────────────┬────────────────────────┘   │
│                   │                             │
│       ┌───────────┼──────────────┐              │
│       ↓           ↓              ↓              │
│  ┌─────────┐ ┌─────────┐  ┌──────────────┐     │
│  │Funcioná-│ │Funcioná-│  │ Funcionário  │     │
│  │rioPadrão│ │rioComis-│  │  Produção    │     │
│  │         │ │sionado  │  │              │     │
│  └─────────┘ └─────────┘  └──────────────┘     │
└─────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────┐
│              UTILITÁRIOS                        │
│                                                 │
│  ┌─────────────────┐  ┌──────────────────┐     │
│  │ InputValidator  │  │ MoneyFormatter   │     │
│  │ - Validações    │  │ - Formatação R$  │     │
│  └─────────────────┘  └──────────────────┘     │
└─────────────────────────────────────────────────┘
```

## 🎯 Responsabilidades de Cada Camada

### 1. **Camada de Apresentação (View)**
**Arquivo:** `MenuView.java`

**Responsabilidades:**
- Exibir menus e interfaces para o usuário
- Capturar entrada de dados
- Formatar saída de informações
- Chamar métodos da camada de serviço

**Princípios:**
- Não contém lógica de negócio
- Não acessa diretamente o repositório
- Delega processamento para o service

---

### 2. **Camada de Serviço (Service)**
**Arquivo:** `FolhaPagamentoService.java`

**Responsabilidades:**
- Implementar regras de negócio
- Coordenar operações entre camadas
- Validar dados de negócio
- Gerar relatórios e cálculos

**Princípios:**
- Single Responsibility: apenas lógica de negócio
- Independente da UI
- Utiliza o repositório para persistência

---

### 3. **Camada de Acesso a Dados (Repository)**
**Arquivo:** `ColaboradorRepository.java`

**Responsabilidades:**
- Gerenciar coleção de dados
- Operações CRUD (Create, Read, Update, Delete)
- Garantir integridade dos dados
- Prover métodos de busca e listagem

**Padrões Aplicados:**
- **Repository Pattern**: Abstrai acesso a dados
- **Singleton Pattern**: Única instância do repositório

---

### 4. **Camada de Domínio (Model/Entities)**
**Arquivos:** `Colaborador.java`, `FuncionarioPadrao.java`, `FuncionarioComissionado.java`, `FuncionarioProducao.java`

**Responsabilidades:**
- Representar entidades do negócio
- Encapsular dados e comportamentos
- Implementar cálculos específicos de cada tipo

**Padrões Aplicados:**
- **Template Method**: `calcularSalarioFinal()` é abstrato
- **Herança**: Reutilização de código da classe base

---

### 5. **Utilitários (Utils)**
**Arquivos:** `InputValidator.java`, `MoneyFormatter.java`

**Responsabilidades:**
- Validação de entrada
- Formatação de dados
- Funções auxiliares reutilizáveis

**Princípios:**
- DRY (Don't Repeat Yourself)
- Cohesão funcional
- Métodos estáticos para utilização global

---

## 🔄 Fluxo de Dados

### Exemplo: Cadastro de Funcionário Comissionado

```
1. Usuário escolhe opção no menu
   ↓
2. MenuView captura dados (nome, matrícula, vendas, %)
   ↓
3. MenuView.cadastrarFuncionarioComissionado() chama
   ↓
4. FolhaPagamentoService.cadastrarFuncionarioComissionado()
   ↓
5. Service cria objeto FuncionarioComissionado
   ↓
6. Service chama Repository.adicionar()
   ↓
7. Repository valida e armazena em ArrayList
   ↓
8. Retorna sucesso para o Service
   ↓
9. Service retorna para a View
   ↓
10. View exibe mensagem de sucesso ao usuário
```

---

## 🏛️ Princípios SOLID Aplicados

### Single Responsibility Principle (SRP)
- ✅ **MenuView**: Apenas UI
- ✅ **FolhaPagamentoService**: Apenas lógica de negócio
- ✅ **ColaboradorRepository**: Apenas acesso a dados
- ✅ **InputValidator**: Apenas validações
- ✅ **MoneyFormatter**: Apenas formatação

### Open/Closed Principle (OCP)
- ✅ Classe `Colaborador` aberta para extensão (novas subclasses)
- ✅ Fechada para modificação (código base não muda)

### Liskov Substitution Principle (LSP)
- ✅ Qualquer subclasse de `Colaborador` pode substituir a classe base
- ✅ Polimorfismo funciona corretamente

### Interface Segregation Principle (ISP)
- ✅ Interfaces implícitas específicas para cada necessidade
- ✅ Classes não forçadas a implementar métodos desnecessários

### Dependency Inversion Principle (DIP)
- ✅ Camadas superiores dependem de abstrações
- ✅ Repository retorna interface List, não ArrayList específico

---

## 📊 Padrões de Projeto Utilizados

| Padrão | Onde | Benefício |
|--------|------|-----------|
| **Repository** | ColaboradorRepository | Abstração de acesso a dados |
| **Singleton** | ColaboradorRepository | Instância única |
| **Service Layer** | FolhaPagamentoService | Separação de lógica de negócio |
| **Template Method** | Colaborador.calcularSalarioFinal() | Comportamento polimórfico |
| **MVC** | Estrutura geral | Separação de responsabilidades |

---

## 🧪 Testabilidade

A arquitetura facilita testes unitários:

```java
// Exemplo de teste do Service (pseudocódigo)
@Test
public void testCadastroFuncionarioPadrao() {
    FolhaPagamentoService service = new FolhaPagamentoService();
    boolean resultado = service.cadastrarFuncionarioPadrao("João", 123);
    assertTrue(resultado);
    assertEquals(1, service.getTotalColaboradores());
}
```

---

## 🔐 Validações Implementadas

1. **Camada de Apresentação (View)**
   - Validação de tipo de dados (InputValidator)
   - Valores não negativos
   - Strings não vazias

2. **Camada de Domínio (Model)**
   - Validação de regras de negócio
   - Lançamento de exceções para dados inválidos

3. **Camada de Repositório**
   - Prevenção de matrículas duplicadas
   - Validação de null

---

## 📈 Extensibilidade

Para adicionar novo tipo de funcionário:

1. Criar nova classe que estende `Colaborador`
2. Implementar métodos abstratos
3. Adicionar método no `Service`
4. Adicionar opção no `MenuView`

**Sem necessidade de modificar código existente! (OCP)**

---

## 🎓 Conceitos de Engenharia de Software

✅ **Coesão Alta**: Cada classe tem responsabilidade bem definida  
✅ **Acoplamento Baixo**: Camadas independentes  
✅ **Encapsulamento**: Dados privados com getters apropriados  
✅ **Abstração**: Uso de classes abstratas e polimorfismo  
✅ **Reutilização**: Código compartilhado via herança e utilitários  
✅ **Manutenibilidade**: Código organizado e documentado  
✅ **Escalabilidade**: Fácil adicionar novos recursos  

---

**Versão:** 2.0  
**Data:** 2026  
**Arquitetura:** Layered Architecture + MVC + Repository Pattern
