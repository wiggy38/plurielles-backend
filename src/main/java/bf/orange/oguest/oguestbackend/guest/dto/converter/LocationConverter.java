package bf.orange.oguest.oguestbackend.guest.dto.converter;

import bf.orange.oguest.oguestbackend.guest.dao.entity.Location;
import bf.orange.oguest.oguestbackend.guest.dao.repository.LocationRepository;
import bf.orange.oguest.oguestbackend.guest.dto.LocationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LocationConverter {

    @Autowired
    LocationRepository locationRepository;
    
    public LocationDto toDto(Location location) {
        LocationDto locationDto = new LocationDto();
        locationDto.setId(location.getId());
        locationDto.setLibelle(location.getLibelle());
        locationDto.setTown(location.getTown());
        return locationDto;
    }

    public List<LocationDto> toDtoList(List<Location> locations) {
        List<LocationDto> locationDtos = new ArrayList<>();
        for (Location location:locations) {
            locationDtos.add(this.toDto(location));
        }
        return locationDtos;
    }

    public Location fromDto(LocationDto locationDto) {
        Location location = new Location();
        location.setId(locationDto.getId());
        location.setLibelle(locationDto.getLibelle());
        location.setTown(locationDto.getTown());
        location.setDeleted(false);
        return location;
    }

    public List<Location> fromDtoList(List<LocationDto> LocationDtos) {
        List<Location> Locations = new ArrayList<>();
        for (LocationDto LocationDto:LocationDtos) {
            Locations.add(this.fromDto(LocationDto));
        }
        return Locations;
    }
    
}
