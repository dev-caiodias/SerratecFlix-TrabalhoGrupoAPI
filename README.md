# 🎬 SerratecFlix API

API REST para gerenciamento de filmes, séries, avaliações e listas de favoritos, desenvolvida com Spring Boot como projeto avaliativo do curso de Desenvolvimento de API no SENAI/Serratec.

---

## 👥 Integrantes e Contribuições

### Caio Vinícius Dias — `feature/caio`
**Entidade:** Usuario + Setup global + Security/JWT

Responsável pela infraestrutura base do projeto. Implementou o CRUD completo de usuários com validação de duplicatas de username e email, encriptação de senha com BCrypt e mapeamento Entity → DTO sem expor a senha na resposta. Configurou toda a camada de segurança com Spring Security e JWT: `JwtUtil` para geração e validação de tokens, `JwtAuthFilter` para interceptar requisições, `CustomUserDetailsService` para autenticação via banco, `AuthController` com o endpoint público `POST /auth/login` e `SecurityConfig` definindo as rotas públicas e protegidas. Também criou as exceções customizadas `ResourceNotFoundException` e `ConflictException`, o `GlobalExceptionHandler` com respostas padronizadas para erros 400, 404, 409 e 500, e o `SwaggerConfig` com autenticação BearerAuth.

**Diferencial individual:** Sistema de envio de email de boas-vindas — ao criar um novo usuário via `POST /usuarios`, o sistema dispara automaticamente um email para o endereço cadastrado utilizando `JavaMailSender` com SMTP do Gmail, notificando o usuário sobre a criação da conta no SerratecFlix.

---

### Bernardo Araújo — `feature/bernardo`
**Entidade:** Filme + Consumo de API externa (OMDB)

Implementou o CRUD completo de filmes com relacionamento `@ManyToMany` com Categoria e `@OneToMany` com AvaliacaoFilme. O `FilmeRequest` recebe uma lista de ids de categorias que são buscadas no banco e associadas ao filme. O `FilmeResponse` retorna apenas os nomes das categorias, sem recursão. Criou o ENUM `ClassificacaoIndicativa` com `@Enumerated(EnumType.STRING)` e o método público `recalcularNotaMedia(Long filmeId)` no Service, chamado automaticamente após cada avaliação criada, atualizada ou removida. Integrou a API externa OMDB: o endpoint `GET /filmes/{id}/info-externa` busca o título do filme no banco, consulta a OMDB e retorna informações complementares como diretor, prêmios, poster e rating do IMDb.

**Diferencial individual:** Ranking de filmes com paginação — endpoint `GET /filmes/ranking?page=0&size=10` que retorna filmes ordenados por nota média decrescente usando JPQL com `ORDER BY`. O DTO de resposta inclui a posição no ranking e o total de avaliações recebidas pelo filme.

---

### Mário José Praun — `feature/mario`
**Entidade:** Serie

Implementou o CRUD completo de séries com relacionamento `@ManyToMany` com Categoria e `@OneToMany` com AvaliacaoSerie, seguindo o mesmo padrão do Filme. Criou o método `recalcularNotaMedia(Long serieId)` chamado após cada avaliação de série. O repositório inclui Query Method para busca por título, JPQL para busca por nota mínima e query nativa para busca por número mínimo de temporadas.

**Diferencial individual:** Estatísticas detalhadas de série — endpoint `GET /series/{id}/stats` que retorna total de avaliações, nota média, nota mais alta, nota mais baixa e média de episódios por temporada, reunidas em um único DTO `SerieStatsResponse` usando múltiplas queries JPQL agregadas.

---

### Vitor Ribeiro — `feature/vitor`
**Entidades:** Categoria + AvaliacaoFilme

Implementou o CRUD de Categoria — entidade mais simples e prioritária, pois Bernardo e Mário dependem dos ids de categoria para criar filmes e séries. Adicionou verificação de nome duplicado com `ConflictException`. Também implementou o CRUD completo de AvaliacaoFilme com relacionamentos `@ManyToOne` para Usuario e Filme via `@JsonBackReference`. A nota é validada entre 0 e 10 com `@DecimalMin` e `@DecimalMax`. Após cada criação, atualização ou remoção de avaliação, chama `filmeService.recalcularNotaMedia()` automaticamente.

**Diferencial individual:** Sistema de recomendação — endpoint `GET /usuarios/{id}/recomendacoes` que busca as categorias dos filmes mais bem avaliados pelo usuário (nota ≥ 7) e retorna outros filmes das mesmas categorias que ele ainda não avaliou, usando JPQL com `NOT IN` e `JOIN`.

---

### Valois Leite da Silva Costa — `feature/valois`
**Entidade:** AvaliacaoSerie

Implementou o CRUD completo de avaliações de séries com relacionamentos `@ManyToOne` para Usuario e Serie. Seguiu o mesmo padrão de AvaliacaoFilme com validação de nota entre 0 e 10. Após cada operação, chama `serieService.recalcularNotaMedia()`. O repositório inclui Query Method, JPQL para cálculo de média e query nativa para busca de avaliações por nota mínima ordenadas de forma decrescente.

**Diferencial individual:** Histórico unificado de avaliações do usuário — endpoint `GET /usuarios/{id}/historico-avaliacoes?page=0&size=10` que retorna todas as avaliações feitas (filmes e séries) em ordem cronológica decrescente com paginação, usando um DTO unificado `AvaliacaoHistoricoResponse` com campo `tipo` (FILME ou SERIE).

---

### Marcelo Pozzato Rabello Mayrinck — `feature/marcelo`
**Entidade:** ListaFavoritos

Implementou o CRUD de listas de favoritos com relacionamento `@ManyToOne` com Usuario e `@ManyToMany` com Filme e Serie. Além do CRUD básico, criou endpoints extras para gerenciar o conteúdo das listas com verificação de duplicata. O `ListaFavoritosResponse` usa DTOs resumidos `FilmeResumoResponse` e `SerieResumoResponse` para evitar recursão e respostas muito aninhadas.

**Diferencial individual:** Listas públicas em destaque — endpoint `GET /listas/destaque?limite=10` que retorna as listas públicas com mais conteúdo (filmes + séries), ordenadas por quantidade decrescente, usando JPQL com `SIZE()` ou query nativa com `COUNT` e `GROUP BY`.

---

## 🛠️ Tecnologias

| Tecnologia | Versão |
|---|---|
| Java | 17 |
| Spring Boot | 4.0.6 |
| Spring Security | 7.0.5 |
| Spring Data JPA | 4.0.5 |
| PostgreSQL | 18 |
| Hibernate | 7.2.12 |
| jjwt | 0.11.5 |
| Lombok | 1.18.46 |
| springdoc-openapi | 1.8.0 |
| Maven | 3.x |

---

## ⚙️ Como configurar e rodar

### 1. Pré-requisitos

- Java 17+
- Maven 3.x
- PostgreSQL instalado e rodando

### 2. Criar o banco de dados

```sql
CREATE DATABASE serratecflix;
```

### 3. Configurar o `application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/SerratecFlix
spring.datasource.username=SEU_USUARIO_POSTGRES
spring.datasource.password=SUA_SENHA_POSTGRES

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

jwt.secret=SUA_CHAVE_JWT_32_CARACTERES_MINIMO
jwt.expiration=86400000

omdb.api.key=SUA_CHAVE_OMDB

spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=SEU_EMAIL@gmail.com
spring.mail.password=SUA_SENHA_DE_APP_GMAIL
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

> **jwt.secret** deve ter no mínimo 32 caracteres.
> **spring.mail.password** é a senha de app gerada em: Conta Google → Segurança → Senhas de app.

### 4. Rodar a aplicação

```bash
mvn spring-boot:run
```

A aplicação sobe em `http://localhost:8080`.

---

## 📖 Swagger

```
http://localhost:8080/swagger-ui.html
```

Para testar endpoints protegidos: faça login em `POST /auth/login`, copie o token, clique em **Authorize** e cole o token.

---

## 🔐 Autenticação JWT

Rotas públicas:

| Método | Rota |
|---|---|
| POST | /auth/login |
| POST | /usuarios |

Todas as demais exigem o header:
```
Authorization: Bearer <token>
```

---

## 📋 Endpoints

### Auth
| Método | Rota | Descrição | Token |
|---|---|---|---|
| POST | /auth/login | Login e geração de token | ❌ |

### Usuários
| Método | Rota | Descrição | Token |
|---|---|---|---|
| GET | /usuarios | Lista todos os usuários | ✅ |
| GET | /usuarios/{id} | Busca por ID | ✅ |
| POST | /usuarios | Cria usuário + envia email de boas-vindas | ❌ |
| PUT | /usuarios/{id} | Atualiza usuário | ✅ |
| DELETE | /usuarios/{id} | Remove usuário | ✅ |

### Filmes
| Método | Rota | Descrição | Token |
|---|---|---|---|
| GET | /filmes | Lista todos | ✅ |
| GET | /filmes/{id} | Busca por ID | ✅ |
| GET | /filmes/buscar?titulo=X | Busca por título | ✅ |
| GET | /filmes/ranking | Ranking por nota média | ✅ |
| GET | /filmes/{id}/info-externa | Info via OMDB | ✅ |
| POST | /filmes | Cria filme | ✅ |
| PUT | /filmes/{id} | Atualiza filme | ✅ |
| DELETE | /filmes/{id} | Remove filme | ✅ |

### Séries
| Método | Rota | Descrição | Token |
|---|---|---|---|
| GET | /series | Lista todas | ✅ |
| GET | /series/{id} | Busca por ID | ✅ |
| GET | /series/buscar?titulo=X | Busca por título | ✅ |
| GET | /series/{id}/stats | Estatísticas da série | ✅ |
| POST | /series | Cria série | ✅ |
| PUT | /series/{id} | Atualiza série | ✅ |
| DELETE | /series/{id} | Remove série | ✅ |

### Categorias
| Método | Rota | Descrição | Token |
|---|---|---|---|
| GET | /categorias | Lista todas | ✅ |
| GET | /categorias/{id} | Busca por ID | ✅ |
| POST | /categorias | Cria categoria | ✅ |
| PUT | /categorias/{id} | Atualiza categoria | ✅ |
| DELETE | /categorias/{id} | Remove categoria | ✅ |

### Avaliações de Filme
| Método | Rota | Descrição | Token |
|---|---|---|---|
| GET | /avaliacoes-filme | Lista todas | ✅ |
| GET | /avaliacoes-filme/{id} | Busca por ID | ✅ |
| GET | /avaliacoes-filme/filme/{filmeId} | Por filme | ✅ |
| GET | /usuarios/{id}/recomendacoes | Recomendações por perfil | ✅ |
| POST | /avaliacoes-filme | Cria avaliação | ✅ |
| PUT | /avaliacoes-filme/{id} | Atualiza avaliação | ✅ |
| DELETE | /avaliacoes-filme/{id} | Remove avaliação | ✅ |

### Avaliações de Série
| Método | Rota | Descrição | Token |
|---|---|---|---|
| GET | /avaliacoes-serie | Lista todas | ✅ |
| GET | /avaliacoes-serie/{id} | Busca por ID | ✅ |
| GET | /avaliacoes-serie/serie/{serieId} | Por série | ✅ |
| GET | /avaliacoes-serie/top?notaMinima=8.0 | Top por nota | ✅ |
| GET | /usuarios/{id}/historico-avaliacoes | Histórico unificado | ✅ |
| POST | /avaliacoes-serie | Cria avaliação | ✅ |
| PUT | /avaliacoes-serie/{id} | Atualiza avaliação | ✅ |
| DELETE | /avaliacoes-serie/{id} | Remove avaliação | ✅ |

### Listas de Favoritos
| Método | Rota | Descrição | Token |
|---|---|---|---|
| GET | /listas | Lista todas | ✅ |
| GET | /listas/{id} | Busca por ID | ✅ |
| GET | /listas/usuario/{usuarioId} | Listas do usuário | ✅ |
| GET | /listas/destaque | Listas públicas em destaque | ✅ |
| POST | /listas | Cria lista | ✅ |
| POST | /listas/{id}/filmes/{filmeId} | Adiciona filme | ✅ |
| POST | /listas/{id}/series/{serieId} | Adiciona série | ✅ |
| PUT | /listas/{id} | Atualiza lista | ✅ |
| DELETE | /listas/{id} | Remove lista | ✅ |
| DELETE | /listas/{id}/filmes/{filmeId} | Remove filme da lista | ✅ |
| DELETE | /listas/{id}/series/{serieId} | Remove série da lista | ✅ |

---

## ⚠️ Respostas de Erro Padronizadas

```json
{
  "timestamp": "2026-05-27T18:00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Usuário não encontrado com id: 99",
  "path": "/usuarios/99"
}
```

| Status | Situação |
|---|---|
| 400 | Campos inválidos ou em branco |
| 401 | Token ausente ou inválido |
| 404 | Recurso não encontrado |
| 409 | Duplicata (username, email, nome de categoria) |
| 500 | Erro interno do servidor |