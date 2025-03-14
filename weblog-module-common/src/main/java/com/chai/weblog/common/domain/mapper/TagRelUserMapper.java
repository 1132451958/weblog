package com.chai.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chai.weblog.common.domain.dos.TagRelUserDo;

import java.sql.Wrapper;
import java.util.List;

public interface TagRelUserMapper extends BaseMapper<TagRelUserDo> {

    default List<TagRelUserDo> selectListById(Long userID){

        return selectList(Wrappers.<TagRelUserDo>lambdaQuery().in(TagRelUserDo::getUserId , userID));

    }
}
