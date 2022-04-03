package com.road.mapper;

import com.road.bean.Jfodule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface JfoduleMapper extends BaseMapper<Jfodule> {
    @Select("select * from jfodule where id in (select mkid from role_module where jsid=#{id});")
    List<Jfodule> getModuleByRoleId(int id);

    @Select("select * from jfodule where id>7")
    List<Jfodule> selectAll();

    @Update("update jfodule set ${info}=#{data} where id=#{id}")
    int update(String id, String info, String data);

}
