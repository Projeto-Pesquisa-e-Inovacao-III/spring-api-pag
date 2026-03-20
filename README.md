# PagModulo

PagModulo é uma aplicação Spring Boot projetada para facilitar a integração de processamento de pagamentos com o PagBank. Ela foi arquitetada com Clean Architecture para garantir modularidade, escalabilidade e facilidade de manutenção.

## Visão Geral do Projeto

Esta API permite a criação e o gerenciamento de checkouts de pagamento e processa notificações de webhook do PagBank sobre atualizações de status de checkout e confirmações de pagamento.

## Stack Tecnológica

O projeto utiliza as seguintes tecnologias:

- **Java 21**: Linguagem principal.
- **Spring Boot 4.0.3**: Framework para construção da aplicação.
- **Spring Data JPA**: Persistência de dados.
- **MapStruct**: Mapeamento eficiente de beans Java.
- **H2 Database**: Banco em memória para desenvolvimento e testes.
- **Docker Compose**: Orquestração de containers e deploy simplificado.
- **Maven**: Gerenciamento de dependências e build.

## Arquitetura

A aplicação segue os princípios de Clean Architecture, dividindo a base de código em camadas com responsabilidades bem definidas e dependências apontando para o núcleo de negócio:

- **Application**: Contém os casos de uso de negócio e orquestra o fluxo de dados.
- **Domain**: Encapsula a lógica de negócio principal, entidades e eventos de domínio, permanecendo independente de frameworks externos.
- **Infrastructure**: Implementa adaptadores e detalhes técnicos, como persistência em banco de dados (JPA) e integrações com APIs externas (PagBank).
- **Presentation**: Recebe requisições HTTP via controllers REST e as converte em comandos de aplicação.

## Como Começar

### Pré-requisitos

- Java Development Kit (JDK) 21
- Maven
- Docker (opcional, para execução com Docker Compose)

### Configuração

A configuração da aplicação é gerenciada via `application.yaml` e arquivos específicos de perfil, como `application-dev.yaml`.

Principais propriedades de configuração:
- **Porta do Servidor**: O padrão é `8080`.
- **Datasource**: Configurado para H2 em memória por padrão no perfil `dev`.
- **Integração PagBank**: Requer URL da API, Token e URL de pagamento (configurados em `application-dev.yaml` para desenvolvimento).

### Build e Execução

1.  **Clone o repositório:**
    ```bash
    git clone <repository-url>
    cd spring-api-pag
    ```

2.  **Compile o projeto:**
    ```bash
    mvn clean install
    ```

3.  **Execute a aplicação:**
    ```bash
    mvn spring-boot:run
    ```
    Como alternativa, você pode executar o arquivo JAR diretamente após o build:
    ```bash
    java -jar target/pagModulo-0.0.1.jar
    ```

4.  **Execute com Docker Compose:**
    Certifique-se de que o Docker esteja em execução e rode:
    ```bash
    docker-compose up -d
    ```

## Endpoints da API

### Gerenciamento de Checkout

- **Criar Checkout**
    - `POST /api/v1/checkouts`
    - Cria uma sessão de checkout.

- **Consultar Checkout** (Apenas no perfil Dev)
    - `GET /api/v1/checkouts/{uuid}`
    - Retorna os detalhes de um checkout específico.

- **Ativar Checkout**
    - `POST /api/v1/checkouts/{uuid}/activate`
    - Ativa um checkout previamente inativo.

- **Inativar Checkout**
    - `POST /api/v1/checkouts/{uuid}/inactivate`
    - Desativa um checkout.

### Webhooks (PagBank)

- **Atualizar Status do Checkout**
    - `POST /api/v1/webhook/pagbank/update`
    - Recebe atualizações sobre mudanças de status do checkout.

- **Atualização de Pagamento**
    - `POST /api/v1/webhook/pagbank/payment-update`
    - Recebe notificações sobre atualizações de status de pagamento.

## Documentação OpenAPI

- **Swagger UI**: `http://localhost:8080/swagger-ui/index.html`
- **OpenAPI completo**: `http://localhost:8080/v3/api-docs`
- **Grupo Checkout**: `http://localhost:8080/v3/api-docs/checkout`
- **Grupo Order**: `http://localhost:8080/v3/api-docs/order`

As descrições dos endpoints foram centralizadas em interfaces na camada de **Presentation**.
