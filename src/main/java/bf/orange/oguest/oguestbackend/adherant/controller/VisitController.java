package bf.orange.oguest.oguestbackend.adherant.controller;

import bf.orange.oguest.oguestbackend.adherant.business.VisitBusiness;
import bf.orange.oguest.oguestbackend.adherant.dao.entity.Visit;
import bf.orange.oguest.oguestbackend.adherant.dto.VisitDto;
import bf.orange.oguest.oguestbackend.adherant.dto.converter.VisitConverter;
import bf.orange.oguest.oguestbackend.adherant.payload.request.VisitRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<List<VisitDto>> update(@RequestBody @Valid VisitRequest visitRequest) {
        Visit visit = visitConverter.fromRequest(visitRequest);
        List<Visit> visits = List.of(visit);
        List<Visit> savedVisits = visitBusiness.saveAll(visits);
        return ResponseEntity.ok().body(visitConverter.toDtoList(savedVisits));
    }

    @PostMapping("/w/insert/new")
    public ResponseEntity<List<VisitDto>> create(@RequestBody @Valid List<VisitDto> visitDtos) throws Exception {
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
