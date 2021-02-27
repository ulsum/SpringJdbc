package com.ulsum.spring.jdbc;

import com.ulsum.spring.jdbc.dao.EmployeeDao;
import com.ulsum.spring.jdbc.entity.Employee;
import com.ulsum.spring.jdbc.service.EmployeeService;
import com.ulsum.spring.jdbc.service.EmployeeServiceNew;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext1.xml"})
public class JdbcTemplateTester {

    @Resource
    private EmployeeDao employeeDao;

    @Resource
    private EmployeeService employeeService;

    @Resource
    private EmployeeServiceNew employeeServiceNew;

    @Test
    public void testFindById() {
        Employee employee = employeeDao.findByID(3308);
        System.out.println(employee);
    }

    @Test
    public void testFindByDname() {
        List<Employee> list = employeeDao.findByDname("研发部");
        System.out.println(list);
    }

    @Test
    public void testFindMapByDname() {
        System.out.println(employeeDao.findMapByDane("研发部"));
    }

    @Test
    public void testInsert() {
        Employee employee = new Employee();
        employee.setEno(8888);
        employee.setEname("赵六");
        employee.setSalary(6666f);
        employee.setDname("研发部");
        employee.setHiredate(new Date());
        employeeDao.insert(employee);
    }

    @Test
    public void testUpdate() {
        Employee employee = employeeDao.findByID(8888);
        employee.setSalary(employee.getSalary() + 1000);
        int count = employeeDao.update(employee);
        System.out.println("本次更新了：" + count + "行数据");
    }

    @Test
    public void testDelete() {
        System.out.println("本次更新了：" + employeeDao.delete(8888) + "行数据");
    }

    @Test
    public void testBatchImport() {
        employeeService.batchImport();
    }

    @Test
    public void testBatchImportNew() {
        employeeServiceNew.batchImport();
    }

}
