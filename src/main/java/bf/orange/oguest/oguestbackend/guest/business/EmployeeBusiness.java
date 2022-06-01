package bf.orange.oguest.oguestbackend.guest.business;

import bf.orange.oguest.oguestbackend.guest.dao.entity.Employee;
import bf.orange.oguest.oguestbackend.guest.dao.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EmployeeBusiness {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    public Employee findById(Long id) {
        Employee employee = findById(id);
        return employee;
    }

    public List<Employee> saveAll(List<Employee> employees) {
        List<Employee> employees1 = employeeRepository.saveAll(employees);
        return employees1;
    }

    public void softDeleteAll(List<Long> ids) {
        List<Employee> employees = new ArrayList<>();
        for (Long id:ids) {
            Employee l = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aucune ressource correspondante trouvée pour "+id));
            l.setDeleted(true);
            employees.add(l);
        }
        employeeRepository.saveAll(employees);
    }

    public void deleteAll(List<Long> ids) {
        List<Employee> employees = new ArrayList<>();
        for (Long id:ids) {
            Employee l = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aucune ressource correspondante trouvée pour "+id));
            employees.add(l);
        }
        employeeRepository.deleteAll(employees);
    }
}
