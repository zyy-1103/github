package com.road.mapper;

import com.road.bean.Operator;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.road.bean.Role;
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
public interface OperatorMapper extends BaseMapper<Operator> {
    @Select("select id from operator where dlm=#{name}")
    Integer getIdByName(String name);

    @Select("select * from operator where id=#{id}")
    Operator selectById(int id);

    @Select("select * from operator")
    List<Operator> selectAll();

    @Update("update operator set ${info}=#{data} where id=#{id}")
    int update(String id, String info, String data);


    @Select("select * from operator where id=#{id}")
    Operator getById(String id);

}
