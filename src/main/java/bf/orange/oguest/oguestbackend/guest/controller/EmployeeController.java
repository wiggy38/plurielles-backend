package bf.orange.oguest.oguestbackend.guest.controller;

import bf.orange.oguest.oguestbackend.guest.business.EmployeeBusiness;
import bf.orange.oguest.oguestbackend.guest.dao.entity.Employee;
import bf.orange.oguest.oguestbackend.guest.dao.entity.Visit;
import bf.orange.oguest.oguestbackend.guest.dto.EmployeeDto;
import bf.orange.oguest.oguestbackend.guest.dto.converter.EmployeeConverter;
import bf.orange.oguest.oguestbackend.guest.payload.request.EmployeeRequest;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/employee/")
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeBusiness employeeBusiness;
    @Autowired
    EmployeeConverter employeeConverter;

    @GetMapping("/r/list/all")
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeBusiness.findAll();
        return employeeConverter.toDtoList(employees);
    }

    @GetMapping("/r/list/searchBy/name/{data}")
    public List<EmployeeDto> searchAllEmployeesByName(@PathVariable String data) {
        log.error(">>>>>>>>>> DATA "+data);
        List<Employee> employees = employeeBusiness.searchByNomPrenomsContains(data);
        return employeeConverter.toDtoList(employees);
    }

    @PutMapping("/w/update/{id}")
    public ResponseEntity<List<EmployeeDto>> update(@RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeConverter.fromRequest(employeeRequest);
        List<Employee> employees = List.of(employee);
        List<Employee> savedEmployees = employeeBusiness.saveAll(employees);
        return ResponseEntity.ok().body(employeeConverter.toDtoList(savedEmployees));
    }

    @PostMapping("/w/insert/new")
    public ResponseEntity<List<EmployeeDto>> create(@RequestBody List<EmployeeRequest> employeeRequests) throws Exception {
        List<Employee> employees = new ArrayList<Employee>(employeeConverter.fromRequestList(employeeRequests));
        List<Employee> savedEmployees = employeeBusiness.saveAll(employees);
        if(savedEmployees == null) {
            throw new Exception("Une erreur s'est produite. Veuillez réessayer plus tard.");
        }
        return ResponseEntity.ok().body(employeeConverter.toDtoList(savedEmployees));
    }

    @PostMapping("/d/delete/employees")
    public void deleteAll(@PathVariable("id") List<Long> ids) {
        employeeBusiness.deleteAll(ids);
    }

}
