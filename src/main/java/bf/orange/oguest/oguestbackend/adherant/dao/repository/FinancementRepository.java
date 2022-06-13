package bf.orange.oguest.oguestbackend.adherant.dao.repository;

import bf.orange.oguest.oguestbackend.adherant.dao.entity.Financement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancementRepository extends JpaRepository<Financement, Long> {
}