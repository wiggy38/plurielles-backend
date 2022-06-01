package bf.orange.oguest.oguestbackend.guest.controller;

import bf.orange.oguest.oguestbackend.guest.business.VisitorBusiness;
import bf.orange.oguest.oguestbackend.guest.dao.entity.Visitor;
import bf.orange.oguest.oguestbackend.guest.dto.VisitorDto;
import bf.orange.oguest.oguestbackend.guest.dto.converter.VisitorConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/visitor/")
@Slf4j
public class VisitorController {

    @Autowired
    VisitorBusiness visitorBusiness;
    @Autowired
    VisitorConverter visitorConverter;

    @GetMapping("/r/list/all")
    public List<VisitorDto> getAllVisitors() {
        List<Visitor> visitors = visitorBusiness.findAll();
        return visitorConverter.toDtoList(visitors);
    }

    @PutMapping("/w/update/{id}")
    public ResponseEntity<List<VisitorDto>> update(@RequestBody VisitorDto visitorDto) {
        List<Visitor> visitors = List.of(visitorConverter.fromDto(visitorDto));
        List<Visitor> savedVisitors = visitorBusiness.saveAll(visitors);
        return ResponseEntity.ok().body(visitorConverter.toDtoList(savedVisitors));
    }

    @PostMapping("/w/insert/new")
    public ResponseEntity<List<VisitorDto>> create(@RequestBody List<VisitorDto> visitorDtos) throws Exception {
        List<Visitor> visitors = new ArrayList<Visitor>(visitorConverter.fromDtoList(visitorDtos));
        List<Visitor> savedVisitors = visitorBusiness.saveAll(visitors);
        if(savedVisitors == null) {
            throw new Exception("Une erreur s'est produite. Veuillez réessayer plus tard.");
        }
        return ResponseEntity.ok().body(visitorConverter.toDtoList(savedVisitors));
    }

    @PostMapping("/w/delete/visitors")
    public void deleteAll(@PathVariable("id") List<Long> ids) {
        visitorBusiness.deleteAll(ids);
    }

}
