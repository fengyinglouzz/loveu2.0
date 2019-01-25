package com.zxzyyyy.loveu.controller;

import com.zxzyyyy.loveu.dto.ArticleDto;
import com.zxzyyyy.loveu.entity.Article;
import com.zxzyyyy.loveu.entity.Group;
import com.zxzyyyy.loveu.response.ResultMap;
import com.zxzyyyy.loveu.service.ArticleService;
import com.zxzyyyy.loveu.service.AuthService;
import com.zxzyyyy.loveu.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.zxzyyyy.loveu.response.ErrorCode.NOT_HAS_ARTICLE;

@RestController
@RequestMapping(value = "/article")
public class ArticleController {
    private final ArticleService articleService;
    private final AuthService authService;
    private final GroupService groupService;

    @Autowired
    public ArticleController(ArticleService articleService, AuthService authService, GroupService groupService) {
        this.articleService = articleService;
        this.authService = authService;
        this.groupService = groupService;
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResultMap add(@RequestBody ArticleDto articleDto) {
        Article article = new Article();
        article.setTitle(articleDto.getTitle());
        article.setContent(articleDto.getContent());
        article.setAuth(authService.findByUserName(articleDto.getUsername()));
        article.setGroup(groupService.findByGroupname(articleDto.getGroupName()));
        return new ResultMap().success().message("成功添加一篇文章").data(articleService.add(article));
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public ResultMap findArticlesByGroup(@RequestParam String groupName) {
        Group group = groupService.findByGroupname(groupName);
        return new ResultMap().success().message("查询成功").data(articleService.findArticlesByGroup(group));
    }

    @RequestMapping(value = "/{articleId}", method = RequestMethod.GET)
    public ResultMap findArticleById(@PathVariable int articleId) {
        Article article = articleService.findArticleById(articleId);
        if(article == null) {
            return new ResultMap().fail("434").message(NOT_HAS_ARTICLE);
        }
        return new ResultMap().success().message("查询成功").data(article);
    }
}
