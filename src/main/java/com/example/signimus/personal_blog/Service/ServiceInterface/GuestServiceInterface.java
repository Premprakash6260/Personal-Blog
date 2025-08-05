package com.example.signimus.personal_blog.Service.ServiceInterface;

import com.example.signimus.personal_blog.DTO.ArticleDto;
import com.example.signimus.personal_blog.Entity.Article;

import java.util.List;

public interface GuestServiceInterface {
    public List<ArticleDto> getAllArticles();

    public ArticleDto getArticleById(Long id);
}
