package com.bookstore.demo;

import com.bookstore.demo.controller.BookController;
import com.bookstore.demo.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerIntegrationTests {

    @Autowired
    public MockMvc mockMvc;

    @Test
    public void testCreateRetrieveWithMockMVC() throws Exception {
        this.mockMvc.perform(get("/api/books/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Book One")));
        this.mockMvc.perform(get("/api/books/2/authors")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Suresh")));
        this.mockMvc.perform(get("/api/books/2/genres")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Fiction")));
        this.mockMvc.perform(get("/api/books/2/publishers")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("PublisherOne")));
        this.mockMvc.perform(post("/api/books")).andExpect(status().is2xxSuccessful());

    }

}

