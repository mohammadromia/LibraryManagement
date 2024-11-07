package com.managementsystem.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.managementsystem.library.entities.Book;
import com.managementsystem.library.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
@SpringBootTest
class BookControllerTest {


    private MockMvc mockMvc;
    @Autowired
    private BookController controller;
    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetAllUsers() throws Exception {
        mockMvc.perform(get("http://localhost:8080/api/books"))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void testGetBookById() throws Exception {
        mockMvc.perform(get("http://localhost:8080/api/books/1"))
                .andExpect(status().isOk());
    }
    @Test
    public void testAddBook() throws Exception {
        Book newBook = new Book();
        newBook.setAuthor("add Author");
        newBook.setTitle("add title");
        newBook.setPublicationYear(2000);
        newBook.setIsbn("21312dwq22");
        mockMvc.perform(post("http://localhost:8080/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newBook)))
                .andExpect(status().isOk());
    }
    @Test
    public void testUpdateBook() throws Exception {
        Book updatedBook = new Book();
        updatedBook.setAuthor("Updated Author");
        updatedBook.setTitle("Updated title");
        updatedBook.setPublicationYear(2000);
        updatedBook.setIsbn("2131wq22");
        mockMvc.perform(put("http://localhost:8080/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedBook)))
                .andExpect(status().isOk());
    }



    @Test
    public void testDeleteBook() throws Exception {
        mockMvc.perform(delete("http://localhost:8080/api/books/1"))
                .andExpect(status().isOk());
    }

}