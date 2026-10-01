@echo off
setlocal

REM Carrega as variaveis centrais (caso precise de portas ou nomes no futuro)
call config.bat

echo Verificando se o Node.js esta instalado...
node -v >nul 2>&1
if %errorlevel% neq 0 (
    echo [ERRO] O Node.js nao foi encontrado no seu computador.
    echo Por favor, instale o Node.js antes de continuar: https://nodejs.org/
    pause
    exit /b
)

echo [OK] Node.js detectado com sucesso.
echo Verificando se o Newman ja esta instalado...

call newman --version >nul 2>&1
if %errorlevel% neq 0 (
    echo Newman nao encontrado. Instalando globalmente...
    call npm install -g newman --silent
) else (
    echo [OK] O Newman ja esta instalado. Pulando a instalacao.
)

echo.
echo Executando a colecao de testes com o Newman...
newman run %POSTMAN_COLLECTION%

echo.
echo Testes finalizados!
pause
endlocal