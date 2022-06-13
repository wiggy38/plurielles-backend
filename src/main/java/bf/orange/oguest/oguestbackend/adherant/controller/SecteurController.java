package bf.orange.oguest.oguestbackend.adherant.controller;

import bf.orange.oguest.oguestbackend.adherant.business.SecteurBusiness;
import bf.orange.oguest.oguestbackend.adherant.dao.entity.Secteur;
import bf.orange.oguest.oguestbackend.adherant.dto.SecteurDto;
import bf.orange.oguest.oguestbackend.adherant.dto.converter.SecteurConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/typeVisit/")
@Slf4j
public class SecteurController {

    @Autowired
    SecteurBusiness secteurBusiness;
    @Autowired
    SecteurConverter secteurConverter;

    @GetMapping("/r/list/all")
    public List<SecteurDto> getAllTypeVisits() {
        List<Secteur> secteurs = secteurBusiness.findAll();
        return secteurConverter.toDtoList(secteurs);
    }

    @PutMapping("/w/update/{id}")
    public ResponseEntity<List<SecteurDto>> update(@RequestBody SecteurDto secteurDto) {
        List<Secteur> secteurs = List.of(secteurConverter.fromDto(secteurDto));
        List<Secteur> savedSecteurs = secteurBusiness.saveAll(secteurs);
        return ResponseEntity.ok().body(secteurConverter.toDtoList(savedSecteurs));
    }

    @PostMapping("/w/insert/new")
    public ResponseEntity<List<SecteurDto>> create(@RequestBody List<SecteurDto> secteurDtos) throws Exception {
        List<Secteur> secteurs = new ArrayList<Secteur>(secteurConverter.fromDtoList(secteurDtos));
        List<Secteur> savedSecteurs = secteurBusiness.saveAll(secteurs);
        if(savedSecteurs == null) {
            throw new Exception("Une erreur s'est produite. Veuillez réessayer plus tard.");
        }
        return ResponseEntity.ok().body(secteurConverter.toDtoList(savedSecteurs));
    }

    @PostMapping("/d/delete/typeVisits")
    public void deleteAll(@PathVariable("id") List<Long> ids) {
        secteurBusiness.deleteAll(ids);
    }

}
