package ru.kovshov.shop.shop;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

//@SpringBootTest
@WebMvcTest
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHomePage() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/")) //Выполняет Get
                .andExpect(MockMvcResultMatchers.status().isOk()) // ожидает http 200
                .andExpect(MockMvcResultMatchers.view().name("home")) // ожидает страницу home
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Welcome to...")));
    }
}
