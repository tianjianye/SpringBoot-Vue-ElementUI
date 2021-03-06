package com.example.service;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.util.Util.getNullPropertyNames;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> listEmployee() {
        return employeeRepository.findAll();
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }

    public Employee updateEmployee(Integer id, Employee employee) {
        Employee currentInstance = employeeRepository.findById(id).orElse(null);
        String[] nullPropertyNames = getNullPropertyNames(employee);
        BeanUtils.copyProperties(employee, currentInstance, nullPropertyNames);
        return employeeRepository.save(currentInstance);
    }

    public Employee findByNameAndPassword(String name, String password) {
        return employeeRepository.findByNameAndPassword(name, password);
    }

    public List<Employee> findByName(String name) {
        return employeeRepository.findByName(name);
    }
}
