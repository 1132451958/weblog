package com.chai.weblog.admin.service;

import com.chai.weblog.admin.model.vo.tag.*;
import com.chai.weblog.common.model.vo.tag.AddTagReqVO;
import com.chai.weblog.common.utils.*;

public interface AdminTagService {

    /**
     * 根据标签关键词模糊查询
     * @param searchTagsReqVO
     * @return
     */
    Response searchTags(SearchTagsReqVO searchTagsReqVO);
    /**
     * 添加标签集合
     * @param addTagReqVO
     * @return
     */
    Response addTags(AddTagReqVO addTagReqVO);

    /**
     * 查询标签分页
     * @param findTagPageListReqVO
     * @return
     */
    PageResponse findTagPageList(FindTagPageListReqVO findTagPageListReqVO);

    /**
     * 删除标签
     * @param deleteTagReqVO
     * @return
     */
    Response deleteTag(DeleteTagReqVO deleteTagReqVO);

    /**
     * 查询标签 Select 列表数据
     * @return
     */
    Response findTagSelectList();

    /**
     * 根据用户ID订阅标签
     * @return
     */
    Response subSubscribeTagByUserId(UserSubscribeTagReqVo userSubscribeTagReqVo);

    /**
     * 根据用户id查询用户订阅标签
     * @return
     */
    Response searchTagsByUserId(UserSubscribeSearchReqVo userSubscribeSearchReqVo);
}
