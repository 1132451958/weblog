package com.chai.weblog.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chai.weblog.admin.model.vo.tag.*;
import com.chai.weblog.admin.service.AdminTagService;
import com.chai.weblog.common.domain.dos.TagDO;
import com.chai.weblog.common.domain.dos.TagRelUserDo;
import com.chai.weblog.common.domain.dos.UserDO;
import com.chai.weblog.common.domain.mapper.TagMapper;
import com.chai.weblog.common.domain.mapper.TagRelUserMapper;
import com.chai.weblog.common.domain.mapper.UserMapper;
import com.chai.weblog.common.model.vo.category.SelectRspVO;
import com.chai.weblog.common.model.vo.tag.AddTagReqVO;
import com.chai.weblog.common.model.vo.tag.FindTagPageListRspVO;
import com.chai.weblog.common.utils.PageResponse;
import com.chai.weblog.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.chai.weblog.common.enums.ResponseCodeEnum.TAG_NOT_EXISTED;
import static com.chai.weblog.common.enums.ResponseCodeEnum.USERNAME_NOT_FOUND;

@Service
@Slf4j
public class AdminTagServiceImpl extends ServiceImpl<TagMapper, TagDO> implements AdminTagService {

    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TagRelUserMapper tagRelUserMapper;

    @Override
    public Response findTagSelectList() {
        // 查询所有标签, Wrappers.emptyWrapper() 表示查询条件为空
        List<TagDO> tagDOS = tagMapper.selectList(Wrappers.emptyWrapper());

        // DO 转 VO
        List<SelectRspVO> vos = null;
        if (!CollectionUtils.isEmpty(tagDOS)) {
            vos = tagDOS.stream()
                    .map(tagDO -> SelectRspVO.builder()
                            .label(tagDO.getName())
                            .value(tagDO.getId())
                            .build())
                    .collect(Collectors.toList());
        }

        return Response.success(vos);
    }

    /**
     * 用户订阅标签服务
     * @param userSubscribeTagReqVo
     * @return
     */
    @Override
    public Response subSubscribeTagByUserId(UserSubscribeTagReqVo userSubscribeTagReqVo) {
        String tag = userSubscribeTagReqVo.getTag();
        //根据tag查询tagId
        Long userID = userSubscribeTagReqVo.getUserId();
        UserDO userDO = userMapper.selectById(userID);
        //判断是否存在用户与标签
        if(Objects.isNull(userDO)){
            return Response.fail(USERNAME_NOT_FOUND);
        }
        TagDO tagDO= tagMapper.selectByName(tag);
        if(Objects.isNull(tagDO)){
            return Response.fail(TAG_NOT_EXISTED);
        }
        //用户与标签都存在，可以将用户与标签存入用户订阅标签数据库中
        TagRelUserDo tagRelUserDo = new TagRelUserDo().builder()
                .tagId(tagDO.getId())
                .userId(userID)
                .createTime(LocalDateTime.now()).build();
        tagRelUserMapper.insert(tagRelUserDo);
        return Response.success(tagRelUserDo);
    }

    @Override
    public Response searchTagsByUserId(UserSubscribeSearchReqVo userSubscribeSearchReqVo) {
        Long userID = userSubscribeSearchReqVo.getUserId();
        UserDO userDO = userMapper.selectById(userID);
        //判断是否存在用户
        if(Objects.isNull(userDO)){
            return Response.fail(USERNAME_NOT_FOUND);
        }
        //存在用户，则从表中取出所有订阅标签
        List<TagRelUserDo> tagRelUserDoList = tagRelUserMapper.selectListById(userID);
        //DO转VO
        List<TagDO> tagDOS = new ArrayList<>();
        if(!CollectionUtils.isEmpty(tagRelUserDoList)){
            tagDOS = tagRelUserDoList.stream().map(
                    tagRelUserDo -> tagMapper.selectById(tagRelUserDo.getTagId())
            ).collect(Collectors.toList());
        }
        List<UserSubscribeSearchRspVo> userSubscribeSearchRspVos = new ArrayList<>();
        userSubscribeSearchRspVos = tagDOS.stream().map(
                tagDO -> UserSubscribeSearchRspVo.builder()
                        .tagname(tagDO.getName())
                        .tag(tagDO.getId()).build()
        ).collect(Collectors.toList());
        return Response.success(userSubscribeSearchRspVos);
    }

    /**
     * 添加标签集合
     *
     * @param addTagReqVO
     * @return
     */
    @Override
    public Response addTags(AddTagReqVO addTagReqVO) {
        // vo 转 do
        List<TagDO> tagDOS = addTagReqVO.getTags().stream()
                .map(tagName -> TagDO.builder()
                        .name(tagName.trim()) // 去掉前后空格
                        .createTime(LocalDateTime.now())
                        .updateTime(LocalDateTime.now())
                        .build())
                .collect(Collectors.toList());

        // 批量插入
        try {
            saveBatch(tagDOS);
        } catch (Exception e) {
            log.warn("该标签已存在", e);
        }

        return Response.success();
    }

    /**
     * 查询标签分页
     *
     * @param findTagPageListReqVO
     * @return
     */
    @Override
    public PageResponse findTagPageList(FindTagPageListReqVO findTagPageListReqVO) {
        // 分页参数、条件参数
        Long current = findTagPageListReqVO.getCurrent();
        Long size = findTagPageListReqVO.getSize();
        String name = findTagPageListReqVO.getName();
        LocalDate startDate = findTagPageListReqVO.getStartDate();
        LocalDate endDate = findTagPageListReqVO.getEndDate();

        // 分页查询
        Page<TagDO> page = tagMapper.selectPageList(current, size, name, startDate, endDate);

        List<TagDO> records = page.getRecords();

        // do 转 vo
        List<FindTagPageListRspVO> vos = null;
        if (!CollectionUtils.isEmpty(records)) {
            vos = records.stream().map(tagDO -> FindTagPageListRspVO.builder()
                    .id(tagDO.getId())
                    .name(tagDO.getName())
                    .createTime(tagDO.getCreateTime())
                    .build()).collect(Collectors.toList());
        }

        return PageResponse.success(page, vos);
    }
    /**
     * 删除标签
     *
     * @param deleteTagReqVO
     * @return
     */
    @Override
    public Response deleteTag(DeleteTagReqVO deleteTagReqVO) {
        // 标签 ID
        Long tagId = deleteTagReqVO.getId();

        // 根据标签 ID 删除
        int count = tagMapper.deleteById(tagId);

        return count == 1 ? Response.success() : Response.fail(TAG_NOT_EXISTED);
    }

    @Override
    public Response searchTags(SearchTagsReqVO searchTagsReqVO) {
        String key = searchTagsReqVO.getKey();

        // 执行模糊查询
        List<TagDO> tagDOS = tagMapper.selectByKey(key);

        // do 转 vo
        List<SelectRspVO> vos = null;
        if (!CollectionUtils.isEmpty(tagDOS)) {
            vos = tagDOS.stream()
                    .map(tagDO -> SelectRspVO.builder()
                            .label(tagDO.getName())
                            .value(tagDO.getId())
                            .build())
                    .collect(Collectors.toList());
        }

        return Response.success(vos);
    }

}
