package com.chai.weblog.common.domain.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("t_tag_user")
public class TagRelUserDo {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long tagId;
    private Long userId;
    private LocalDateTime createTime;

}
