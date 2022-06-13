package bf.orange.oguest.oguestbackend.adherant.controller;

import bf.orange.oguest.oguestbackend.adherant.business.CategoryBusiness;
import bf.orange.oguest.oguestbackend.adherant.dao.entity.Category;
import bf.orange.oguest.oguestbackend.adherant.dto.CategoryDto;
import bf.orange.oguest.oguestbackend.adherant.dto.converter.CategoryConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/category/")
@Slf4j
public class CategoryController {

    @Autowired
    CategoryBusiness categoryBusiness;
    @Autowired
    CategoryConverter categoryConverter;

    @GetMapping("/r/list/all")
    public List<CategoryDto> getAllDepartments() {
        List<Category> categories = categoryBusiness.findAll();
        return categoryConverter.toDtoList(categories);
    }

    @PutMapping("/w/update/{id}")
    public ResponseEntity<List<CategoryDto>> update(@RequestBody CategoryDto categoryDto) {
        List<Category> categories = List.of(categoryConverter.fromDto(categoryDto));
        List<Category> savedCategories = categoryBusiness.saveAll(categories);
        return ResponseEntity.ok().body(categoryConverter.toDtoList(savedCategories));
    }

    @PostMapping("/w/insert/new")
    public ResponseEntity<List<CategoryDto>> create(@RequestBody List<CategoryDto> categoryDtos) throws Exception {
        List<Category> categories = new ArrayList<Category>(categoryConverter.fromDtoList(categoryDtos));
        List<Category> savedCategories = categoryBusiness.saveAll(categories);
        if(savedCategories == null) {
            throw new Exception("Une erreur s'est produite. Veuillez réessayer plus tard.");
        }
        return ResponseEntity.ok().body(categoryConverter.toDtoList(savedCategories));
    }

    @PostMapping("/d/delete/departments")
    public void deleteAll(@PathVariable("id") List<Long> ids) {
        categoryBusiness.deleteAll(ids);
    }

}
