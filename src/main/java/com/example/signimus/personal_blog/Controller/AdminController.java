package com.example.signimus.personal_blog.Controller;

import com.example.signimus.personal_blog.DTO.ArticleDto;
import com.example.signimus.personal_blog.DTO.ArticleRequest;
import com.example.signimus.personal_blog.Entity.Article;
import com.example.signimus.personal_blog.Service.AdminService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/admin")
public class AdminController {
    @Autowired
    private final AdminService adminService;

    @GetMapping("/testAdmin")
    public ResponseEntity<String> seyHello() {
        return ResponseEntity.ok("Hello from Admin Controller!");
    }

//    get all articles
    @GetMapping("/articles")
    public ResponseEntity<List<ArticleDto>> getAllArticles(){
        List<ArticleDto> articleDtos = adminService.getAllArticles();
        return ResponseEntity.ok(articleDtos);
    }

//    get article by id
    @GetMapping("/articles/{id}")
    public ResponseEntity<ArticleDto> getArticleById(@PathVariable Long id){
        ArticleDto articleDto = adminService.getArticleById(id);
        return ResponseEntity.ok(articleDto);
    }

//    add new article
    @PostMapping("/articles")
    public ResponseEntity<ArticleDto> addArticle(@AuthenticationPrincipal UserDetails user, @RequestBody ArticleRequest articleRequest){
        ArticleDto articleDto = adminService.addArticle(user.getUsername(), articleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(articleDto);
    }

//    update existing article
    @PutMapping("/articles/{id}")
    public ResponseEntity<ArticleDto> updateArticle(@AuthenticationPrincipal UserDetails user, @PathVariable Long id, @RequestBody ArticleRequest request) {
        ArticleDto articleDto = adminService.updateArticle(id, user.getUsername(), request);
        return ResponseEntity.ok(articleDto);
    }

//    delete Article
    @DeleteMapping("/articles/{id}")
    public ResponseEntity<String> deleteArticle(@AuthenticationPrincipal UserDetails user, @PathVariable Long id){
        String s = adminService.deleteArticle(user.getUsername(), id);
        return ResponseEntity.ok(s);
    }

}
