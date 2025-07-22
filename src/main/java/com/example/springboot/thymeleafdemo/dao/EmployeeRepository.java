package com.example.springboot.thymeleafdemo.dao;

import com.example.springboot.thymeleafdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // Added method to sort by last name
    public List<Employee> findAllByOrderByLastNameAsc();

}
