package bf.orange.oguest.oguestbackend.guest.business;

import bf.orange.oguest.oguestbackend.guest.dao.entity.Location;
import bf.orange.oguest.oguestbackend.guest.dao.repository.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class LocationBusiness {

    @Autowired
    LocationRepository locationRepository;

    public List<Location> findAll() {
        List<Location> locations = locationRepository.findAll();
        return locations;
    }

    public Location findById(Long id) {
        Location location = findById(id);
        return location;
    }

    public List<Location> saveAll(List<Location> locations) {
        for (Location location: locations) {
            location.setDeleted(false);
        }
        List<Location> locations1 = locationRepository.saveAll(locations);
        return locations1;
    }

    public void softDeleteAll(List<Long> ids) {
        List<Location> locations = new ArrayList<>();
        for (Long id:ids) {
            Location l = locationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aucune ressource correspondante trouvée pour "+id));
            l.setDeleted(true);
            locations.add(l);
        }
        locationRepository.saveAll(locations);
    }

    public void deleteAll(List<Long> ids) {
        List<Location> locations = new ArrayList<>();
        for (Long id:ids) {
            Location l = locationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aucune ressource correspondante trouvée pour "+id));
            locations.add(l);
        }
        locationRepository.deleteAll(locations);
    }

}
