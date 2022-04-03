package com.road.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
public class Jfodule implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer ssxt;

    private String mkmc;

    private String mkbh;

    private String dyym;

    private String mksm;

    private String sfsm;

    private LocalDate xzsj;

    private LocalDate xgsj;

    private LocalDate scsj;

    private String isdel;


}
