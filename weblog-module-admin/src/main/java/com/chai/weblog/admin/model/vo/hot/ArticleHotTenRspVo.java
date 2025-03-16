package com.chai.weblog.admin.model.vo.hot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleHotTenRspVo {
    /**
     * 文章名
     */
    String articleName ;
    /**
     * 文章热度
     */
    Double hot;
}
