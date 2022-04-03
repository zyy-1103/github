package com.road.mapper;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.road.bean.Dljcxx;
import com.sun.javafx.beans.IDProperty;
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
public interface DljcxxMapper extends BaseMapper<Dljcxx> {
    /**
     * 获取操作员所属中队的所有道路信息
     */
    @Select("select * from dljcxx where sszd=(" +
            "select bmmc from organization where id=(" +
            "select ssjg from employee where id=#{id}))")
    List<Dljcxx> getSquadron(int id);

    @Select("select * from dljcxx where ssdd in(" +
            "select bmmc from organization where id in(" +
            "select ssjg from employee where id=#{id}) or" +
            " id in(select sjbm from organization where id in(" +
            "select ssjg from employee where id=#{id})))")
    List<Dljcxx> getBrigade(int id);

    @Update("update dljcxx set ${info}=#{data} where id=#{id}")
    int update(String id, String info, String data);

    @Select("select * from dljcxx where ${info} like '%${data}%'")
    List<Dljcxx> search(String info, String data);
}
