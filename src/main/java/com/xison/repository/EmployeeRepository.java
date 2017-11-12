package com.xison.repository;

import com.xison.model.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * created by Xison Chen
 * on 2017/11/12-16:59
 */
@Repository
public interface EmployeeRepository extends ElasticsearchRepository<Employee, Long> {
}
