package bf.orange.oguest.oguestbackend.adherant.dao.repository;

import bf.orange.oguest.oguestbackend.adherant.dao.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}