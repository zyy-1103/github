package com.road.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.road.bean.Datadictionary;
import com.road.bean.Datadictionaryitem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author sky
 * @since 2022-03-16
 */
@Mapper
@Repository
public interface DatadictionaryitemMapper extends BaseMapper<Datadictionaryitem> {

    @Select("select * from datadictionaryitem")
    List<Datadictionaryitem> selectAll();

    @Select("select * from datadictionaryitem where ${info} like '%${data}%'")
    List<Datadictionaryitem> search(String info, String data);

}
