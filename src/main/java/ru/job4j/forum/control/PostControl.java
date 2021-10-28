package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

@Controller
public class PostControl {
    private PostService postService;

    public PostControl(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/create")
    public String create(@ModelAttribute("post") Post post, Model model) {
        model.addAttribute("user",
                SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "post/saveOrUpdate";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("post") Post post, Model model) {
        postService.saveOrUpdate(post);
        model.addAttribute("user",
                SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("postId") int postId, Model model) {
        model.addAttribute("post",
                postService.findById(postId));
        model.addAttribute("user",
                SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "post/saveOrUpdate";
    }

    @GetMapping("/post")
    public String post(@RequestParam("postId") int postId, Model model) {
        model.addAttribute("post",
                postService.findById(postId));
        model.addAttribute("user",
                SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "post/post";
    }

}
