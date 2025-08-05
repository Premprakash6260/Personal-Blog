package com.example.signimus.personal_blog.Repository;

import com.example.signimus.personal_blog.Entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
//    find all articles order by Id in asc
    public List<Article> findAllByOrderByIdAsc();

}
