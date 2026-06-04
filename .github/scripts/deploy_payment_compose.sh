#!/usr/bin/env bash
set -euo pipefail

echo "[INFO] Deploying spring-api-pag using compose"

APP_IMAGE=${APP_IMAGE:-""}
COMPOSE_FILE=${COMPOSE_FILE:-"compose.prod.yaml"}
ENV_FILE=${ENV_FILE:-".env"}

if [ -z "$APP_IMAGE" ]; then
  echo "[ERROR] APP_IMAGE is not set"
  exit 1
fi

if [ ! -f "$ENV_FILE" ]; then
  echo "[ERROR] Env file not found: $ENV_FILE"
  exit 1
fi

echo "[INFO] Pulling images..."
echo "APP_IMAGE=$APP_IMAGE" >> "$ENV_FILE"
docker compose -f "$COMPOSE_FILE" --env-file "$ENV_FILE" pull

echo "[INFO] Starting services..."
docker compose -f "$COMPOSE_FILE" --env-file "$ENV_FILE" up -d

echo "[INFO] Deploy complete."


