package bf.orange.oguest.oguestbackend.guest.dto.converter;

import bf.orange.oguest.oguestbackend.guest.dao.entity.Department;
import bf.orange.oguest.oguestbackend.guest.dao.repository.DepartmentRepository;
import bf.orange.oguest.oguestbackend.guest.dto.DepartmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepartmentConverter {

    @Autowired
    DepartmentRepository departmentRepository;

    public DepartmentDto toDto(Department dep) {
        DepartmentDto depDto = new DepartmentDto();
        depDto.setId(dep.getId());
        depDto.setLibelle(dep.getLibelle());
        return depDto;
    }

    public Department fromDto(DepartmentDto depDto) {
        Department dep = new Department();
        dep.setId(depDto.getId());
        dep.setLibelle(depDto.getLibelle());
        return dep;
    }

}
