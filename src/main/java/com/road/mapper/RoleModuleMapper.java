package com.road.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.road.bean.OperatorModule;
import com.road.bean.RoleModule;
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
public interface RoleModuleMapper extends BaseMapper<RoleModule> {
    @Select("select * from role_module")
    List<RoleModule> selectAll();

    @Update("update role_module set ${info}=#{data} where id=#{id}")
    int updateInfo(String id, String info, String data);

    @Select("select * from role_module where id=#{id}")
    RoleModule selectOne(String id);
}
