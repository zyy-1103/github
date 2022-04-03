package com.road.bean;

import com.road.mapper.RoleMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 因为axios传输json对象时，会多一个对象名，因此用该对象进行包装
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parse {
    Dljcxx dljcxx;
    Organization organization;
    Datadictionary datadictionary;
    Datadictionaryitem datadictionaryitem;
    Jfodule jfodule;
    Role role;
    Operator operator;
    Operatorrole operatorrole;
    OperatorModule operatorModule;
    RoleModule roleModule;
}
