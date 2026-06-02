#!/usr/bin/env bash
set -euo pipefail

echo "[INFO] Deploy stub for spring-api-pag"
echo "[INFO] TODO: Implement compose-based deploy for production"

echo "[INFO] Expected inputs (sample):"
cat <<'EOF'
APP_IMAGE
COMPOSE_FILE=compose.prod.yaml
ENV_FILE=.env
EOF

exit 0

