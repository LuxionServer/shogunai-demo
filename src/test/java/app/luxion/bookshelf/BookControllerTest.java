package app.luxion.bookshelf;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private BookRepository books;

    @Test
    void listsEveryBook() throws Exception {
        given(books.findAll()).willReturn(List.of(
                new Book(1L, "Refactoring", "Martin Fowler", 1999),
                new Book(2L, "Clean Code", "Robert C. Martin", 2008)));

        mvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("Refactoring"));
    }

    @Test
    void returnsNotFoundForAnUnknownBook() throws Exception {
        given(books.findById(42L)).willReturn(Optional.empty());

        mvc.perform(get("/books/42")).andExpect(status().isNotFound());
    }

    @Test
    void createsABookAndPointsToIt() throws Exception {
        given(books.save(any())).willReturn(new Book(21L, "Tidy First?", "Kent Beck", 2023));

        mvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"title": "Tidy First?", "author": "Kent Beck", "publishedYear": 2023}
                                """))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/books/21"))
                .andExpect(jsonPath("$.id").value(21));
    }
}
