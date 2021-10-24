package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.store.AuthorityRepository;

@Service
public class AuthorityService {
    private final AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public void save(Authority auth) {
        authorityRepository.save(auth);
    }

    public Authority findByAuthority(String auth) {
        return authorityRepository.findByAuthority(auth);
    }
}
