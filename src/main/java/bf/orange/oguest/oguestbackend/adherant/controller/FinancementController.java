package bf.orange.oguest.oguestbackend.adherant.controller;

import bf.orange.oguest.oguestbackend.adherant.business.FinancementBusiness;
import bf.orange.oguest.oguestbackend.adherant.dao.entity.Financement;
import bf.orange.oguest.oguestbackend.adherant.dto.FinancementDto;
import bf.orange.oguest.oguestbackend.adherant.dto.converter.FinancementConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/visitor/")
@Slf4j
public class FinancementController {

    @Autowired
    FinancementBusiness financementBusiness;
    @Autowired
    FinancementConverter financementConverter;

    @GetMapping("/r/list/all")
    public List<FinancementDto> getAllVisitors() {
        List<Financement> financements = financementBusiness.findAll();
        return financementConverter.toDtoList(financements);
    }

    @PutMapping("/w/update/{id}")
    public ResponseEntity<List<FinancementDto>> update(@RequestBody FinancementDto financementDto) {
        List<Financement> financements = List.of(financementConverter.fromDto(financementDto));
        List<Financement> savedFinancements = financementBusiness.saveAll(financements);
        return ResponseEntity.ok().body(financementConverter.toDtoList(savedFinancements));
    }

    @PostMapping("/w/insert/new")
    public ResponseEntity<List<FinancementDto>> create(@RequestBody List<FinancementDto> financementDtos) throws Exception {
        List<Financement> financements = new ArrayList<Financement>(financementConverter.fromDtoList(financementDtos));
        List<Financement> savedFinancements = financementBusiness.saveAll(financements);
        if(savedFinancements == null) {
            throw new Exception("Une erreur s'est produite. Veuillez réessayer plus tard.");
        }
        return ResponseEntity.ok().body(financementConverter.toDtoList(savedFinancements));
    }

    @PostMapping("/d/delete/visitors")
    public void deleteAll(@PathVariable("id") List<Long> ids) {
        financementBusiness.deleteAll(ids);
    }

}
