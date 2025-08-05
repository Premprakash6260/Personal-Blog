package com.example.signimus.personal_blog.Controller;

import com.example.signimus.personal_blog.DTO.ArticleDto;
import com.example.signimus.personal_blog.Service.GuestServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/guest")
public class GuestController {
    @Autowired
    private GuestServiceImpl guestService;

    @GetMapping("/testGuest")
    public ResponseEntity<String> testGuest(){
        return ResponseEntity.ok("Guest controller is working");
    }

//    get all articles
    @GetMapping("/articles")
    public ResponseEntity<List<ArticleDto>> getAllArticles(){
        List<ArticleDto> allArticles = guestService.getAllArticles();
        return ResponseEntity.ok(allArticles);
    }

//    get article by id
    @GetMapping("/articles/{id}")
    public ResponseEntity<ArticleDto> getArticleById(@PathVariable Long id){
        ArticleDto articleById = guestService.getArticleById(id);
        return ResponseEntity.ok(articleById);
    }
}
