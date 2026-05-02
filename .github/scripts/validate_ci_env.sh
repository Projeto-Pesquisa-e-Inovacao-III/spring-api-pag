#!/usr/bin/env bash
set -euo pipefail

ENV_FILE="${1:-.env}"
EXPECT_RABBITMQ_VHOST="${EXPECT_RABBITMQ_VHOST:-}"

if [ ! -f "$ENV_FILE" ]; then
  echo "[ERROR] Env file not found: $ENV_FILE"
  exit 1
fi

declare -A ENV_VALUES=()
while IFS= read -r line || [ -n "$line" ]; do
  # Skip empty lines and comments.
  if [ -z "$line" ] || [ "${line#\#}" != "$line" ]; then
    continue
  fi

  if [[ "$line" != *"="* ]]; then
    echo "[ERROR] Invalid env line (missing '='): $line"
    exit 1
  fi

  key="${line%%=*}"
  value="${line#*=}"
  value="${value%$'\r'}"
  ENV_VALUES["$key"]="$value"
done < "$ENV_FILE"

REQUIRED_VARS=(
  PAGBANK_API_URL
  PAGBANK_API_TOKEN
  PAGBANK_API_PAY_URL
  GATEWAY_CONFIG_NOTIFICATION_WEBHOOK_URLS
  GATEWAY_CONFIG_PAYMENT_NOTIFICATION_URLS
  GATEWAY_CONFIG_PAYMENT_METHOD
  GATEWAY_CONFIG_SOFT_DESCRIPTOR
  GATEWAY_CONFIG_RETURN_URL
  GATEWAY_CONFIG_REDIRECT_URL
  GATEWAY_CONFIG_CUSTOMER_MODIFIABLE
  EVENT_URLS
  SPRING_RABBITMQ_PORT
  SPRING_RABBITMQ_USERNAME
  SPRING_RABBITMQ_PASSWORD
  SPRING_RABBITMQ_VHOST
  PAYMENT_WEBHOOK_SECRET
  MYSQL_HOST
  MYSQL_PORT
  MYSQL_DATABASE
  MYSQL_USER
  MYSQL_PASSWORD
  NGROK_URL
  NGROK_AUTHTOKEN
)

MISSING=()
for var_name in "${REQUIRED_VARS[@]}"; do
  value="${ENV_VALUES[$var_name]:-}"
  if [ -z "$value" ]; then
    MISSING+=("$var_name")
  fi
done

if [ ${#MISSING[@]} -gt 0 ]; then
  echo "[ERROR] Missing required env vars in $ENV_FILE: ${MISSING[*]}"
  exit 1
fi

if [ -n "$EXPECT_RABBITMQ_VHOST" ]; then
  if [ "${ENV_VALUES[SPRING_RABBITMQ_VHOST]}" != "$EXPECT_RABBITMQ_VHOST" ]; then
    echo "[ERROR] SPRING_RABBITMQ_VHOST must be $EXPECT_RABBITMQ_VHOST"
    exit 1
  fi
fi

echo "[INFO] Env validation passed for $ENV_FILE"

