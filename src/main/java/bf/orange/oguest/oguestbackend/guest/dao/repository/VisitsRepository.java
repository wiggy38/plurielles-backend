package bf.orange.oguest.oguestbackend.guest.dao.repository;

import bf.orange.oguest.oguestbackend.guest.dao.entity.Visits;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitsRepository extends JpaRepository<Visits, Long> {
}