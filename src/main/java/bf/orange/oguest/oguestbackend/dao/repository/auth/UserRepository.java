package bf.orange.oguest.oguestbackend.dao.repository.auth;

import bf.orange.oguest.oguestbackend.dao.entity.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}