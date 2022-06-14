package bf.orange.oguest.oguestbackend.adherant.dto.converter;

import bf.orange.oguest.oguestbackend.adherant.dao.entity.Category;
import bf.orange.oguest.oguestbackend.adherant.dao.repository.CategoryRepository;
import bf.orange.oguest.oguestbackend.adherant.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryConverter {

    @Autowired
    CategoryRepository categoryRepository;

    public CategoryDto toDto(Category dep) {
        CategoryDto depDto = new CategoryDto();
        depDto.setId(dep.getId());
        depDto.setLabel(dep.getLibelle());
        return depDto;
    }

    public List<CategoryDto> toDtoList(List<Category> deps) {
        List<CategoryDto> depDtos = new ArrayList<>();
        for (Category category :deps) {
            depDtos.add(this.toDto(category));
        }
        return depDtos;
    }

    public Category fromDto(CategoryDto depDto) {
        Category dep = new Category();
        dep.setId(depDto.getId());
        dep.setLibelle(depDto.getLabel());
        return dep;
    }

    public List<Category> fromDtoList(List<CategoryDto> depDtos) {
        List<Category> deps = new ArrayList<>();
        for (CategoryDto depDto:depDtos) {
            deps.add(this.fromDto(depDto));
        }
        return deps;
    }

}
