package ru.job4j.forum;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class PostControlTest {

    @MockBean
    private PostService posts;

    private MockMvc mockMvc;

    @Autowired
    public PostControlTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    @WithMockUser
    void whenGetPostPage() throws Exception {
        mockMvc.perform(get("/post?postId=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post/post"));
    }

    @Test
    @WithMockUser
    void whenGetUpdatePage() throws Exception {
        mockMvc.perform(get("/update?postId=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post/saveOrUpdate"));
    }

    @Test
    @WithMockUser
    public void whenSavePost() throws Exception {
        this.mockMvc.perform(post("/save")
                        .param("name", "Куплю ладу-грант. Дорого."))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(posts).saveOrUpdate(argument.capture());
        assertThat(argument.getValue().getName(), is("Куплю ладу-грант. Дорого."));
    }

    @Test
    @WithMockUser
    public void whenUpdatePost() throws Exception {
        Post post = new Post();
        post.setName("name");
        post.setDescription("description");
        int id = posts.saveOrUpdate(post).getId();
        this.mockMvc.perform(get("/update?postId=" + id)
                        .param("name", "Куплю ладу-грант. Дорого."))
                .andDo(print())
                .andExpect(status().isOk());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(posts).saveOrUpdate(argument.capture());
        assertThat(argument.getValue().getName(), is("Куплю ладу-грант. Дорого."));
        assertThat(argument.getValue().getDescription(), is("description"));
    }
}
