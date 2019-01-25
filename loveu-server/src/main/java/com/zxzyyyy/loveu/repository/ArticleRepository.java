package com.zxzyyyy.loveu.repository;

import com.zxzyyyy.loveu.entity.Article;
import com.zxzyyyy.loveu.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    List<Article> findAllByGroup(Group group);

    Article findById(int articleId);
}
