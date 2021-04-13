package com.bookstore.demo;

import com.bookstore.demo.controller.BookController;
import com.bookstore.demo.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@WebMvcTest(BookController.class)
public class ControllerIntegrationTests {
    //This file tests the primary Book CRUD and all of the GET methods for the tables.
    @Autowired
    public MockMvc mockMvc;

    @Test
    public void testGetMethodsForAll() throws Exception {
        //This tests the Get Endpoints for all tables with hardcoded entries for the database
        //So the user can test it without DB tool or Postman
        //These are placeholder values for url and containsString

        this.mockMvc.perform(get("/api/books/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Book One")));
        this.mockMvc.perform(get("/api/books/2/authors")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Suresh")));
        this.mockMvc.perform(get("/api/books/2/genres")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Fiction")));
        this.mockMvc.perform(get("/api/books/2/publishers")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("PublisherOne")));

    }
    @Test
    public void testPostMethod() throws Exception {
        //Testing Post Method for for main table Book
        //These are placeholder values for url and content
        this.mockMvc.perform(post("/api/books").contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"newBook\",\"description\":\"new book description\"}"))
                .andExpect(status().isOk());
        }

    @Test
        //Testing Put Method for the main table Book
        //These are placeholder values for url and content
    public void testPutMethod() throws Exception {
        this.mockMvc.perform(put("/api/books/3")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"title\":\"Book3\",\"description\":\"book3 description\"}")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testDeleteMethod() throws Exception {
        //Testing Delete Method for main table Book
        //These are placeholder values for url
        this.mockMvc.perform(delete("/api/books/3").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}



