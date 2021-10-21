package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.UserService;

@Controller
public class RegControl {
    private PostService postService;
    private UserService userService;

    public RegControl(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping("/reg")
    public String save(@ModelAttribute User user) {
        //user.setEnabled(true);
        //user.setPassword(user.getPassword());
    //user.setAuthority(authorityService.findByAuthority("ROLE_USER"));

    return "redirect:/login";
    }

    @GetMapping("/reg")
    public String reg() {
        return "reg";
    }
}
