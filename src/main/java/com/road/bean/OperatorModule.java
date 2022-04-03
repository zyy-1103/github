package com.road.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

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
@TableName("operator_module")
public class OperatorModule implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer czyid;

    private Integer mkid;


}
