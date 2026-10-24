# ShogunAi demo: three agents, one backend

A small Spring Boot API over Postgres for running several AI coding agents on the same project at once, with and without [ShogunAi](https://github.com/LuxionServer/ShogunAi).

## The problem it shows

The `compose.yaml` publishes fixed ports and names its project, as many real projects do. Two agents in two worktrees that run `docker compose up --build` don't get two stacks: the second one recreates the first one's containers, and the first agent ends up testing the second agent's code on `localhost:8080`, without any error.

With ShogunAi, the repository declares once what each worktree needs in `.shogunai.json`:

```json
{
  "copy": ["/.env"],
  "providers": {
    "docker-compose": { "portVariables": ["DB_PORT", "API_PORT"] },
    "maven": { "mavenMaxHeap": "1g" }
  }
}
```

Every worktree then gets its copy of `.env`, its own `COMPOSE_PROJECT_NAME`, its own free `DB_PORT` and `API_PORT`, and Maven builds bounded to 1 GB.

## Requirements

- Docker with Compose.
- [ShogunAi](https://github.com/LuxionServer/ShogunAi#installation) with the `shogun` command.
- An agent such as [Claude Code](https://claude.com/claude-code). Java isn't needed: the API is built inside Docker and `./mvnw` downloads Maven, though running the tests locally needs a JDK 25.

## Running it

```bash
git clone https://github.com/LuxionServer/shogunai-demo.git
cd shogunai-demo
cp .env.example .env
```

Add the folder as a project in the ShogunAi app. The hooks in `.claude/settings.json` make `claude --worktree` create its worktrees through ShogunAi, so start one session per task:

```bash
claude --worktree search "Do the task in tasks/search.md"
claude --worktree pagination "Do the task in tasks/pagination.md"
claude --worktree health "Do the task in tasks/health.md"
```

Each session brings up its own stack; `docker compose ls` lists one project per worktree, and the ShogunAi app shows which agent is working, which one is waiting for you and which one is done. When a task is merged, `shogun clean --yes --down-compose-projects` removes its worktree and takes its stack down.

Without Claude Code, create the worktrees with `shogun new <task>` and load their variables with `eval "$(shogun env)"` before `docker compose up -d --build`.

## Without ShogunAi

The `without-shogunai` branch has no `.shogunai.json` and no hooks. Run the same three sessions from it to see the agents step on each other.

## The API

| Endpoint | What it does |
|---|---|
| `GET /books` | Every book |
| `GET /books/{id}` | One book, or 404 |
| `POST /books` | Adds a book (`title`, `author`, `publishedYear`) |

The tasks in `tasks/` add search, pagination and a health endpoint.

## License

[MIT](./LICENSE)
