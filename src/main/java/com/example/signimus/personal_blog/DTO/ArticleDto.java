package com.example.signimus.personal_blog.DTO;

import com.example.signimus.personal_blog.Entity.Article;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class ArticleDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime dateOfPublication;

    public static ArticleDto fromEntity(Article article) {
        return ArticleDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .author(article.getAuthor().getFirstName() + " " + article.getAuthor().getLastName())
                .dateOfPublication(article.getDateOfPublication())
                .build();
    }
}
