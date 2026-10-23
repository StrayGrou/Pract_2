package com.example.arishka_2.model;



import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "ФИО не может быть пустым")
    @Size(min = 2, max = 100, message = "ФИО должно быть от 2 до 100 символов")
    @Column(nullable = false)
    private String fullName;

    @NotBlank(message = "Email не может быть пустым")
    @Email(message = "Некорректный формат email")
    @Column(nullable = false, unique = true) // Уникальный на уровне БД
    private String email;

    @NotBlank(message = "Телефон не может быть пустым")
    @Column(nullable = false)
    private String phone;

    @NotBlank(message = "Должность не может быть пустой")
    @Column(nullable = false)
    private String position;

    @NotNull(message = "Зарплата должна быть указана")
    @DecimalMin(value = "0.01", message = "Зарплата должна быть больше 0")
    @Column(nullable = false)
    private Double salary;

    @Column(nullable = false)
    private boolean active = true;


    public Employee() {
    }


    public Employee(String fullName, String email, String phone, String position, Double salary, boolean active) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.position = position;
        this.salary = salary;
        this.active = active;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public Double getSalary() { return salary; }
    public void setSalary(Double salary) { this.salary = salary; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
