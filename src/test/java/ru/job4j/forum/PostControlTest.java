package ru.job4j.forum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class PostControlTest {

    private MockMvc mockMvc;

    @Autowired
    public PostControlTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }
    @Test
    @WithMockUser
    void whenGetPostPage() throws Exception {
        mockMvc.perform(get("/post?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post/post"));
    }

    @Test
    @WithMockUser
    void whenGetUpdatePage() throws Exception {
        mockMvc.perform(get("/update"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post/saveOrUpdate"));
    }

    @Test
    void whenDeletePost() throws Exception {
        mockMvc.perform(get("/delete"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("/"));
    }
}
