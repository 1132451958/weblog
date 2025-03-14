package com.chai.weblog.admin.controller;

import com.chai.weblog.admin.model.vo.tag.*;
import com.chai.weblog.admin.service.AdminTagService;
import com.chai.weblog.common.aspect.ApiOperationLog;
import com.chai.weblog.common.model.vo.tag.AddTagReqVO;
import com.chai.weblog.common.utils.PageResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.chai.weblog.common.utils.*;


@RestController
@RequestMapping("/admin")
@Api(tags = "Admin 标签模块")
public class AdminTagController {

    @Autowired
    private AdminTagService tagService;

    @PostMapping("/tag/add")
    @ApiOperation(value = "添加标签")
    @ApiOperationLog(description = "添加标签")
    public Response addTags(@RequestBody @Validated AddTagReqVO addTagReqVO) {
        return tagService.addTags(addTagReqVO);
    }

    @PostMapping("/tag/list")
    @ApiOperation(value = "标签分页数据获取")
    @ApiOperationLog(description = "标签分页数据获取")
    public PageResponse findTagPageList(@RequestBody @Validated FindTagPageListReqVO findTagPageListReqVO) {
        return tagService.findTagPageList(findTagPageListReqVO);
    }

     @PostMapping("/tag/delete")
     @ApiOperation(value = "删除分类")
     @ApiOperationLog(description = "删除分类")
     @PreAuthorize("hasRole('ROLE_ADMIN')")
     public Response deleteCategory(@RequestBody @Validated DeleteTagReqVO deleteTagReqVO) {
         return tagService.deleteTag(deleteTagReqVO);
     }

    @PostMapping("/tag/search")
    @ApiOperation(value = "标签模糊查询")
    @ApiOperationLog(description = "标签模糊查询")
    public Response searchTags(@RequestBody @Validated SearchTagsReqVO searchTagsReqVO) {
        return tagService.searchTags(searchTagsReqVO);
    }


    @PostMapping("/select/list")
    @ApiOperation(value = "查询标签 Select 列表数据")
    @ApiOperationLog(description = "查询标签 Select 列表数据")
    public Response findTagSelectList() {
        return tagService.findTagSelectList();
    }
    @PostMapping("/subscribe/id")
    @ApiOperation(value = "用户订阅标签服务")
    @ApiOperationLog(description = "根据UserId与tagID订阅")
    public Response findTagIdByTagId(@RequestBody UserSubscribeTagReqVo userSubscribeTagReqVo){
        return tagService.subSubscribeTagByUserId(userSubscribeTagReqVo);
    }

    @PostMapping("/subscribe/search")
    @ApiOperation(value = "查询用户订阅的标签服务")
    @ApiOperationLog(description = "根据UserId查询订阅的标签列表")
    public Response findTagIdByTagId(@RequestBody UserSubscribeSearchReqVo userSubscribeSearchReqVo){
        return tagService.searchTagsByUserId(userSubscribeSearchReqVo);
    }


}