# PagModulo

PagModulo e uma aplicacao Spring Boot projetada para facilitar a integracao de processamento de pagamentos com o PagBank. Ela foi arquitetada com Clean Architecture para garantir modularidade, escalabilidade e facilidade de manutencao.

## Visao Geral do Projeto

Esta API permite a criacao e o gerenciamento de checkouts de pagamento e processa notificacoes de webhook do PagBank sobre atualizacoes de status de checkout e confirmacoes de pagamento.

## Stack Tecnologica

O projeto utiliza as seguintes tecnologias:

- **Java 21**: Linguagem principal.
- **Spring Boot 4.0.3**: Framework para construcao da aplicacao.
- **Spring Data JPA**: Persistencia de dados.
- **MapStruct**: Mapeamento eficiente de beans Java.
- **H2 Database**: Banco em memoria para desenvolvimento e testes.
- **Docker Compose**: Orquestracao de containers e deploy simplificado.
- **Maven**: Gerenciamento de dependencias e build.

## Arquitetura

A aplicacao segue os principios de Clean Architecture, dividindo a base de codigo em camadas com responsabilidades bem definidas e dependencias apontando para o nucleo de negocio:

- **Application**: Contem os casos de uso de negocio e orquestra o fluxo de dados.
- **Domain**: Encapsula a logica de negocio principal, entidades e eventos de dominio, permanecendo independente de frameworks externos.
- **Infrastructure**: Implementa adaptadores e detalhes tecnicos, como persistencia em banco de dados (JPA) e integracoes com APIs externas (PagBank).
- **Presentation**: Recebe requisicoes HTTP via controllers REST e as converte em comandos de aplicacao.

## Como Comecar

### Pre-requisitos

- Java Development Kit (JDK) 21
- Maven
- Docker (opcional, para execucao com Docker Compose)

### Configuracao

A configuracao da aplicacao e gerenciada via `application.yaml` e arquivos especificos de perfil, como `application-dev.yaml`.

Principais propriedades de configuracao:
- **Porta do Servidor**: O padrao e `8080`.
- **Datasource**: Configurado para H2 em memoria por padrao no perfil `dev`.
- **Integracao PagBank**: Requer URL da API, Token e URL de pagamento (configurados em `application-dev.yaml` para desenvolvimento).

### Build e Execucao

1.  **Clone o repositorio:**
    ```bash
    git clone <repository-url>
    cd spring-api-pag
    ```

2.  **Compile o projeto:**
    ```bash
    mvn clean install
    ```

3.  **Execute a aplicacao:**
    ```bash
    mvn spring-boot:run
    ```
    Como alternativa, voce pode executar o arquivo JAR diretamente apos o build:
    ```bash
    java -jar target/pagModulo-0.0.1.jar
    ```

4.  **Execute com Docker Compose:**
    Certifique-se de que o Docker esteja em execucao e rode:
    ```bash
    docker-compose up -d
    ```

## Endpoints da API

### Gerenciamento de Checkout

- **Criar Checkout**
    - `POST /api/v1/checkouts`
    - Cria uma nova sessao de checkout.

- **Consultar Checkout** (Apenas no perfil Dev)
    - `GET /api/v1/checkouts/{uuid}`
    - Retorna os detalhes de um checkout especifico.

- **Ativar Checkout**
    - `POST /api/v1/checkouts/{uuid}/activate`
    - Ativa um checkout previamente inativo.

- **Inativar Checkout**
    - `POST /api/v1/checkouts/{uuid}/inactivate`
    - Desativa um checkout.

### Webhooks (PagBank)

- **Atualizar Status do Checkout**
    - `POST /api/v1/webhook/pagbank/update`
    - Recebe atualizacoes sobre mudancas de status do checkout.

- **Atualizacao de Pagamento**
    - `POST /api/v1/webhook/pagbank/payment-update`
    - Recebe notificacoes sobre atualizacoes de status de pagamento.
