package com.example.arishka_2.controller;



import com.example.arishka_2.model.Employee;
import com.example.arishka_2.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // список сотрудников с поиском и фильтром
    @GetMapping
    public String listEmployees(@RequestParam(value = "searchName", required = false) String searchName,
                                @RequestParam(value = "filterActive", required = false, defaultValue = "all") String filterActive,
                                Model model) {
        model.addAttribute("employees", employeeService.search(searchName, filterActive));
        model.addAttribute("searchName", searchName);
        model.addAttribute("filterActive", filterActive);
        return "employees-list";
    }


    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee-form";
    }


    @PostMapping("/add")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee,
                               BindingResult bindingResult) {

        // проверк на сущ сотрудника
        if (employee.getId() == null && employeeService.emailExists(employee.getEmail())) {
            bindingResult.addError(new FieldError("employee", "email", "Сотрудник с таким Email уже существует"));
        }

        // Если есть ошибк
        if (bindingResult.hasErrors()) {
            return "employee-form";
        }

        employeeService.save(employee);
        return "redirect:/employees";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        try {
            Employee employee = employeeService.getById(id);
            model.addAttribute("employee", employee);
            return "employee-form";
        } catch (IllegalArgumentException e) {
            return "redirect:/employees";
        }
    }


    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        employeeService.delete(id);
        return "redirect:/employees";
    }
}
