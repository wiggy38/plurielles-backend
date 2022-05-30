package bf.orange.oguest.oguestbackend.auth.repository;

import bf.orange.oguest.oguestbackend.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    Boolean existsByUsername(String cuid);

}