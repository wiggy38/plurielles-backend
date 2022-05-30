package bf.orange.oguest.oguestbackend.dao.repository.auth;

import bf.orange.oguest.oguestbackend.dao.entity.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}