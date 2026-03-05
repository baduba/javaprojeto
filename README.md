# Sistema de Folha de Pagamento - Web Edition

## 📋 Descrição
Sistema web completo para gerenciamento de folha de pagamento com suporte a diferentes tipos de colaboradores. Desenvolvido com Spring Boot e interface web responsiva.

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas (Layered Architecture) com Spring Boot:

```
src/main/java/com/folhapagamento/
├── controller/         # Camada de Apresentação (REST API + Web)
│   ├── ColaboradorController.java
│   └── HomeController.java
│
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
├── util/              # Utilitários
│   ├── InputValidator.java
│   ├── MoneyFormatter.java
│   └── DataSeeder.java
│
└── Application.java   # Ponto de Entrada Spring Boot

src/main/resources/
└── templates/
    └── index.html     # Interface Web
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
- **MVC/REST**: Arquitetura web com Spring Boot
- **Dependency Injection**: Gerenciamento de dependências pelo Spring

### Boas Práticas
- ✅ Validação de entrada centralizada
- ✅ Tratamento de exceções apropriado
- ✅ Código documentado (JavaDoc)
- ✅ Nomes descritivos e significativos
- ✅ Encapsulamento adequado
- ✅ Imutabilidade quando apropriado
- ✅ Formatação consistente

## 🚀 Como Executar

### Via Spring Boot (Desenvolvimento Local)
```bash
# Com Maven instalado
mvn spring-boot:run
```

Acesse: http://localhost:8080

### Via Docker (Produção)
```bash
# Build da imagem
docker build -t folha-pagamento .

# Executar
docker run -p 8080:8080 folha-pagamento
```

Acesse: http://localhost:8080

### Deploy Online
O projeto está configurado para deploy em:
- **Sliplane**: Push para GitHub dispara deploy automático
- **Heroku**: `git push heroku master`
- **Railway**: Conecte o repositório GitHub

**URL de demonstração**: https://javaprojeto.sliplane.app/

## 💼 Tipos de Funcionários

1. **Funcionário Padrão**: Salário fixo (R$ 2.000,00)
2. **Funcionário Comissionado**: Salário base + comissão sobre vendas
3. **Funcionário de Produção**: Salário base + bônus por produtividade

## 📊 Funcionalidades

- ✅ Interface web responsiva e moderna
- ✅ Cadastro de diferentes tipos de funcionários
- ✅ Validação de dados de entrada
- ✅ Cálculo automático de salários
- ✅ Geração de relatório completo da folha
- ✅ Formatação monetária em Real (R$)
- ✅ Popular dados de exemplo automaticamente
- ✅ API REST para integração
- ✅ Deploy em nuvem (Docker-ready)

## 🔧 Requisitos

- Java JDK 21
- Maven 3.6+
- Docker (opcional, para containerização)
- Navegador web moderno

## 🌐 API Endpoints

- `POST /api/colaboradores/padrao` - Cadastrar funcionário padrão
- `POST /api/colaboradores/comissionado` - Cadastrar funcionário comissionado
- `POST /api/colaboradores/producao` - Cadastrar funcionário de produção
- `GET /api/colaboradores/folha` - Obter folha de pagamento
- `POST /api/colaboradores/popular` - Popular dados de exemplo

## 📖 Documentação Adicional

- **Versão Console/CLI**: Disponível em `projeto_original_sem_webapp.zip`
- **Arquitetura Detalhada**: Consulte [ARCHITECTURE.md](ARCHITECTURE.md)

## 📝 Licença

Projeto educacional - Livre para uso e modificação.
