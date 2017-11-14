package com.xison.manager.impl;

import com.xison.manager.EmployeeManager;
import com.xison.model.Employee;
import com.xison.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * created by Xison Chen
 * on 2017/11/12-17:02
 */
@Service
public class EmployeeManagerImpl implements EmployeeManager {

    @Resource
    private EmployeeRepository employeeRepository;

    @Override
    public Long addEmployee(Employee employee) {
        Employee result = employeeRepository.save(employee);
        return result.getId();
    }
}
