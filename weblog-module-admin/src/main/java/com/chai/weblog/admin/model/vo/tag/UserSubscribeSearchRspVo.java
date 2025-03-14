package com.chai.weblog.admin.model.vo.tag;

import com.chai.weblog.common.domain.dos.TagDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSubscribeSearchRspVo {
    //标签列表
    // 表示当前登录用户订阅的所有标签
    private Long tag;
    private String tagname;
}
