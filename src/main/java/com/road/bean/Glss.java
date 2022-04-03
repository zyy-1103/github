package com.road.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDate;
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
public class Glss implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer ssdl;

    private String gllx;

    private String qd;

    private String zd;

    private String sfwx;

    private Integer cd;

    private LocalDate xzsj;

    private LocalDate xgsj;

    private LocalDate xcsj;

    private String isdel;

    private Integer blr;

    private String ip;


}
