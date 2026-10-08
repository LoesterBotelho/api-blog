# Como verificar erros do Flyway

Este guia mostra como verificar se uma migration do **Flyway** teve sucesso ou falhou em uma aplicação **Spring Boot**.

Existem duas formas principais:

1. Verificar o **log do Spring Boot**
2. Consultar a tabela `flyway_schema_history`

---

## 1. Verificando pelo log do Spring Boot

Ao iniciar a aplicação:

```bash
mvn spring-boot:run
```

Observe o console do Spring Boot.

### Migration com sucesso

Você poderá encontrar mensagens como:

```text
Successfully validated migrations
```

ou:

```text
Successfully applied migration
```

Isso indica que a migration foi executada corretamente.

---

## 2. Quando uma migration falha

Se uma migration apresentar erro, o Spring Boot mostrará no console algo parecido com:

```text
Migration V3__adicionar_autor.sql failed
```

Procure também pela mensagem:

```text
Caused by:
```

Exemplo:

```text
Migration V3__adicionar_autor.sql failed

Caused by: java.sql.SQLSyntaxErrorException:
Unknown column 'autor' in 'field list'
```

Nesse caso:

```text
V3__adicionar_autor.sql
```

é a migration que apresentou o problema.

E:

```text
Unknown column 'autor'
```

é a causa do erro.

---

# 3. Verificando pelo banco de dados

O Flyway mantém um histórico das migrations na tabela:

```text
flyway_schema_history
```

Execute:

```sql
SELECT
    installed_rank,
    version,
    description,
    script,
    success
FROM flyway_schema_history
ORDER BY installed_rank;
```

---

# 4. Entendendo a coluna `success`

A coluna `success` indica o resultado da migration.

```text
success = 1
```

Significa:

```text
Migration executada com sucesso.
```

Já:

```text
success = 0
```

Significa:

```text
Migration apresentou falha.
```

### Exemplo

```text
installed_rank | version | description       | script                    | success
---------------+---------+-------------------+---------------------------+--------
1              | 1       | criar posts       | V1__criar_posts.sql       | 1
2              | 2       | criar comentarios | V2__criar_comentarios.sql | 1
3              | 3       | adicionar autor   | V3__adicionar_autor.sql   | 0
```

Nesse exemplo:

```text
V1 → sucesso
V2 → sucesso
V3 → falha
```

---

# 5. Ver somente as migrations que falharam

Podemos filtrar utilizando:

```sql
SELECT
    installed_rank,
    version,
    description,
    script,
    success
FROM flyway_schema_history
WHERE success = 0
ORDER BY installed_rank;
```

Se não retornar nenhum registro, não existem migrations registradas com:

```text
success = 0
```

---

# 6. Log x SELECT

As duas formas possuem objetivos diferentes.

### SELECT

Mostra:

```text
Qual migration falhou?
```

Exemplo:

```text
V3__adicionar_autor.sql
```

### Log do Spring Boot

Mostra:

```text
Por que a migration falhou?
```

Exemplo:

```text
Unknown column 'autor' in 'field list'
```

---

# 7. Resumo

O fluxo para investigar um problema é:

```text
Spring Boot
    ↓
Verificar o LOG
    ↓
Identificar a migration
    ↓
Consultar flyway_schema_history
    ↓
Verificar success
    ↓
success = 1 → sucesso
success = 0 → falha
```

### Consulta principal

```sql
SELECT
    installed_rank,
    version,
    description,
    script,
    success
FROM flyway_schema_history
ORDER BY installed_rank;
```

### Somente falhas

```sql
SELECT
    version,
    description,
    script,
    success
FROM flyway_schema_history
WHERE success = 0;
```

---

## Regra simples

> O `SELECT` mostra **qual migration falhou** e o **log do Spring Boot mostra o motivo do erro**.
