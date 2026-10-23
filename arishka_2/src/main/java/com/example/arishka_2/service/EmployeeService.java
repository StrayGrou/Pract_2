package com.example.arishka_2.service;



import com.example.arishka_2.model.Employee;
import com.example.arishka_2.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public List<Employee> search(String name, String active) {
        // Если имя для поиск
        boolean hasName = name != null && !name.trim().isEmpty();

        if (hasName && "active".equals(active)) {
            return employeeRepository.findByFullNameContainingIgnoreCaseAndActive(name.trim(), true);
        } else if (hasName && "inactive".equals(active)) {
            return employeeRepository.findByFullNameContainingIgnoreCaseAndActive(name.trim(), false);
        } else if (hasName) {
            return employeeRepository.findByFullNameContainingIgnoreCase(name.trim());
        } else if ("active".equals(active)) {
            return employeeRepository.findByActive(true);
        } else if ("inactive".equals(active)) {
            return employeeRepository.findByActive(false);
        } else {
            return employeeRepository.findAll();
        }
    }

    // Возвр сотр или ошибк
    public Employee getById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Сотрудник с ID " + id + " не найден"));
    }

    // Проверяет, есть ли сотрудник с таким email
    public boolean emailExists(String email) {
        return employeeRepository.existsByEmail(email);
    }


    public void save(Employee employee) {
        employeeRepository.save(employee);
    }


    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}

