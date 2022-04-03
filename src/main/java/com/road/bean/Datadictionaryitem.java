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
public class Datadictionaryitem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String mc;

    private String bh;

    private String dlbh;

    private String sp;

    private String ms;

    private String zt;

    private LocalDate xzsj;

    private LocalDate xgsj;

    private LocalDate scsj;

    private String isdel;


}
