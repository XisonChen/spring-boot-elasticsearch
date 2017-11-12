package com.xison.controller;

import com.xison.manage.EmployeeManager;
import com.xison.model.Employee;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * created by Xison Chen
 * on 2017/11/12-17:03
 */
@RestController
public class EmployeeRestController {

    @Resource
    private EmployeeManager employeeManager;

    @RequestMapping(value = "/api/employee", method = RequestMethod.POST)
    public Long createCity(@RequestBody Employee employee) {
        return employeeManager.addEmployee(employee);
    }
}
