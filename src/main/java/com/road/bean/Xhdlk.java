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
public class Xhdlk implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String lkmc;

    private Integer ssdl;

    private LocalDate xzsj;

    private LocalDate xgsj;

    private LocalDate scsj;

    private String isdel;

    private Integer blr;

    private String ip;

    private String lddm;

    private Integer xhdlkid;

    private String jkdxh;

    private String firstcd;

    private String seconded;

    private String thirdcd;

    private String fifthcd;

    private String xithcd;

    private String seventhc;

    private String ninethcd;

    private String eighthcd;

    private String kbcd;

    private String dxq;

    private String cxcd;

    private String sfydxp;

    private String bz;

    private Integer cxcdcd;

    private String cxcdfx;


}
