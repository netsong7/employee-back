package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.domain.Employee;
import com.example.exception.EmployeeNotFoundException;
import com.example.repository.IEmployeeRepository;

@Service
public class EmployeeService implements IEmployeeService {
    private final IEmployeeRepository employeeRepository;

    public EmployeeService(IEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee add(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee update(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id:" + id));
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

}
