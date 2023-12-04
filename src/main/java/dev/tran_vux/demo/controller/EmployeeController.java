package dev.tran_vux.demo.controller;

import dev.tran_vux.demo.dto.employee.CreateEmployee;
import dev.tran_vux.demo.dto.ResponseDto;
import dev.tran_vux.demo.dto.employee.UpdateEmployee;
import dev.tran_vux.demo.entity.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
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
    public List<Employee> getEmployees(@RequestParam(required = false) Integer roll,
                                       @RequestParam(required = false) String phone){
        if (roll != null && phone == null){
            return findEmployeeByRoll(roll);
        } else if (roll == null && phone != null) {
            return findEmployeeByPhone(phone);
        } else if (roll != null && phone != null) {
            return findEmployeeByRollAndPhone(roll, phone);
        }
        return employees;
    }

    List<Employee> findEmployeeByRoll(Integer roll){
        List<Employee> result = new LinkedList<>();

        for (Employee employee:employees){
            if (employee.getRoll().equals(roll)){
                result.add(employee);
            }
        }

        return result;
    }

    List<Employee> findEmployeeByPhone(String phone){
        List<Employee> result = new LinkedList<>();

        for (Employee employee:employees){
            if (employee.getPhone().equals(phone)){
                result.add(employee);
            }
        }

        return result;
    }

    List<Employee> findEmployeeByRollAndPhone(Integer roll, String phone){
        List<Employee> result = new LinkedList<>();

        for (Employee employee:employees){
            if (employee.getRoll().equals(roll)  && employee.getPhone().equals(phone)){
                result.add(employee);
            }
        }

        return result;
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
