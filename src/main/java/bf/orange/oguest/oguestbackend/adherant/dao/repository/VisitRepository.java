package bf.orange.oguest.oguestbackend.adherant.dao.repository;

import bf.orange.oguest.oguestbackend.adherant.dao.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, Long> {
}