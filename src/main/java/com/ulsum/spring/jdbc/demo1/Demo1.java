package com.ulsum.spring.jdbc.demo1;

import com.ulsum.spring.jdbc.dao.EmployeeDao;
import com.ulsum.spring.jdbc.entity.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo1 {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        EmployeeDao employeeDao = context.getBean("employeeDao", EmployeeDao.class);
        Employee employee = employeeDao.findByID(3308);
        System.out.println(employee);
    }

}
