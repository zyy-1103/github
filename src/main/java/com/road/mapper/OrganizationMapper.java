package com.road.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.road.bean.Organization;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mockito.internal.matchers.Or;
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
public interface OrganizationMapper extends BaseMapper<Organization> {
    @Select("select * from organization")
    List<Organization> selectAll();

    @Delete("delete from organization where bmmc=#{bmmc}")
    int deleteByBmmc(String bmmc);

    @Update("update organization set ${info}=#{data} where bmmc=#{org}")
    int updateInfo(String org, String info, String data);
}
