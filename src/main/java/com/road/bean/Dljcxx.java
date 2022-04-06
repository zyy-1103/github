package com.road.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

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
@ToString
public class Dljcxx implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String dlm;

    private String ssdd;

    private String sszd;

    private String dlsx;

    private String dlfldj;

    private Integer cd;

    private Integer hdmkd;

    private String hdmkdbz;

    private String hdmjg;

    private String hdmjgbz;

    private String cdfb;

    private String cdfbbz;

    private String fx;

    private String qd;

    private String zd;

    private Integer xhdlkcount;

    private Integer fdkzxlcount;

    private Integer lnbwcount;

    private Integer glsscount;

    private Integer gjzdcount;

    private Integer rxhdxcount;

    private Integer jzwcrkcount;

    private Integer bzpcount;

    private Integer dlqcount;

    private Integer jshcdcount;

    private Integer tqlbcount;

    private Integer isglsscount;

    private Integer lsydbzcount;

    private Integer qtjtsscou;

    private LocalDate xzsj;

    private LocalDate xgsj;

    private LocalDate scsj;

    private String isdel;

    private Integer blr;

    private String ip;

    private String sjsdbz;

    private String dldm;

    private String bz;

    private Integer fdx;


}
