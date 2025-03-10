package com.chai.weblog.admin.service;

import com.chai.weblog.admin.model.vo.blogsettings.UpdateBlogSettingsReqVO;
import com.chai.weblog.common.utils.Response;

public interface AdminBlogSettingsService {
    /**
     * 更新博客设置信息
     * @param updateBlogSettingsReqVO
     * @return
     */
    Response updateBlogSettings(UpdateBlogSettingsReqVO updateBlogSettingsReqVO);

    Response findDetail();
}
