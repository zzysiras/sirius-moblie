package com.cesaba.siriusmobliemain.Controller;


import com.alibaba.fastjson.JSON;
import com.cesaba.siriusmobliemain.annotation.LogAnnotation;
import com.cesaba.siriusmobliemain.entity.Article;
import com.cesaba.siriusmobliemain.mapper.ArticleMapper;
import com.cesaba.siriusmobliemain.tables.PageTableHandler;
import com.cesaba.siriusmobliemain.tables.PageTableRequest;
import com.cesaba.siriusmobliemain.tables.PagesTableResponse;
import com.cesaba.siriusmobliemain.tables.PageTableHandler.ListHandler;
import com.cesaba.siriusmobliemain.tables.PageTableHandler.CountHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "文章")
@RestController
@RequestMapping("/article")
public class articleController {


    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Autowired
    private ArticleMapper articleMapper;

    @LogAnnotation
    @ApiOperation(value = "添加文章")
    @PostMapping
    @PreAuthorize("hasAuthority('sys:user:query')")
    public Article savaAriticle(@RequestBody Article article){
        articleMapper.save(article);
        return article;
    }

    @ApiOperation(value = "获取分类文章")
    @RequestMapping(method = RequestMethod.GET, value = "/list/{type}")
    public List<Article> getListByType(@PathVariable("type") String type){
        return articleMapper.getListByType(type);
    }


    @LogAnnotation
    @ApiOperation(value = "删除文章")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        articleMapper.delete(id);
    }

    @LogAnnotation
    @ApiOperation(value = "更新文章")
    @PutMapping  //更新请求
    public Article updateNotice(@RequestBody Article article){
        articleMapper.update(article);
        return article;
    }

    @ApiOperation(value = "读取文章列表")
    @GetMapping
    public PagesTableResponse listArticle(PageTableRequest request){
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return articleMapper.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<Article> list(PageTableRequest request) {
                return articleMapper.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);

    }

    @ApiOperation(value = "通过传参获取文章")
    @GetMapping("/api/{id}")
    public String get(@PathVariable("id") Long id){
        System.out.println(id);
        String json = JSON.toJSONString(articleMapper.getById(id));
        System.out.println(articleMapper.getById(id));
        return json;}


}
