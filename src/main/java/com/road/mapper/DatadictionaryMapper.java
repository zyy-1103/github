package com.road.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.road.bean.Datadictionary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

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
public interface DatadictionaryMapper extends BaseMapper<Datadictionary> {
    @Select("select * from datadictionary")
    List<Datadictionary> selectAll();

    @Update("update datadictionary set ${info}=#{data} where id=#{id}")
    int updateInfo(String id, String info, String data);

    @Select("select * from datadictionary where id=#{id}")
    Datadictionary selectOne(String id);
}
