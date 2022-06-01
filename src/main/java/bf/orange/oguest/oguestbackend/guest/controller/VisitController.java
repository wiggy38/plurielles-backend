package bf.orange.oguest.oguestbackend.guest.controller;

import bf.orange.oguest.oguestbackend.guest.business.VisitBusiness;
import bf.orange.oguest.oguestbackend.guest.dao.entity.Visit;
import bf.orange.oguest.oguestbackend.guest.dto.VisitDto;
import bf.orange.oguest.oguestbackend.guest.dto.converter.VisitConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/visit/")
@Slf4j
public class VisitController {

    @Autowired
    VisitBusiness visitBusiness;
    @Autowired
    VisitConverter visitConverter;

    @GetMapping("/r/list/all")
    public List<VisitDto> getAllVisits() {
        List<Visit> visits = visitBusiness.findAll();
        return visitConverter.toDtoList(visits);
    }

    @PutMapping("/w/update/{id}")
    public ResponseEntity<List<VisitDto>> update(@RequestBody VisitDto visitDto) {
        List<Visit> visits = List.of(visitConverter.fromDto(visitDto));
        List<Visit> savedVisits = visitBusiness.saveAll(visits);
        return ResponseEntity.ok().body(visitConverter.toDtoList(savedVisits));
    }

    @PostMapping("/w/insert/new")
    public ResponseEntity<List<VisitDto>> create(@RequestBody List<VisitDto> visitDtos) throws Exception {
        List<Visit> visits = new ArrayList<Visit>(visitConverter.fromDtoList(visitDtos));
        List<Visit> savedVisits = visitBusiness.saveAll(visits);
        if(savedVisits == null) {
            throw new Exception("Une erreur s'est produite. Veuillez réessayer plus tard.");
        }
        return ResponseEntity.ok().body(visitConverter.toDtoList(savedVisits));
    }

    @PostMapping("/d/delete/visits")
    public void deleteAll(@PathVariable("id") List<Long> ids) {
        visitBusiness.deleteAll(ids);
    }

}
