#!/bin/bash

set -e

echo "Pulling latest image..."
sudo docker pull yigitsancar/publish-management-pro2:latest

echo "Stopping old app container..."
sudo docker rm -f publish-management-app || true

echo "Starting app container..."
sudo docker run -d \
  --name publish-management-app \
  -p 80:8080 \
  --add-host=host.docker.internal:host-gateway \
  -e SPRING_DATASOURCE_URL="jdbc:postgresql://host.docker.internal:5432/publishdb?sslmode=disable" \
  -e SPRING_DATASOURCE_USERNAME=postgres \
  -e SPRING_DATASOURCE_PASSWORD=1234 \
  yigitsancar/publish-management-pro2:latest

echo "Deployment completed."
