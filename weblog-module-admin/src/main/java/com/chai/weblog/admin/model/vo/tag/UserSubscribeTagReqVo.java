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
public class UserSubscribeTagReqVo {
    @NotNull(message = "订阅标签不能为空")
    String tag;
    @NotNull(message = "UserId不能为空")
    Long userId;

}
