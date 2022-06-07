package bf.orange.oguest.oguestbackend.guest.controller;

import bf.orange.oguest.oguestbackend.guest.business.LocationBusiness;
import bf.orange.oguest.oguestbackend.guest.dao.entity.Location;
import bf.orange.oguest.oguestbackend.guest.dto.LocationDto;
import bf.orange.oguest.oguestbackend.guest.dto.converter.LocationConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/location/")
@Slf4j
public class LocationController {

    @Autowired
    LocationBusiness locationBusiness;
    @Autowired
    LocationConverter locationConverter;

    @GetMapping("/r/list/all")
    public List<LocationDto> getAllLocations() {
        List<Location> locations = locationBusiness.findAll();
        return locationConverter.toDtoList(locations);
    }

    @PutMapping("/w/update/{id}")
    public ResponseEntity<List<LocationDto>> update(@RequestBody @Valid LocationDto locationDto) {
        List<Location> locations = List.of(locationConverter.fromDto(locationDto));
        List<Location> savedLocations = locationBusiness.saveAll(locations);
        return ResponseEntity.ok().body(locationConverter.toDtoList(savedLocations));
    }

    @PostMapping("/w/insert/new")
    public ResponseEntity<List<LocationDto>> create(@RequestBody List<LocationDto> locationDtos) throws Exception {
        List<Location> locations = new ArrayList<Location>(locationConverter.fromDtoList(locationDtos));
        List<Location> savedLocations = locationBusiness.saveAll(locations);
        if(savedLocations == null) {
            throw new Exception("Une erreur s'est produite. Veuillez réessayer plus tard.");
        }
        return ResponseEntity.ok().body(locationConverter.toDtoList(savedLocations));
    }

    @PostMapping("/d/delete/locations")
    public void deleteAll(@PathVariable("id") List<Long> ids) {
        locationBusiness.deleteAll(ids);
    }


}
