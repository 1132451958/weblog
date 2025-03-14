package com.chai.weblog.admin.model.vo.tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSubscribeSearchReqVo {
    @NotNull(message = "查询用户不能为空")
    Long UserId;
}
