package com.example.signimus.personal_blog.Service;

import com.example.signimus.personal_blog.DTO.ArticleDto;
import com.example.signimus.personal_blog.DTO.ArticleRequest;
import com.example.signimus.personal_blog.Entity.Article;
import com.example.signimus.personal_blog.Entity.User;
import com.example.signimus.personal_blog.Repository.ArticleRepository;
import com.example.signimus.personal_blog.Repository.UserRepository;
import com.example.signimus.personal_blog.Service.ServiceInterface.AdminServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService implements AdminServiceInterface {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<ArticleDto> getAllArticles() {
        List<Article> articles = articleRepository.findAllByOrderByIdAsc();
        return articles.stream()
                .map(ArticleDto::fromEntity)
                .toList();
    }

    @Override
    public ArticleDto getArticleById(Long id) {
       Article article = articleRepository.findById(id).orElseThrow(
               () -> new RuntimeException("Article not found")
       );
       return ArticleDto.fromEntity(article);
    }

    @Override
    public ArticleDto updateArticle(Long id, String username, ArticleRequest request) {
        Article existingArticle = articleRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Article not found")
        );
        if(!existingArticle.getAuthor().getUsername().equals(username)){
            throw new RuntimeException("You are not the author of this article.");
        }
        System.out.println(existingArticle);
        System.out.println(username);
        existingArticle.setTitle(request.getTitle());
        existingArticle.setContent(request.getContent());
        articleRepository.save(existingArticle);
        return ArticleDto.fromEntity(existingArticle);
    }

    @Override
    public ArticleDto addArticle(String username, ArticleRequest request) {
        User user = userRepository.findByEmail(username).orElseThrow(
                () -> new RuntimeException("User Not found")
        );
        Article article = Article.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .author(user)
                .build();
        articleRepository.save(article);
        return ArticleDto.fromEntity(article);
    }

    @Override
    public String deleteArticle(String username, Long id) {
        Article existingArticle = articleRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Article not found")
        );
        if(!existingArticle.getAuthor().getUsername().equals(username)) {
            throw new RuntimeException("You are not the author of this article");
        }
        articleRepository.delete(existingArticle);
        return "Article is deleted";
    }


}
