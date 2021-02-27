package com.ulsum.spring.jdbc.service;

import com.ulsum.spring.jdbc.dao.EmployeeDao;
import com.ulsum.spring.jdbc.entity.Employee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
// 声明式事务核心注解
// 放在类上，将声明式事务配置应用于当前类所有方法，默认事务传播为 REQUIRED
@Transactional(propagation = Propagation.REQUIRED)
public class EmployeeServiceNew {

    @Resource
    private EmployeeDao employeeDao;

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    // 优于类上配置的
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Employee findById(Integer eno) {
        return employeeDao.findByID(eno);
    }

    public void batchImport() {
        for (int i = 1; i <= 10; i++) {
            if (i == 3) {
                throw new RuntimeException("异常了");
            }
            Employee employee = new Employee();
            employee.setEno(8000 + i);
            employee.setEname("员工" + i);
            employee.setSalary(4000f);
            employee.setDname("市场部");
            employee.setHiredate(new Date());
            employeeDao.insert(employee);
        }
    }

}
