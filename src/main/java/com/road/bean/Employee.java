package com.road.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

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
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer ssjg;

    private String xm;

    private String xb;

    private String jh;

    private String sfzh;

    private String dh;

    private String sjch;

    private String gadh;

    private String dz;

    private Integer zw;

    private Integer rylx;

    private String sfyx;

    private LocalDate xgsj;

    private LocalDate xzsj;

    private LocalDate scsj;

    private String isdel;


}
