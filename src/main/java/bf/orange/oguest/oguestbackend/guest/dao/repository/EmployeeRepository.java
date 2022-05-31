package bf.orange.oguest.oguestbackend.guest.dao.repository;

import bf.orange.oguest.oguestbackend.guest.dao.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}