# Paginate the book list

Make `GET /books` accept `page` (from 0) and `size` (1 to 50, default 10) and answer `{"items": [...], "page": 0, "size": 10, "total": 20}`. Values out of range answer 400.

Done when:

- `./mvnw test` passes with tests for the default page, a given page and size, the last partial page and invalid values.
- The stack is running with `docker compose up -d --build` and `curl "localhost:${API_PORT:-8080}/books?page=1&size=5"` returns books 6 to 10.
