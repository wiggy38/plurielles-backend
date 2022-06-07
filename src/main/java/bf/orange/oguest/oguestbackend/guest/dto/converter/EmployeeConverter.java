package bf.orange.oguest.oguestbackend.guest.dto.converter;

import bf.orange.oguest.oguestbackend.guest.business.DepartmentBusiness;
import bf.orange.oguest.oguestbackend.guest.business.LocationBusiness;
import bf.orange.oguest.oguestbackend.guest.dao.entity.Employee;
import bf.orange.oguest.oguestbackend.guest.dao.repository.EmployeeRepository;
import bf.orange.oguest.oguestbackend.guest.dao.repository.EmployeeRepository;
import bf.orange.oguest.oguestbackend.guest.dto.EmployeeDto;
import bf.orange.oguest.oguestbackend.guest.payload.request.EmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeConverter {
    
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DepartmentConverter departmentConverter;
    @Autowired
    LocationConverter locationConverter;
    @Autowired
    DepartmentBusiness departmentBusiness;
    @Autowired
    LocationBusiness locationBusiness;

    public EmployeeDto toDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setCuid(employee.getCuid());
        employeeDto.setNom(employee.getNom());
        employeeDto.setPrenoms(employee.getPrenoms());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setPhone(employee.getPhone());
        employeeDto.setDepartment(departmentConverter.toDto(employee.getDepartment()));
        employeeDto.setLocation(locationConverter.toDto(employee.getLocation()));
        return employeeDto;
    }

    public List<EmployeeDto> toDtoList(List<Employee> employees) {
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        for (Employee employee:employees) {
            employeeDtos.add(this.toDto(employee));
        }
        return employeeDtos;
    }

    public Employee fromDto(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setId(employeeDto.getId());
        employee.setCuid(employeeDto.getCuid());
        employee.setNom(employeeDto.getNom());
        employee.setPrenoms(employeeDto.getPrenoms());
        employee.setEmail(employeeDto.getEmail());
        employee.setPhone(employeeDto.getPhone());
        employee.setDepartment(departmentConverter.fromDto(employeeDto.getDepartment()));
        employee.setLocation(locationConverter.fromDto(employeeDto.getLocation()));
        return employee;
    }

    public List<Employee> fromDtoList(List<EmployeeDto> EmployeeDtos) {
        List<Employee> Employees = new ArrayList<>();
        for (EmployeeDto EmployeeDto:EmployeeDtos) {
            Employees.add(this.fromDto(EmployeeDto));
        }
        return Employees;
    }

    public Employee fromRequest(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setId(employeeRequest.getId());
        employee.setCuid(employeeRequest.getCuid());
        employee.setNom(employeeRequest.getNom());
        employee.setPrenoms(employeeRequest.getPrenoms());
        employee.setEmail(employeeRequest.getEmail());
        employee.setPhone(employeeRequest.getPhone());
        employee.setDepartment(departmentBusiness.findById(employeeRequest.getDepartmentId()));
        employee.setLocation(locationBusiness.findById(employeeRequest.getLocationId()));
        return employee;
    }

    public List<Employee> fromRequestList(List<EmployeeRequest> employeeRequests) {
        List<Employee> employees = new ArrayList<>();
        for (EmployeeRequest employeeRequest:employeeRequests) {
            employees.add(this.fromRequest(employeeRequest));
        }
        return employees;
    }

}
