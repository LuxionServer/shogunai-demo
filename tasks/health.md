# Health endpoint

Add `GET /health` answering `{"status": "UP", "database": "UP", "books": <count>}` when the database answers, and 503 with `"status": "DOWN"` and `"database": "DOWN"` when it doesn't.

Done when:

- `./mvnw test` passes with tests for both cases.
- The stack is running with `docker compose up -d --build` and `curl "localhost:${API_PORT:-8080}/health"` answers `UP` with 20 books.
