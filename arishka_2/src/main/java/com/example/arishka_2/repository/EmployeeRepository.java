package com.example.arishka_2.repository;



import com.example.arishka_2.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Проверка сущпо email
    boolean existsByEmail(String email);

    // Поиск по ФИО, ContainingIgnoreCase, чтобы искало по части строки и без учета регистра)
    List<Employee> findByFullNameContainingIgnoreCase(String fullName);

    //Поиск по актив
    List<Employee> findByActive(boolean active);

    // 4. Поиск по ФИО и активности
    List<Employee> findByFullNameContainingIgnoreCaseAndActive(String fullName, boolean active);
}

