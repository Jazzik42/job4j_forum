package ru.job4j.forum.service;
import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.store.PostRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> getAll() {
        List<Post> posts =  new ArrayList<>();
        for (Post post : repository.findAll()) {
            posts.add(post);
        }
        return posts;
    }

    public Post saveOrUpdate(Post post) {
        return repository.save(post);
    }

    public Post findById(int id) {
        return repository.findById(id).get();
    }
}
