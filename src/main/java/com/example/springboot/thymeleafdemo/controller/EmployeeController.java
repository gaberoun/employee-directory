package com.example.springboot.thymeleafdemo.controller;

import com.example.springboot.thymeleafdemo.entity.Employee;
import com.example.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Show list of all employees
    @GetMapping("/list")
    public String listEmployees(Model model) {
        List<Employee> employeeList = employeeService.findAll();
        model.addAttribute("employees", employeeList);

        return "employees/list-employees";
    }

    // Show form to add new employee
    @GetMapping("/add")
    public String addEmployee(Model model) {
        model.addAttribute("employee", new Employee());

        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.save(employee);

        // Redirect to prevent duplicate submissions
        return "redirect:/employees/list";
    }

    // Show form to update an employee
    @GetMapping("/update")
    public String updateEmployee(@RequestParam("employeeId") int id, Model model) {
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);

        return "employees/employee-form";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int id) {
        employeeService.deleteById(id);

        return "redirect:/employees/list";
    }
}
