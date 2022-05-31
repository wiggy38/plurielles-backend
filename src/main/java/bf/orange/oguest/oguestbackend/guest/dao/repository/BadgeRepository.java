package bf.orange.oguest.oguestbackend.guest.dao.repository;

import bf.orange.oguest.oguestbackend.guest.dao.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BadgeRepository extends JpaRepository<Badge, Long> {
}