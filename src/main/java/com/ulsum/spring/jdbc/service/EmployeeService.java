package com.ulsum.spring.jdbc.service;

import com.ulsum.spring.jdbc.dao.EmployeeDao;
import com.ulsum.spring.jdbc.entity.Employee;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Date;

public class EmployeeService {

    private EmployeeDao employeeDao;

    private DataSourceTransactionManager transactionManager;

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public DataSourceTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(DataSourceTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void batchImport() {
        // 定义了事务默认的标准配置
        TransactionDefinition definition = new DefaultTransactionDefinition();
        // 开始一个事务，返回事务状态，事务状态说明当前事务的执行阶段
        TransactionStatus status = transactionManager.getTransaction(definition);
        try {
            for (int i = 1; i <= 10; i++) {
//                if (i == 3) {
//                    throw new RuntimeException("异常了");
//                }
                Employee employee = new Employee();
                employee.setEno(8000 + i);
                employee.setEname("员工" + i);
                employee.setSalary(4000f);
                employee.setDname("市场部");
                employee.setHiredate(new Date());
                employeeDao.insert(employee);
            }
            // 提交事务
            transactionManager.commit(status);
        } catch (RuntimeException e) {
            // 回滚事务
            transactionManager.rollback(status);
            // 自己修复
            e.printStackTrace();
//            // 向上传，上层修复
//            throw e;
        }
    }

}
