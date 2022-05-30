package bf.orange.oguest.oguestbackend.dao.repository;

import bf.orange.oguest.oguestbackend.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}