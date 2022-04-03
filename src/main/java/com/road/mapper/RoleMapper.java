package com.road.mapper;

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
public interface RoleMapper extends BaseMapper<Role> {
    @Select("select * from role where id in (select jsid from operatorrole where czyid=#{id})")
    List<Role> getRolesByOperatorId(int id);

    @Select("select * from role")
    List<Role> selectAll();

    @Update("update role set ${info}=#{data} where id=#{id}")
    int update(String id, String info, String data);

    @Select("select * from role where id=#{id}")
    Role selectById(String id);
}
