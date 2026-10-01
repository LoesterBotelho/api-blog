@echo off
REM ==========================================
REM Arquivo Central de Configuracoes
REM ==========================================

REM Docker & App Configuration
set DOCKER_USER=loesterbotelho
set IMAGE_NAME=api-blog
set IMAGE_VERSION=1.0.0
set CONTAINER_NAME=api-blog
set HOST_PORT=8080
set CONTAINER_PORT=8080

REM Test Configuration (Newman)
set POSTMAN_COLLECTION=api-blog.postman_collection.json