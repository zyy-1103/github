package com.road.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author sky
 * @since 2022-03-16
 */
@Getter
@Setter
@TableName("role_module")
public class RoleModule implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;

    private Integer jsid;

    private Integer mkid;


}
