package bf.orange.oguest.oguestbackend.guest.dao.repository;

import bf.orange.oguest.oguestbackend.guest.dao.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}