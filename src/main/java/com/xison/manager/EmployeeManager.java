package com.xison.manager;

import com.xison.model.Employee;

/**
 * created by Xison Chen
 * on 2017/11/12-17:01
 */
public interface EmployeeManager {

    /**
     * 新增员工
     * @param employee
     * @return
     */
    Long addEmployee(Employee employee);
}
