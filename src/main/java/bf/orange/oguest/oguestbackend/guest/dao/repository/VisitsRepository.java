package bf.orange.oguest.oguestbackend.guest.dao.repository;

import bf.orange.oguest.oguestbackend.guest.dao.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitsRepository extends JpaRepository<Visit, Long> {
}