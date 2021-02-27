package com.ulsum.spring.jdbc;

import com.ulsum.spring.jdbc.dao.EmployeeDao;
import com.ulsum.spring.jdbc.service.EmployeeServiceNew;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext2.xml"})
public class JdbcTemplateTesterNew {

    @Resource
    private EmployeeDao employeeDao;

    @Resource
    private EmployeeServiceNew employeeServiceNew;

    @Test
    public void testBatchImport() {
        employeeServiceNew.batchImport();
    }

}
