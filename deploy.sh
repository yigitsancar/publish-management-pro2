#!/bin/bash

set -e

echo "Pulling latest image..."
sudo docker pull yigitsancar/publish-management-pro2:latest

echo "Stopping old containers..."
sudo docker compose down || true

echo "Starting containers with compose..."
sudo docker compose -f /home/ubuntu/compose.yml up -d

echo "Deployment completed."

