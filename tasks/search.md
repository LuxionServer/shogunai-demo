# Search books

Add `GET /books/search?q=<text>` returning the books whose title or author contains the text, ignoring case, ordered by title. An empty or missing `q` answers 400.

Done when:

- `./mvnw test` passes with tests for matches by title, by author, no matches and a missing `q`.
- The stack is running with `docker compose up -d --build` and `curl "localhost:${API_PORT:-8080}/books/search?q=fowler"` returns both Martin Fowler books.
