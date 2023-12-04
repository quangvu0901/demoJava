package dev.tran_vux.demo.controller;

import dev.tran_vux.demo.dto.employee.CreateEmployee;
import dev.tran_vux.demo.dto.ResponseDto;
import dev.tran_vux.demo.dto.employee.UpdateEmployee;
import dev.tran_vux.demo.entity.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/employees")

public class EmployeeController {
    // Make list employee
    private static List<Employee> employees = new ArrayList<>();

    // Create employee
    @PostMapping
    public Employee createEmployee(@RequestBody CreateEmployee request){
        Employee employee = new Employee();

        employee.setEmployeeId(request.getEmployeeId());
        employee.setRoomId(request.getRoomId());
        employee.setName(request.getName());
        employee.setAge(request.getAge());
        employee.setPhone(request.getPhone());
        employee.setRoll(request.getRoll());

        employees.add(employee);

        return employee;
    }

    // Get list employee
    @GetMapping
    public List<Employee> getEmployees(){
        return employees;
    }

    // Get employee by id
    @GetMapping("/{employeeId}")
    public Employee getEmployee(@PathVariable String employeeId){
        return findEmployeeById(employeeId);
    }
    // Update employee
    @PutMapping("/{employeeId}")
    public Employee updateEmployee(@PathVariable String employeeId,
                                   @RequestBody UpdateEmployee request){
        Employee employee = findEmployeeById(employeeId);
        if (employee == null){
            return null;
        }

        employee.setName(request.getName());
        employee.setRoomId(request.getRoomId());
        employee.setPhone(request.getPhone());
        employee.setAge(request.getAge());
        employee.setRoll(request.getRoll());

        return employee;
    }

    // Delete employee
    @DeleteMapping("/{employeeId}")
    public ResponseDto deleteEmployee(@PathVariable String employeeId){
        Employee employee = findEmployeeById(employeeId);

        if (employee == null){
            return new ResponseDto(false, "Employee Not Found!");
        }
        employees.remove(employee);
        return new ResponseDto(true, "Employee Deleted.");
    }

    // Find employee by id
    private Employee findEmployeeById(String employeeId){
        for (Employee employee: employees){
            if (employee.getEmployeeId().equals(employeeId)){
                return employee;
            }
        }

        return null;
    }

}
