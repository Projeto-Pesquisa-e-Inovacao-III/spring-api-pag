## Gateway Configuration

### EN
Currently loaded from `application.yaml` via `@Value`.
Recommended future improvement: persist this as a `GatewayConfig` entity to allow runtime changes without redeployment, especially if the client needs self-service configuration management.

### PT-BR
Atualmente carregado de `application.yaml` via `@Value`.
Melhoria futura recomendada: persistir isso como uma entidade `GatewayConfig` para permitir mudanças em runtime sem redeploy, especialmente se o cliente precisar por conta propria mudar a configuração.

## Database Idempotency

### EN
Webhooks are not currently receiving an idempotency key to prevent duplicate processing; only the event id is validated. For now, this works because Pix is being used as a single payment.
This becomes a problem for installments, because if the same webhook is sent multiple times, it can lead to duplicate processing and potential system issues.
Recommended future improvement: implement an idempotency-key mechanism for webhooks so duplicate requests are identified and handled correctly, ensuring system integrity.

### PT-BR
Atualmente os webhooks não recebem uma chave de idempotência para evitar processamento duplicado; apenas o id do evento e validado. Por enquanto, isso funciona porque o Pix está a ser usado como pagamento único.
Isso se torna um problema para parcelamento, pois se o mesmo webhook for enviado várias vezes, pode gerar processamento duplicado e possíveis problemas no sistema.
Melhoria futura recomendada: implementar um mecanismo de chave de idempotência para webhooks, de forma que requisições duplicadas sejam identificadas e tratadas corretamente, garantindo a integridade do sistema.

## Circuit Breaker

### EN
The current implementation does not include a circuit breaker mechanism to handle potential failures when communicating with the PagBank API. This can cause issues if the external service is down or experiencing high latency.
Recommended future improvement: implement the circuit breaker pattern using libraries such as Resilience4j to gracefully handle failures when communicating with the PagBank API, improving application resilience and stability.

### PT-BR
A implementação atual não inclui um mecanismo de circuit breaker para lidar com falhas potenciais na comunicação com a API do PagBank. Isso pode causar problemas se o serviço externo estiver indisponível ou com alta latência.
Melhoria futura recomendada: implementar o padrão circuit breaker usando bibliotecas como Resilience4j para tratar falhas de forma controlada na comunicação com a API do PagBank, melhorando a resiliência e a estabilidade da aplicação.
