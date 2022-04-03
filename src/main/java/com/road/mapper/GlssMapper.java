package com.road.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.road.bean.Glss;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sky
 * @since 2022-03-16
 */
@Mapper
@Repository
public interface GlssMapper extends BaseMapper<Glss> {

}