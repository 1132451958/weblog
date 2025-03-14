package com.chai.weblog.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chai.weblog.common.domain.dos.CategoryDO;
import com.chai.weblog.common.domain.mapper.CategoryMapper;
import com.chai.weblog.common.utils.Response;
import com.chai.weblog.web.model.vo.category.FindCategoryListRspVO;
import com.chai.weblog.web.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    /**
     * 获取分类接口
     * @return
     */
    public Response findCategoryList(){
        List<CategoryDO> categoryDOS = categoryMapper.selectList(Wrappers.emptyWrapper());
        // DO 转 VO
        List<FindCategoryListRspVO> vos = null;
        if (!CollectionUtils.isEmpty(categoryDOS)) {
            vos = categoryDOS.stream()
                    .map(categoryDO -> FindCategoryListRspVO.builder()
                            .id(categoryDO.getId())
                            .name(categoryDO.getName())
                            .build())
                    .collect(Collectors.toList());
        }

        return Response.success(vos);
    }
}
