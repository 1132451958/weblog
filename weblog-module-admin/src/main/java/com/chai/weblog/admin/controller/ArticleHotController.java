package com.chai.weblog.admin.controller;

import com.chai.weblog.admin.service.ArticleHotService;
import com.chai.weblog.common.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(tags = "文章热度模块")
public class ArticleHotController {
    @Autowired
    ArticleHotService articleHotService;
    @PostMapping("/hot/select")
    @ApiOperation("获得前10个热度最高的文章")
    public Response selectHotTen(){
        return Response.success(articleHotService.getTopTenArticle());
    }
}
