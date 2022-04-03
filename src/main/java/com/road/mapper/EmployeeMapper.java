package com.road.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.road.bean.Employee;
import org.apache.ibatis.annotations.Delete;
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
public interface EmployeeMapper extends BaseMapper<Employee> {
    @Select("select * from employee limit ${n},${perPage}")
    List<Employee> selectAll(int n,int perPage);

    @Select("select count(*) from employee")
    int selectPages();

    @Update("update employee set ${info} =#{data} where id=#{id}")
    int update(String id, String info, String data);

    @Select("select * from employee where ${info} like '%${data}%'")
    List<Employee> search(String info, String data);
}
