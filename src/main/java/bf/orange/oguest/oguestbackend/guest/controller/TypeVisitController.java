package bf.orange.oguest.oguestbackend.guest.controller;

import bf.orange.oguest.oguestbackend.guest.business.TypeVisitBusiness;
import bf.orange.oguest.oguestbackend.guest.dao.entity.TypeVisit;
import bf.orange.oguest.oguestbackend.guest.dto.TypeVisitDto;
import bf.orange.oguest.oguestbackend.guest.dto.converter.TypeVisitConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/typeVisit/")
@Slf4j
public class TypeVisitController {

    @Autowired
    TypeVisitBusiness typeVisitBusiness;
    @Autowired
    TypeVisitConverter typeVisitConverter;

    @GetMapping("/r/list/all")
    public List<TypeVisitDto> getAllTypeVisits() {
        List<TypeVisit> typeVisits = typeVisitBusiness.findAll();
        return typeVisitConverter.toDtoList(typeVisits);
    }

    @PutMapping("/w/update/{id}")
    public ResponseEntity<List<TypeVisitDto>> update(@RequestBody TypeVisitDto typeVisitDto) {
        List<TypeVisit> typeVisits = List.of(typeVisitConverter.fromDto(typeVisitDto));
        List<TypeVisit> savedTypeVisits = typeVisitBusiness.saveAll(typeVisits);
        return ResponseEntity.ok().body(typeVisitConverter.toDtoList(savedTypeVisits));
    }

    @PostMapping("/w/insert/new")
    public ResponseEntity<List<TypeVisitDto>> create(@RequestBody List<TypeVisitDto> typeVisitDtos) throws Exception {
        List<TypeVisit> typeVisits = new ArrayList<TypeVisit>(typeVisitConverter.fromDtoList(typeVisitDtos));
        List<TypeVisit> savedTypeVisits = typeVisitBusiness.saveAll(typeVisits);
        if(savedTypeVisits == null) {
            throw new Exception("Une erreur s'est produite. Veuillez réessayer plus tard.");
        }
        return ResponseEntity.ok().body(typeVisitConverter.toDtoList(savedTypeVisits));
    }

    @PostMapping("/w/delete/typeVisits")
    public void deleteAll(@PathVariable("id") List<Long> ids) {
        typeVisitBusiness.deleteAll(ids);
    }

}
