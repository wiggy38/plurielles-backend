package bf.orange.oguest.oguestbackend.auth.repository;

import bf.orange.oguest.oguestbackend.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findBySlug( String slug);
}