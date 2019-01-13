package com.zxzyyyy.loveu.service;

import com.zxzyyyy.loveu.entity.Article;
import com.zxzyyyy.loveu.repository.ArticleRepository;
import org.springframework.stereotype.Service;


@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    //增加恋爱记文章
    public Article add(Article article) {
        return articleRepository.saveAndFlush(article);
    }
}
