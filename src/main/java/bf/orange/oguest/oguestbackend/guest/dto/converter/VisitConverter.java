package bf.orange.oguest.oguestbackend.guest.dto.converter;

import bf.orange.oguest.oguestbackend.guest.dao.entity.Visit;
import bf.orange.oguest.oguestbackend.guest.dao.repository.VisitRepository;
import bf.orange.oguest.oguestbackend.guest.dto.VisitDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VisitConverter {

    @Autowired
    VisitRepository visitRepository;
    @Autowired
    BadgeConverter badgeConverter;
    @Autowired
    VisitorConverter visitorConverter;
    @Autowired
    TypeVisitConverter typeVisitConverter;

    public VisitDto toDto(Visit visit) {
        VisitDto visitDto = new VisitDto();
        visitDto.setId(visit.getId());
        visitDto.setArrivalTime(visit.getArrivalTime());
        visitDto.setDepartureTime(visit.getDepartureTime());
        visitDto.setMotive(visit.getMotive());
        visitDto.setComment(visit.getComment());
        visitDto.setStatus(visit.getStatus());
        visitDto.setBadge(badgeConverter.toDto(visit.getBadge()));
        visitDto.setVisitor(visitorConverter.toDto(visit.getVisitor()));
        visitDto.setTypeVisit(typeVisitConverter.toDto(visit.getTypeVisit()));
        return visitDto;
    }

    public List<VisitDto> toDtoList(List<Visit> visits) {
        List<VisitDto> visitDtos = new ArrayList<>();
        for (Visit visit:visits) {
            visitDtos.add(this.toDto(visit));
        }
        return visitDtos;
    }

    public Visit fromDto(VisitDto visitDto) {
        Visit visit = new Visit();
        visit.setId(visitDto.getId());
        visit.setArrivalTime(visitDto.getArrivalTime());
        visit.setDepartureTime(visitDto.getDepartureTime());
        visit.setMotive(visitDto.getMotive());
        visit.setComment(visitDto.getComment());
        visit.setStatus(visitDto.getStatus());
        visit.setBadge(badgeConverter.fromDto(visitDto.getBadge()));
        visit.setVisitor(visitorConverter.fromDto(visitDto.getVisitor()));
        visit.setTypeVisit(typeVisitConverter.fromDto(visitDto.getTypeVisit()));
        return visit;
    }

    public List<Visit> fromDtoList(List<VisitDto> VisitDtos) {
        List<Visit> Visits = new ArrayList<>();
        for (VisitDto VisitDto:VisitDtos) {
            Visits.add(this.fromDto(VisitDto));
        }
        return Visits;
    }

}
