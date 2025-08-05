package com.example.signimus.personal_blog.Service.ServiceInterface;

import com.example.signimus.personal_blog.DTO.ArticleDto;
import com.example.signimus.personal_blog.DTO.ArticleRequest;
import com.example.signimus.personal_blog.Entity.Article;

import java.util.List;

public interface AdminServiceInterface {
//    get all articles
    public List<ArticleDto> getAllArticles();

//    get article by id
    public ArticleDto getArticleById(Long id);

//    update article
    public ArticleDto updateArticle(Long id, String username, ArticleRequest request);

//    add new article
    public ArticleDto addArticle(String username, ArticleRequest request);

//    delete article
    public String deleteArticle(String username, Long id);
}
