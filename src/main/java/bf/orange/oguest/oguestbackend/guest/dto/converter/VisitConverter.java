package bf.orange.oguest.oguestbackend.guest.dto.converter;

import bf.orange.oguest.oguestbackend.guest.business.*;
import bf.orange.oguest.oguestbackend.guest.dao.entity.Visit;
import bf.orange.oguest.oguestbackend.guest.dao.repository.VisitRepository;
import bf.orange.oguest.oguestbackend.guest.dto.VisitDto;
import bf.orange.oguest.oguestbackend.guest.payload.request.VisitRequest;
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
    @Autowired
    EmployeeBusiness employeeBusiness;
    @Autowired
    VisitorBusiness visitorBusiness;
    @Autowired
    TypeVisitBusiness typeVisitBusiness;
    @Autowired
    BadgeBusiness badgeBusiness;

    Visit setNew() {
        Visit visit = new Visit();
        visit.setDeleted(false);
        return visit;
    }

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
        Visit visit = this.setNew();
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

    public Visit fromRequest(VisitRequest visitRequest) {
        Visit visit = this.setNew();
        visit.setId(visitRequest.getId());
        visit.setEmployee(employeeBusiness.findById(visitRequest.getEmployeeId()));
        visit.setVisitor(visitorBusiness.findById(visitRequest.getVisitorId()));
        visit.setTypeVisit(typeVisitBusiness.findById(visitRequest.getTypeVisitId()));
        visit.setBadge(badgeBusiness.findById(visitRequest.getBadgeId()));
        visit.setComment(visitRequest.getComment());
        visit.setArrivalDate(visitRequest.getArrivalDate());
        visit.setArrivalTime(visitRequest.getArrivalTime());
        visit.setDepartureTime(visitRequest.getDepartureTime());
        visit.setMotive(visitRequest.getMotive());
        visit.setStatus(Visit.Status.valueOf(visitRequest.getStatus()));
        visit.setDeleted(false);
        return visit;
    }

}
