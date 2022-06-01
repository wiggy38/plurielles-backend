package bf.orange.oguest.oguestbackend.guest.dto.converter;

import bf.orange.oguest.oguestbackend.guest.dao.entity.Badge;
import bf.orange.oguest.oguestbackend.guest.dao.entity.Department;
import bf.orange.oguest.oguestbackend.guest.dao.repository.DepartmentRepository;
import bf.orange.oguest.oguestbackend.guest.dto.BadgeDto;
import bf.orange.oguest.oguestbackend.guest.dto.DepartmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    public List<DepartmentDto> toDtoList(List<Department> deps) {
        List<DepartmentDto> depDtos = new ArrayList<>();
        for (Department department:deps) {
            depDtos.add(this.toDto(department));
        }
        return depDtos;
    }

    public Department fromDto(DepartmentDto depDto) {
        Department dep = new Department();
        dep.setId(depDto.getId());
        dep.setLibelle(depDto.getLibelle());
        return dep;
    }

    public List<Department> fromDtoList(List<DepartmentDto> depDtos) {
        List<Department> deps = new ArrayList<>();
        for (DepartmentDto depDto:depDtos) {
            deps.add(this.fromDto(depDto));
        }
        return deps;
    }

}
