package com.road.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
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
public class Jzwcrk implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer ssdl;

    private String ssld;

    private String mc;

    private String fw;

    private LocalDate xzsj;

    private LocalDate xgsj;

    private LocalDate scsj;

    private String isdel;

    private Integer blr;

    private String ip;

    private String lddm;

    private String ssld2;

    private String lddm2;


}
