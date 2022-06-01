package bf.orange.oguest.oguestbackend.guest.controller;

import bf.orange.oguest.oguestbackend.guest.business.DepartmentBusiness;
import bf.orange.oguest.oguestbackend.guest.dao.entity.Department;
import bf.orange.oguest.oguestbackend.guest.dto.DepartmentDto;
import bf.orange.oguest.oguestbackend.guest.dto.converter.DepartmentConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/department/")
@Slf4j
public class DepartmentController {

    @Autowired
    DepartmentBusiness departmentBusiness;
    @Autowired
    DepartmentConverter departmentConverter;

    @GetMapping("/r/list/all")
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentBusiness.findAll();
        return departmentConverter.toDtoList(departments);
    }

    @PutMapping("/w/update/{id}")
    public ResponseEntity<List<DepartmentDto>> update(@RequestBody DepartmentDto departmentDto) {
        List<Department> departments = List.of(departmentConverter.fromDto(departmentDto));
        List<Department> savedDepartments = departmentBusiness.saveAll(departments);
        return ResponseEntity.ok().body(departmentConverter.toDtoList(savedDepartments));
    }

    @PostMapping("/w/insert/new")
    public ResponseEntity<List<DepartmentDto>> create(@RequestBody List<DepartmentDto> departmentDtos) throws Exception {
        List<Department> departments = new ArrayList<Department>(departmentConverter.fromDtoList(departmentDtos));
        List<Department> savedDepartments = departmentBusiness.saveAll(departments);
        if(savedDepartments == null) {
            throw new Exception("Une erreur s'est produite. Veuillez réessayer plus tard.");
        }
        return ResponseEntity.ok().body(departmentConverter.toDtoList(savedDepartments));
    }

    @PostMapping("/w/delete/departments")
    public void deleteAll(@PathVariable("id") List<Long> ids) {
        departmentBusiness.deleteAll(ids);
    }

}
