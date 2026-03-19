## Gateway Configuration

Currently loaded from application.yaml via @Value.
Recommended future improvement: persist as GatewayConfig
entity to allow runtime changes without redeployment,
especially if client needs self-service config management.

## Database Idepontecy

Webhooks are not receiving an idepodency-key to prevent duplicate processing, only validating id, for now it works because it uses pix as single payment.
This is a problem with installments because if the same webhook is sent multiple times, it can lead to duplicate processing and potentially cause issues with the system.
Recommended future improvement: Implement an idempotency key mechanism for webhooks to ensure that duplicate requests are identified and handled appropriately, preventing duplicate processing and ensuring the integrity of the system.