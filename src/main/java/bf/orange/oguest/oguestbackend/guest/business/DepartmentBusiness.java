package bf.orange.oguest.oguestbackend.guest.business;

import bf.orange.oguest.oguestbackend.guest.dao.entity.Department;
import bf.orange.oguest.oguestbackend.guest.dao.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DepartmentBusiness {

    @Autowired
    DepartmentRepository departmentRepository;

    public List<Department> findAll() {
        List<Department> departments = departmentRepository.findAll();
        return departments;
    }

    public Department findById(Long id) {
        Department department = findById(id);
        return department;
    }

    public List<Department> saveAll(List<Department> departments) {
        List<Department> departments1 = departmentRepository.saveAll(departments);
        return departments1;
    }

    public void softDeleteAll(List<Long> ids) {
        List<Department> departments = new ArrayList<>();
        for (Long id:ids) {
            Department l = departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aucune ressource correspondante trouvée pour "+id));
            l.setDeleted(true);
            departments.add(l);
        }
        departmentRepository.saveAll(departments);
    }

    public void deleteAll(List<Long> ids) {
        List<Department> departments = new ArrayList<>();
        for (Long id:ids) {
            Department l = departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aucune ressource correspondante trouvée pour "+id));
            departments.add(l);
        }
        departmentRepository.deleteAll(departments);
    }

}
