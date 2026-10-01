@echo off
setlocal

REM Carrega as variaveis centrais
call config.bat

set FULL_IMAGE=%DOCKER_USER%/%IMAGE_NAME%:%IMAGE_VERSION%

echo.
echo Fazendo login no Docker Hub...
docker login

echo.
echo Construindo a imagem Docker para %FULL_IMAGE%...
docker build -t %FULL_IMAGE% .

echo.
echo Enviando a imagem para o Docker Hub...
docker push %FULL_IMAGE%

echo.
echo Processo concluido com sucesso!
pause
endlocal