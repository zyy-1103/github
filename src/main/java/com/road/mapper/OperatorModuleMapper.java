package com.road.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.road.bean.Datadictionary;
import com.road.bean.OperatorModule;
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
public interface OperatorModuleMapper extends BaseMapper<OperatorModule> {
    @Select("select * from operator_module")
    List<OperatorModule> selectAll();

    @Update("update operator_module set ${info}=#{data} where id=#{id}")
    int updateInfo(String id, String info, String data);

    @Select("select * from operator_module where id=#{id}")
    OperatorModule selectOne(String id);
}
