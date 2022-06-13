package bf.orange.oguest.oguestbackend.adherant.dao.repository;

import bf.orange.oguest.oguestbackend.adherant.dao.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}