# AGENTS.md

Bookshelf is a small Spring Boot API over Postgres used to demonstrate [ShogunAi](https://github.com/LuxionServer/ShogunAi): several agents working on it in parallel, each in its own worktree with its own Compose project and ports.

## Working on a task

- Tasks are in `tasks/`; each one says when it's done.
- Run the stack with `docker compose up -d --build` and call the API on `localhost:${API_PORT:-8080}`. The database is on `localhost:${DB_PORT:-5432}`. In a worktree created through ShogunAi these variables and `COMPOSE_PROJECT_NAME` are already set; don't hardcode ports.
- Run the tests with `./mvnw test`. They don't need the database.
- Keep to the existing style: Java records, `JdbcClient` queries in `BookRepository`, controllers returning `ResponseEntity` where the status varies.

## Rules

- No comments in code.
- Code, commits and docs in English.
- This repository is **public**: never commit `.env`, credentials, machine-specific paths or local tool settings.
- `develop` is the default branch; changes go through pull requests against it.
