package bf.orange.oguest.oguestbackend.guest.dto.converter;

import bf.orange.oguest.oguestbackend.guest.dao.entity.TypeVisit;
import bf.orange.oguest.oguestbackend.guest.dao.repository.TypeVisitRepository;
import bf.orange.oguest.oguestbackend.guest.dao.repository.TypeVisitRepository;
import bf.orange.oguest.oguestbackend.guest.dto.TypeVisitDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TypeVisitConverter {
    @Autowired
    TypeVisitRepository typeVisitRepository;

    public TypeVisitDto toDto(TypeVisit typeVisit) {
        TypeVisitDto typeVisitDto = new TypeVisitDto();
        typeVisitDto.setId(typeVisit.getId());
        typeVisitDto.setLibelle(typeVisit.getLibelle());
        return typeVisitDto;
    }

    public List<TypeVisitDto> toDtoList(List<TypeVisit> typeVisits) {
        List<TypeVisitDto> typeVisitDtos = new ArrayList<>();
        for (TypeVisit typeVisit:typeVisits) {
            typeVisitDtos.add(this.toDto(typeVisit));
        }
        return typeVisitDtos;
    }

    public TypeVisit fromDto(TypeVisitDto typeVisitDto) {
        TypeVisit typeVisit = new TypeVisit();
        typeVisit.setId(typeVisitDto.getId());
        typeVisit.setLibelle(typeVisitDto.getLibelle());
        return typeVisit;
    }

    public List<TypeVisit> toList(List<TypeVisitDto> TypeVisitDtos) {
        List<TypeVisit> TypeVisits = new ArrayList<>();
        for (TypeVisitDto TypeVisitDto:TypeVisitDtos) {
            TypeVisits.add(this.fromDto(TypeVisitDto));
        }
        return TypeVisits;
    }

}
