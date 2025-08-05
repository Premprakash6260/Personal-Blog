package com.example.signimus.personal_blog.Service;

import com.example.signimus.personal_blog.DTO.ArticleDto;
import com.example.signimus.personal_blog.Entity.Article;
import com.example.signimus.personal_blog.Repository.ArticleRepository;
import com.example.signimus.personal_blog.Service.ServiceInterface.GuestServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuestServiceImpl implements GuestServiceInterface {
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<ArticleDto> getAllArticles() {
        List<Article> articles = articleRepository.findAllByOrderByIdAsc();
        List<ArticleDto> articleDtos = articles.stream().map(ArticleDto::fromEntity)
                .toList();
        return articleDtos;
    }

    @Override
    public ArticleDto getArticleById(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(
                    () -> new RuntimeException("Article not found")
                );
        return ArticleDto.fromEntity(article);
    }
}
