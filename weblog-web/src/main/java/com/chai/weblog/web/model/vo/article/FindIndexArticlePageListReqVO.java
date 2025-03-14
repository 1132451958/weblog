package com.chai.weblog.web.model.vo.article;

import com.chai.weblog.common.model.BasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(value = "首页查询分页")
public class FindIndexArticlePageListReqVO extends BasePageQuery {
}
