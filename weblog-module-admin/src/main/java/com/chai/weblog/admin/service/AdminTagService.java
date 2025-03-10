package com.chai.weblog.admin.service;

import com.chai.weblog.admin.model.vo.tag.DeleteTagReqVO;
import com.chai.weblog.admin.model.vo.tag.FindTagPageListReqVO;
import com.chai.weblog.admin.model.vo.tag.SearchTagsReqVO;
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
}
