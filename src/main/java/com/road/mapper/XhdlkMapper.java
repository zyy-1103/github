package com.road.mapper;

import com.road.bean.BigFamily;
import com.road.bean.Xhdlk;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
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
public interface XhdlkMapper extends BaseMapper<Xhdlk> {
    @Select("select * from ${tableName} where ssdl=#{ssdl}")
    List<BigFamily> selectByTableName(String tableName, String ssdl);

    @Delete("delete from ${tableName} where id=#{id}")
    void deleteByTableName(String tableName, String id);

    @Update("update ${tableName} set ${info}=#{data} where id=#{id}")
    void updateByTableName(String tableName, String info, String data, String id);

    @Select("select * from ${tableName} where id=#{id}")
    BigFamily selectById(String id, String tableName);

}
