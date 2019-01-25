package com.zxzyyyy.loveu.service;

import com.zxzyyyy.loveu.dto.ArticleDto;
import com.zxzyyyy.loveu.entity.Article;
import com.zxzyyyy.loveu.entity.Group;
import com.zxzyyyy.loveu.repository.ArticleRepository;
import com.zxzyyyy.loveu.repository.AuthRepository;
import com.zxzyyyy.loveu.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final AuthRepository authRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, AuthRepository authRepository, GroupRepository groupRepository) {
        this.articleRepository = articleRepository;
        this.authRepository = authRepository;
        this.groupRepository = groupRepository;
    }

    public Article add(Article article) {
        return articleRepository.saveAndFlush(article);
    }

    public List<Article> findArticlesByGroup(Group group) {
        return articleRepository.findAllByGroup(group);
    }

    public Article findArticleById(int articleId) {
        return articleRepository.findById(articleId);
    }
}
