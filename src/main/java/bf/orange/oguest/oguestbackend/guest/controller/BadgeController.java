package bf.orange.oguest.oguestbackend.guest.controller;

import bf.orange.oguest.oguestbackend.guest.business.BadgeBusiness;
import bf.orange.oguest.oguestbackend.guest.dao.entity.Badge;
import bf.orange.oguest.oguestbackend.guest.dto.BadgeDto;
import bf.orange.oguest.oguestbackend.guest.dto.converter.BadgeConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/badge/")
@Slf4j
public class BadgeController {

    @Autowired
    BadgeBusiness badgeBusiness;
    @Autowired
    BadgeConverter badgeConverter;

    @GetMapping("/r/list/all")
    public List<BadgeDto> getAllBadges() {
        List<Badge> badges = badgeBusiness.findAll();
        return badgeConverter.toDtoList(badges);
    }

    @PutMapping("/w/update/{id}")
    public ResponseEntity<List<BadgeDto>> update(@RequestBody BadgeDto badgeDto) {
        List<Badge> badges = List.of(badgeConverter.fromDto(badgeDto));
        List<Badge> savedBadges = badgeBusiness.saveAll(badges);
        return ResponseEntity.ok().body(badgeConverter.toDtoList(savedBadges));
    }

    @PostMapping("/w/insert/new")
    public ResponseEntity<List<BadgeDto>> create(@RequestBody List<BadgeDto> badgeDtos) throws Exception {
        List<Badge> badges = new ArrayList<Badge>(badgeConverter.fromDtoList(badgeDtos));
        List<Badge> savedBadges = badgeBusiness.saveAll(badges);
        if(savedBadges == null) {
            throw new Exception("Une erreur s'est produite. Veuillez réessayer plus tard.");
        }
        return ResponseEntity.ok().body(badgeConverter.toDtoList(savedBadges));
    }

    @PostMapping("/d/delete/badges")
    public void deleteAll(@PathVariable("id") List<Long> ids) {
        badgeBusiness.deleteAll(ids);
    }


}
