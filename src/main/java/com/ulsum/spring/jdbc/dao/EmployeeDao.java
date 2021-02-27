package com.ulsum.spring.jdbc.dao;

import com.ulsum.spring.jdbc.entity.Employee;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Employee findByID(Integer eno) {
        String sql = "SELECT * FROM employee WHERE eno = ?";
        // 查询单条数据
        Employee employee = jdbcTemplate.queryForObject(sql, new Object[]{eno}, new BeanPropertyRowMapper<Employee>(Employee.class));
        return employee;
    }

    public List<Employee> findByDname(String dname) {
        String sql = "SELECT * FROM employee WHERE dname = ?";
        // 查询复合数据
        List<Employee> list = jdbcTemplate.query(sql, new Object[]{dname}, new BeanPropertyRowMapper<Employee>(Employee.class));
        return list;
    }

    public List<Map<String, Object>> findMapByDane(String dname) {
        String sql = "SELECT eno AS e, salary AS s FROM employee WHERE dname = ?";
        // 将查询结果作为Map进行封装
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, new Object[]{dname});
        return maps;
    }

    public void insert(Employee employee) {
        String sql = "INSERT INTO employee(eno,ename,salary,dname,hiredate) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{
                employee.getEno(), employee.getEname(), employee.getSalary(), employee.getDname(), employee.getHiredate()
        });
    }

    public int update(Employee employee) {
        String sql = "UPDATE employee SET ename = ?, salary = ?, dname = ?, hiredate = ? WHERE eno = ?";
        int count = jdbcTemplate.update(sql, new Object[]{
                employee.getEname(), employee.getSalary(), employee.getDname(), employee.getHiredate(), employee.getEno()
        });
        return count;
    }

    public int delete(Integer eno) {
        String sql = "DELETE FROM employee WHERE eno = ?";
        int count = jdbcTemplate.update(sql, new Object[]{eno});
        return count;
    }

}
