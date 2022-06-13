package bf.orange.oguest.oguestbackend.adherant.business;

import bf.orange.oguest.oguestbackend.adherant.dao.entity.Category;
import bf.orange.oguest.oguestbackend.adherant.dao.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CategoryBusiness {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }

    public Category findById(Long id) {
        Category category = categoryRepository.findById(id).get();
        return category;
    }

    public List<Category> saveAll(List<Category> categories) {
        List<Category> departments1 = categoryRepository.saveAll(categories);
        return departments1;
    }

    public void softDeleteAll(List<Long> ids) {
        List<Category> categories = new ArrayList<>();
        for (Long id:ids) {
            Category l = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aucune ressource correspondante trouvée pour "+id));
            l.setDeleted(true);
            categories.add(l);
        }
        categoryRepository.saveAll(categories);
    }

    public void deleteAll(List<Long> ids) {
        List<Category> categories = new ArrayList<>();
        for (Long id:ids) {
            Category l = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aucune ressource correspondante trouvée pour "+id));
            categories.add(l);
        }
        categoryRepository.deleteAll(categories);
    }

}
